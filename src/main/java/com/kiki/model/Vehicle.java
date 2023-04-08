package main.java.com.kiki.model;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    private final String VEHICLE_ID_PREFIX = "vehicle_";

    private String vehicleId;

    private List<Shipment> shipmentList = new ArrayList<>();

    private double time = 0.0;

    public Vehicle() {
    }

    public Vehicle(int vehicleNumber) {
        this.vehicleId = VEHICLE_ID_PREFIX + vehicleNumber;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public List<Shipment> getShipmentList() {
        return shipmentList;
    }

    public void setShipmentList(List<Shipment> shipmentList) {
        this.shipmentList = shipmentList;
    }

    public void addShipmentToShipmentList(Shipment shipment){
        this.shipmentList.add(shipment);
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public static List<Vehicle> createListofVehicles(int numberOfVehicles){
        List<Vehicle> vehicleList = new ArrayList<>();
        for(int i=0;i<numberOfVehicles;i++){
            Vehicle vehicle = new Vehicle(i+1);
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public static Vehicle getAvailableVehicleForDelivery(List<Vehicle> vehicleList){
        double closestAvailableTime = Double.MAX_VALUE;
        Vehicle availableVehicle = new Vehicle();
        for(Vehicle vehicle: vehicleList){
            if(vehicle.getTime()<closestAvailableTime){
                closestAvailableTime = vehicle.getTime();
                availableVehicle = vehicle;
            }
        }
        return availableVehicle;
    }
}
