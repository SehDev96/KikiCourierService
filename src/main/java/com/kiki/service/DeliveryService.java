package main.java.com.kiki.service;

import main.java.com.kiki.constants.Coupons;
import main.java.com.kiki.model.*;
import main.java.com.kiki.utils.Calculator;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {

    public void calculateDeliveryCost(DeliveryRequest deliveryRequest){
        List<DeliveryOutput> deliveryOutputList = new ArrayList<>();
        int totalCost = 0;
        for(PackageDetails packageDetails:deliveryRequest.getPackageDetailsList()){
            DeliveryOutput deliveryOutput = new DeliveryOutput();
            deliveryOutput.setPackageId(packageDetails.getPackageId());

            totalCost = Calculator.calculateDeliveryCost(deliveryRequest.getBaseDeliveryCost(),packageDetails);

            if(packageDetails.getCouponCode() != null){
                Coupons coupon = Coupons.getCouponCriteria(Coupons.OfferCode.valueOf(packageDetails.getCouponCode()));
                if(coupon!=null && coupon.isCouponApplicable(packageDetails)){
                    int discountedPrice = (int) (totalCost*coupon.getDiscount());
                    totalCost = totalCost - discountedPrice;
                    deliveryOutput.setDiscount(discountedPrice);
                }
            }
            deliveryOutput.setTotalCost(totalCost);
            deliveryOutputList.add(deliveryOutput);
        }
        DeliverySummary.getInstance().setDeliveryOutputList(deliveryOutputList);
    }

    public void calculateDeliveryTime(List<Shipment> shipmentList){
        List<Vehicle> vehicleList = Vehicle.createListofVehicles(DeliveryRequest.getInstance().getNumberOfVehicles());
        for (Shipment shipment:shipmentList){
            Vehicle availableVehicle = Vehicle.getAvailableVehicleForDelivery(vehicleList);
            availableVehicle.addShipmentToShipmentList(shipment);

            double furthestPackageDeliveryTime = 0.0;

            for(PackageDetails packageDetails: shipment.getPackages()){
                double deliveryTime = Calculator.calculateDeliveryTime(packageDetails.getPackageDistance(),
                        DeliveryRequest.getInstance().getVehicleMaxSpeed()
                        );
                if (deliveryTime>furthestPackageDeliveryTime) furthestPackageDeliveryTime= deliveryTime;

                DeliveryOutput.getDeliveryOutputByPackageId(packageDetails.getPackageId()).setDeliveryTime(
                        availableVehicle.getTime()+deliveryTime
                );
            }

            availableVehicle.setTime(Double.sum(availableVehicle.getTime(),2*furthestPackageDeliveryTime));
        }
    }
}
