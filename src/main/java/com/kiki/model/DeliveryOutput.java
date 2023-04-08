package main.java.com.kiki.model;

public class DeliveryOutput {
    private String packageId;
    private int discount;
    private int totalCost;

    private double deliveryTime = 0.0;

    public DeliveryOutput(String packageId, int discount, int totalCost) {
        this.packageId = packageId;
        this.discount = discount;
        this.totalCost = totalCost;
    }

    public DeliveryOutput(String packageId, int discount, int totalCost, double deliveryTime) {
        this.packageId = packageId;
        this.discount = discount;
        this.totalCost = totalCost;
        this.deliveryTime = deliveryTime;
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

    public double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(double deliveryTime) {
        this.deliveryTime = Math.round(deliveryTime*100.0)/100.0;
    }

    public static DeliveryOutput getDeliveryOutputByPackageId(String packageId){
        DeliveryOutput requiredDeliveryOutput = new DeliveryOutput();
        if (DeliverySummary.getInstance().getDeliveryOutputList().size()>0){
            for(DeliveryOutput deliveryOutput:DeliverySummary.getInstance().getDeliveryOutputList()){
                if(deliveryOutput.getPackageId().equalsIgnoreCase(packageId)){
                    requiredDeliveryOutput = deliveryOutput;
                    break;
                }
            }
        }
        return requiredDeliveryOutput;
    }

    @Override
    public String toString() {
        if(deliveryTime != 0.0){
            return packageId + " " +discount+ " " + totalCost + " " + deliveryTime;

        } else {
            return packageId + " " +discount+ " " + totalCost;

        }
    }
}
