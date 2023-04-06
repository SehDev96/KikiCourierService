package main.java.com.kiki;

import main.java.com.kiki.model.DeliveryRequest;

import java.util.Scanner;

public class KikiApplication {
    public static void main(String[] args) {
//        String userInput = getUserInput().toString();
        String inputString = "100 3\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG2 15 5 OFR002\n" +
                "PKG3 10 100 OFR003\n";
        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
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
