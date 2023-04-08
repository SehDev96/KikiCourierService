package main.java.com.kiki.service;

import main.java.com.kiki.model.PackageDetails;
import main.java.com.kiki.model.Shipment;

import java.util.ArrayList;
import java.util.List;

public class ShipmentManager {

    public List<Shipment> sortPackagesIntoShipments(List<PackageDetails> packageDetails) {
        List<Shipment> shipmentList = new ArrayList<>();

        List<PackageDetails> packagesToSort = new ArrayList<>(packageDetails);
        while (packagesToSort.size() > 0) {
            List<List<PackageDetails>> validPackageCombinations = generateValidPackageCombinations(
                    packagesToSort,
                    0,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    0,
                    200
            );

            if (validPackageCombinations.size() > 0) {
                List<PackageDetails> selectedPackagedToBeShipped = getSelectedPackagesForShipping(validPackageCombinations);
                if(selectedPackagedToBeShipped.size()>0) {
                    shipmentList.add(new Shipment(selectedPackagedToBeShipped));
                    packagesToSort.removeAll(selectedPackagedToBeShipped);
                }
            }
        }
        return shipmentList;
    }

    private List<List<PackageDetails>> generateValidPackageCombinations (
            List<PackageDetails> packages,
            int index,
            List<PackageDetails> current,
            List<List<PackageDetails>> result,
            int currentWeight,
            int maxWeight ) {
        if (currentWeight > maxWeight) {
            return result;
        } else if (currentWeight <= maxWeight && !current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }
        for (int i = index; i < packages.size(); i++) {
            current.add(packages.get(i));
            generateValidPackageCombinations(packages, i + 1, current, result, currentWeight + packages.get(i).getPackageWeight(), maxWeight);
            current.remove(current.size() - 1);
        }
        return result;
    }

    private List<PackageDetails> getSelectedPackagesForShipping(List<List<PackageDetails>> packagesCombinations){
        List<PackageDetails> packageDetailsList = new ArrayList<>();
        int numberOfPackages = 0;
        int totalWeightOfPackages = 0;
        for(List<PackageDetails> packageDetails:packagesCombinations){
            if(packageDetails.size() > numberOfPackages){
                numberOfPackages = packageDetails.size();
                packageDetailsList = packageDetails;
                totalWeightOfPackages = PackageDetails.getTotalWeight(packageDetails);
            } else if(packageDetails.size() == numberOfPackages){
                int totalWeight = PackageDetails.getTotalWeight(packageDetails);
                if(totalWeight>totalWeightOfPackages){
                    totalWeightOfPackages = totalWeight;
                    packageDetailsList = packageDetails;
                } else if(totalWeight == totalWeightOfPackages){
                    if(PackageDetails.getClosestPackageDistance(packageDetails)<PackageDetails.getClosestPackageDistance(packageDetailsList)){
                        packageDetailsList = packageDetails;
                    }
                }
            }
        }

        return packageDetailsList;
    }
}
