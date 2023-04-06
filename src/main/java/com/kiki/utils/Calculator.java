package main.java.com.kiki.utils;

import main.java.com.kiki.model.PackageDetails;

public class Calculator {

    public static int calculateDeliveryCost(int baseDeliveryCost, PackageDetails packageDetails){
        return (baseDeliveryCost + (packageDetails.getPackageWeight()*10) + (packageDetails.getPackageDistance() * 5));
    }
}
