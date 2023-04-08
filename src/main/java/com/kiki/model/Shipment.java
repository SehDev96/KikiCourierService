package main.java.com.kiki.model;

import java.util.List;

public class Shipment {

    private List<PackageDetails> packages;

    public Shipment(List<PackageDetails> packages) {
        this.packages = packages;
    }

    public Shipment() {
    }

    public List<PackageDetails> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageDetails> packages) {
        this.packages = packages;
    }
}
