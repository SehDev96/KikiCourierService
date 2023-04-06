package main.java.com.kiki.service;

import main.java.com.kiki.constants.Coupons;
import main.java.com.kiki.model.DeliveryOutput;
import main.java.com.kiki.model.DeliveryRequest;
import main.java.com.kiki.model.DeliverySummary;
import main.java.com.kiki.model.PackageDetails;
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
}
