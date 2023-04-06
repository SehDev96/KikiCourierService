package main.java.com.kiki.model;

import java.util.ArrayList;
import java.util.List;

public class DeliverySummary {

    private static DeliverySummary deliverySummary = null;

    private List<DeliveryOutput> deliveryOutputList = new ArrayList<>();

    public static DeliverySummary getInstance() {
        if (deliverySummary == null) {
            deliverySummary = new DeliverySummary();
        }
        return deliverySummary;
    }

    public List<DeliveryOutput> getDeliveryOutputList() {
        return deliveryOutputList;
    }

    public void setDeliveryOutputList(List<DeliveryOutput> deliveryOutputList) {
        this.deliveryOutputList = deliveryOutputList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(DeliveryOutput deliveryOutput: deliveryOutputList){
            stringBuilder.append(deliveryOutput.toString()).append("\n");
        }
        if(stringBuilder.toString().endsWith("\n")){
            return stringBuilder.substring(0,stringBuilder.toString().length()-1);
        } else {
            return stringBuilder.toString();
        }
    }
}
