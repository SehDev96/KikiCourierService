package main.java.com.kiki.utils;

import main.java.com.kiki.model.PackageDetails;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calculator {

    public static int calculateDeliveryCost(int baseDeliveryCost, PackageDetails packageDetails){
        return (baseDeliveryCost + (packageDetails.getPackageWeight()*10) + (packageDetails.getPackageDistance() * 5));
    }

    public static double calculateDeliveryTime(int distance, int speed){
        if(speed!=0) {
            double deliveryTime = (double) distance/speed;
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.DOWN);
            return Double.parseDouble(df.format(deliveryTime));
        } else{
            return 0.0;
        }
    }
}
