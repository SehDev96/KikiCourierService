package main.java.com.kiki;

import main.java.com.kiki.model.DeliveryRequest;
import main.java.com.kiki.model.DeliverySummary;
import main.java.com.kiki.service.DeliveryService;

import java.util.Scanner;

public class KikiApplication {
    public static void main(String[] args) {
        String inputString = getUserInput().toString();
        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.calculateDeliveryCost(deliveryRequest);
        System.out.println(DeliverySummary.getInstance().toString());
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
