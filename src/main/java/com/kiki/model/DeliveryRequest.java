package main.java.com.kiki.model;

import main.java.com.kiki.constants.Coupons;
import main.java.com.kiki.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRequest {

    private static DeliveryRequest deliveryRequest = null;

    private int baseDeliveryCost;
    private int numberOfPackages;
    private List<PackageDetails> packageDetailsList;

    private int numberOfVehicles;

    private int vehicleMaxSpeed;

    private int vehicleMaxCarriableWeight;

    public static DeliveryRequest getInstance() {
        if (deliveryRequest == null) {
            deliveryRequest = new DeliveryRequest();
        }
        return deliveryRequest;
    }

    public DeliveryRequest parseDeliveryRequestFromString(String inputString) throws NumberFormatException,IllegalArgumentException{

        if (inputString.isEmpty())
            throw new IllegalArgumentException("Input is empty. Please enter delivery request according to the standard input format");

        String[] inputStringArray = inputString.split("\n");

        if(inputStringArray.length<2) throw new IllegalArgumentException(
                "Invalid input. Input needs to have at least two lines."
        );

        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance();

        // last line of inputStringArray becomes the deciding factor if the input contains vehicle details
        // acceptable input vehicle details e.g [2 70 200]
        String lastLine = inputStringArray[inputStringArray.length - 1];
        String[] lastLineArray = lastLine.split(" ");
        if (lastLineArray.length == 3) {
            if (ArrayUtils.isValidIntArray(lastLineArray)) {
                deliveryRequest.setNumberOfVehicles(Integer.parseInt(lastLineArray[0]));
                deliveryRequest.setVehicleMaxSpeed(Integer.parseInt(lastLineArray[1]));
                deliveryRequest.setVehicleMaxCarriableWeight(Integer.parseInt(lastLineArray[2]));
                inputStringArray = ArrayUtils.removeLastElement(inputStringArray);
            }
        }

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
            // to ensure packageid are unique
            if(PackageDetails.packageIdExists(packageId,packageDetailsList)) throw new IllegalArgumentException("Duplicated package id found. Please make sure package id's are unique");

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

        if(deliveryRequest.getNumberOfPackages() != deliveryRequest.getPackageDetailsList().size()) throw new IllegalArgumentException("Number of packages does not match the package details given");

        return deliveryRequest;
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

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public int getVehicleMaxSpeed() {
        return vehicleMaxSpeed;
    }

    public void setVehicleMaxSpeed(int vehicleMaxSpeed) {
        this.vehicleMaxSpeed = vehicleMaxSpeed;
    }

    public int getVehicleMaxCarriableWeight() {
        return vehicleMaxCarriableWeight;
    }

    public void setVehicleMaxCarriableWeight(int vehicleMaxCarriableWeight) {
        this.vehicleMaxCarriableWeight = vehicleMaxCarriableWeight;
    }
}
