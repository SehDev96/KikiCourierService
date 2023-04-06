package main.java.com.kiki.model;

import main.java.com.kiki.constants.Coupons;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRequest {

    private static DeliveryRequest deliveryRequest = null;

    private int baseDeliveryCost;
    private int numberOfPackages;
    private List<PackageDetails> packageDetailsList;

    public static DeliveryRequest getInstance() {
        if (deliveryRequest == null) {
            deliveryRequest = new DeliveryRequest();
        }
        return deliveryRequest;
    }

    public DeliveryRequest parseDeliveryRequestFromString(String inputString) {
        /*
            inputString Format: "[package_id] [package_weight] [distance] [offer_code]"
            inputString Format: "[package_id] [package_weight] [distance]"
         */

        if (inputString.isEmpty())
            throw new IllegalArgumentException("Input is empty. Please enter delivery request according to the standard input format");
        String[] inputStringArray = inputString.split("\n");
        if (inputStringArray.length > 0) {
            DeliveryRequest deliveryRequest = DeliveryRequest.getInstance();
            String deliveryParameters = inputStringArray[0];
            String[] deliveryParametersArray = deliveryParameters.split(" ");
            if (deliveryParametersArray.length < 2)
                throw new IllegalArgumentException("Invalid Input. Delivery parameters not entered in proper format.");
            deliveryRequest.setBaseDeliveryCost(Integer.parseInt(deliveryParametersArray[0]));
            deliveryRequest.setNumberOfPackages(Integer.parseInt(deliveryParametersArray[1]));

            List<PackageDetails> packageDetailsList = new ArrayList<>();
            for (int i = 1; i < inputStringArray.length; i++) {
                String[] packageDetailsStringArray = inputStringArray[i].split(" ");
                if (packageDetailsStringArray.length < 1 || packageDetailsStringArray.length > 4)
                    throw new IllegalArgumentException("Invalid Input. Package details not entered in proper format");
                String packageId = packageDetailsStringArray[0];
                int packageWeight = Integer.parseInt(packageDetailsStringArray[1]);
                int distance = Integer.parseInt(packageDetailsStringArray[2]);
                String couponCode = null;
                if (packageDetailsStringArray.length > 3 && Coupons.couponOfferExists(packageDetailsStringArray[3])) {
                    couponCode = packageDetailsStringArray[3];
                }

                PackageDetails packageDetails = new PackageDetails(
                        packageId,
                        packageWeight,
                        distance,
                        couponCode

                );
                packageDetailsList.add(packageDetails);
            }
            deliveryRequest.setPackageDetailsList(packageDetailsList);

            return deliveryRequest;
        }
        return null;
    }


    public int getBaseDeliveryCost() {
        return baseDeliveryCost;
    }

    public void setBaseDeliveryCost(int baseDeliveryCost) {
        this.baseDeliveryCost = baseDeliveryCost;
    }

    public int getNumberOfPackages() {
        return numberOfPackages;
    }

    public void setNumberOfPackages(int numberOfPackages) {
        this.numberOfPackages = numberOfPackages;
    }

    public List<PackageDetails> getPackageDetailsList() {
        return packageDetailsList;
    }

    public void setPackageDetailsList(List<PackageDetails> packageDetailsList) {
        this.packageDetailsList = packageDetailsList;
    }
}
