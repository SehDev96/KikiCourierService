package main.java.com.kiki.model;

public class DeliveryOutput {
    private String packageId;
    private int discount;
    private int totalCost;

    public DeliveryOutput(String packageId, int discount, int totalCost) {
        this.packageId = packageId;
        this.discount = discount;
        this.totalCost = totalCost;
    }

    public DeliveryOutput() {
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return packageId + " " +discount+ " " + totalCost;
    }
}
