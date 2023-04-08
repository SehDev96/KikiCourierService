package main.java.com.kiki;

import main.java.com.kiki.model.DeliveryRequest;
import main.java.com.kiki.model.DeliverySummary;
import main.java.com.kiki.model.Shipment;
import main.java.com.kiki.service.DeliveryService;
import main.java.com.kiki.service.ShipmentManager;

import java.util.List;
import java.util.Scanner;

public class KikiApplication {
    public static void main(String[] args) {
        while (true) {
            try {
                String inputString = getUserInput().toString();
                DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.calculateDeliveryCost(deliveryRequest);
                if (deliveryRequest.getNumberOfVehicles() > 0) {
                    ShipmentManager shipmentManager = new ShipmentManager();
                    List<Shipment> shipmentList = shipmentManager.sortPackagesIntoShipments(deliveryRequest.getPackageDetailsList());
                    deliveryService.calculateDeliveryTime(shipmentList);
                }
                System.out.println(DeliverySummary.getInstance().toString());
                break; // Exit the loop if everything was successful
            } catch (Exception e){
                System.err.println("Error: " + e.getMessage());
                if(e instanceof IllegalArgumentException){
                    System.out.println("Please input the details according to standard format.");
                    System.out.println("Standard format: ");
                    System.out.println("------------------------------------------------");
                    System.out.println("base_delivery_cost no_of_packges\n" +
                            "pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1\n" +
                            " ....");
                    System.out.println("---------------------OR-------------------------");
                    System.out.println("base_delivery_cost no_of_packges\n" +
                            "pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1\n" +
                            " ....\n" +
                            "no_of_vehicles max_speed max_carriable_weight");
                    System.out.println("------------------------------------------------");
                } else {
                    // break the loop if Exception is other than input related exception.
                    break;
                }
                System.out.println("Please enter the input again:");
            }
        }

    }

    private static StringBuilder getUserInput() {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your input, then type 'exit' on a new line to finish:");

        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                sb.append(line).append("\n");
            } catch (Exception e) {
                System.out.println("Invalid Input");
                break;
            }
        }

        return sb;
    }

}
