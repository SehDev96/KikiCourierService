package main.java.com.kiki.model;

import java.util.List;

public class PackageDetails {
    private String packageId;
    private int packageWeight;
    private int packageDistance;
    String couponCode;

    public PackageDetails(String packageId, int packageWeight, int packageDistance, String couponCode) {
        this.packageId = packageId.toUpperCase();
        this.packageWeight = packageWeight;
        this.packageDistance = packageDistance;
        this.couponCode = couponCode;
    }

    public static int getTotalWeight(List<PackageDetails> packageDetailList){
        int totalWeight = 0;
        for(PackageDetails packageDetails:packageDetailList){
            totalWeight += packageDetails.getPackageWeight();
        }
        return totalWeight;
    }

    public static int getClosestPackageDistance(List<PackageDetails> packageDetailsList){
        int distance = 0;
        for(PackageDetails packageDetails: packageDetailsList){
            distance += packageDetails.getPackageDistance();
        }
        return distance;
    }

    public static boolean packageIdExists(String packageId,List<PackageDetails> packageDetailsList){
        for(PackageDetails packageDetails: packageDetailsList){
            if(packageDetails.getPackageId().equalsIgnoreCase(packageId)) return true;
        }
        return false;
    }

    public String getPackageId() {
        return packageId;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public int getPackageDistance() {
        return packageDistance;
    }

    public String getCouponCode() {
        return couponCode;
    }
}
