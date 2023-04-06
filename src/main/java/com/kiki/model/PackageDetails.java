package main.java.com.kiki.model;

public class PackageDetails {
    private String packageId;
    private int packageWeight;
    private int packageDistance;
    String couponCode;

    public PackageDetails(String packageId, int packageWeight, int packageDistance, String couponCode) {
        this.packageId = packageId;
        this.packageWeight = packageWeight;
        this.packageDistance = packageDistance;
        this.couponCode = couponCode;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(int packageWeight) {
        this.packageWeight = packageWeight;
    }

    public int getPackageDistance() {
        return packageDistance;
    }

    public void setPackageDistance(int packageDistance) {
        this.packageDistance = packageDistance;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}