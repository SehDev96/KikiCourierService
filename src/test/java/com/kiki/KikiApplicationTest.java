package test.java.com.kiki;

import main.java.com.kiki.model.*;
import main.java.com.kiki.service.DeliveryService;
import main.java.com.kiki.service.ShipmentManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KikiApplicationTest {

    @Test
    public void parseUserInput(){
        String inputString = "100 3\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG2 15 5 OFR002\n" +
                "PKG3 10 100 OFR003\n";
        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
        assertEquals(100,DeliveryRequest.getInstance().getBaseDeliveryCost());
        assertEquals(3,DeliveryRequest.getInstance().getNumberOfPackages());
        assertEquals("PKG1",DeliveryRequest.getInstance().getPackageDetailsList().get(0).getPackageId());
        assertEquals(5,DeliveryRequest.getInstance().getPackageDetailsList().get(0).getPackageWeight());
        assertEquals(5,DeliveryRequest.getInstance().getPackageDetailsList().get(0).getPackageDistance());
        assertEquals("OFR001",DeliveryRequest.getInstance().getPackageDetailsList().get(0).getCouponCode());

        assertEquals("PKG3",DeliveryRequest.getInstance().getPackageDetailsList().get(2).getPackageId());
        assertEquals(10,DeliveryRequest.getInstance().getPackageDetailsList().get(2).getPackageWeight());
        assertEquals(100,DeliveryRequest.getInstance().getPackageDetailsList().get(2).getPackageDistance());
        assertEquals("OFR003",DeliveryRequest.getInstance().getPackageDetailsList().get(2).getCouponCode());
    }

    @Test
    public void testUserInputParsingWithVehicleDetails(){
        String inputString = "100 5\n" +
                "PKG1 50 30 OFR001\n" +
                "PKG2 75 125 OFR008\n" +
                "PKG3 175 100 OFR003\n" +
                "PKG4 110 60 OFR002\n" +
                "PKG5 155 95 NA\n" +
                "2 70 200";
        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
        assertEquals(100,deliveryRequest.getBaseDeliveryCost());
        assertEquals(5,deliveryRequest.getNumberOfPackages());
        assertEquals("PKG1",deliveryRequest.getPackageDetailsList().get(0).getPackageId());
        assertEquals(50,deliveryRequest.getPackageDetailsList().get(0).getPackageWeight());
        assertEquals(30,deliveryRequest.getPackageDetailsList().get(0).getPackageDistance());
        assertEquals("OFR001",deliveryRequest.getPackageDetailsList().get(0).getCouponCode());
        assertEquals(2,deliveryRequest.getNumberOfVehicles());
        assertEquals(70,deliveryRequest.getVehicleMaxSpeed());
        assertEquals(200, deliveryRequest.getVehicleMaxCarriableWeight());

    }

    @Test
    public void testCalculateDeliveryCostService(){
        String inputString = "100 3\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG2 15 5 OFR002\n" +
                "PKG3 10 100 OFR003\n";
        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);

        DeliveryService deliveryService = new DeliveryService();
        deliveryService.calculateDeliveryCost(deliveryRequest);

        DeliverySummary deliverySummary = DeliverySummary.getInstance();

        DeliveryOutput expectedDeliveryOutput1 = new DeliveryOutput("PKG1",0,175);
        DeliveryOutput expectedDeliveryOutput2 = new DeliveryOutput("PKG2",0,275);
        DeliveryOutput expectedDeliveryOutput3 = new DeliveryOutput("PKG3",35,665);

        assertEquals(expectedDeliveryOutput1.getPackageId(),deliverySummary.getDeliveryOutputList().get(0).getPackageId());
        assertEquals(expectedDeliveryOutput1.getDiscount(),deliverySummary.getDeliveryOutputList().get(0).getDiscount());
        assertEquals(expectedDeliveryOutput1.getTotalCost(),deliverySummary.getDeliveryOutputList().get(0).getTotalCost());

        assertEquals(expectedDeliveryOutput2.getPackageId(),deliverySummary.getDeliveryOutputList().get(1).getPackageId());
        assertEquals(expectedDeliveryOutput2.getDiscount(),deliverySummary.getDeliveryOutputList().get(1).getDiscount());
        assertEquals(expectedDeliveryOutput2.getTotalCost(),deliverySummary.getDeliveryOutputList().get(1).getTotalCost());

        assertEquals(expectedDeliveryOutput3.getPackageId(),deliverySummary.getDeliveryOutputList().get(2).getPackageId());
        assertEquals(expectedDeliveryOutput3.getDiscount(),deliverySummary.getDeliveryOutputList().get(2).getDiscount());
        assertEquals(expectedDeliveryOutput3.getTotalCost(),deliverySummary.getDeliveryOutputList().get(2).getTotalCost());

    }

    @Test
    public void testSortPackagesIntoShipments() {
        PackageDetails pkg1 = new PackageDetails("PKG1", 50, 30, "OFR001");
        PackageDetails pkg2 = new PackageDetails("PKG2", 75, 125, "OFR008");
        PackageDetails pkg3 = new PackageDetails("PKG3", 175, 100, "OFR003");
        PackageDetails pkg4 = new PackageDetails("PKG4", 110, 60, "OFR002");
        PackageDetails pkg5 = new PackageDetails("PKG5", 155, 95, "NA");
        List<PackageDetails> packageDetails = new ArrayList<>(List.of(pkg1, pkg2, pkg3, pkg4, pkg5));

        ShipmentManager shipmentManager = new ShipmentManager();
        List<Shipment> actualShipmentList = shipmentManager.sortPackagesIntoShipments(packageDetails);

        Shipment shipment1 = new Shipment(List.of(pkg2, pkg4));
        Shipment shipment2 = new Shipment(List.of(pkg3));
        Shipment shipment3 = new Shipment(List.of(pkg5));
        Shipment shipment4 = new Shipment(List.of(pkg1));
        List<Shipment> expectedShipmentList = new ArrayList<>(List.of(shipment1, shipment2, shipment3, shipment4));

        assertEquals(expectedShipmentList.get(0).getPackages().get(0).getPackageId(), actualShipmentList.get(0).getPackages().get(0).getPackageId());
        assertEquals(expectedShipmentList.get(0).getPackages().get(1).getPackageId(), actualShipmentList.get(0).getPackages().get(1).getPackageId());
        assertEquals(expectedShipmentList.get(1).getPackages().get(0).getPackageId(), actualShipmentList.get(1).getPackages().get(0).getPackageId());
        assertEquals(expectedShipmentList.get(2).getPackages().get(0).getPackageId(), actualShipmentList.get(2).getPackages().get(0).getPackageId());
        assertEquals(expectedShipmentList.get(3).getPackages().get(0).getPackageId(), actualShipmentList.get(3).getPackages().get(0).getPackageId());
    }

    @Test
    public void testCalculateDeliveryTimeService(){
        String inputString = "100 5\n" +
                "PKG1 50 30 OFR001\n" +
                "PKG2 75 125 OFR008\n" +
                "PKG3 175 100 OFR003\n" +
                "PKG4 110 60 OFR002\n" +
                "PKG5 155 95 NA\n" +
                "2 70 200";
        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
        ShipmentManager shipmentManager = new ShipmentManager();
        List<Shipment> shipmentList = shipmentManager.sortPackagesIntoShipments(deliveryRequest.getPackageDetailsList());

        DeliveryService deliveryService = new DeliveryService();
        deliveryService.calculateDeliveryCost(deliveryRequest);
        deliveryService.calculateDeliveryTime(shipmentList);

        assertEquals(3.98,DeliveryOutput.getDeliveryOutputByPackageId("PKG1").getDeliveryTime(),0.02);
        assertEquals(1.78,DeliveryOutput.getDeliveryOutputByPackageId("PKG2").getDeliveryTime(),0.02);
        assertEquals(1.42,DeliveryOutput.getDeliveryOutputByPackageId("PKG3").getDeliveryTime(),0.02);
        assertEquals(0.85,DeliveryOutput.getDeliveryOutputByPackageId("PKG4").getDeliveryTime(),0.02);
        assertEquals(4.19,DeliveryOutput.getDeliveryOutputByPackageId("PKG5").getDeliveryTime(),0.02);

    }

    @Test
    public void testOutputSummary(){
        DeliveryOutput deliveryOutput = new DeliveryOutput("PKG1",0,175);
        DeliveryOutput deliveryOutput2 = new DeliveryOutput("PKG2",0,275);
        DeliveryOutput deliveryOutput3 = new DeliveryOutput("PKG3",5,665);

        List<DeliveryOutput> deliveryOutputList = new ArrayList<>(List.of(deliveryOutput,deliveryOutput2,deliveryOutput3));

        DeliverySummary.getInstance().setDeliveryOutputList(deliveryOutputList);

        String expectedString = "PKG1 0 175\n" +
                "PKG2 0 275\n" +
                "PKG3 5 665";
        assertEquals(expectedString,DeliverySummary.getInstance().toString());

        DeliveryOutput deliveryOutputSample = new DeliveryOutput("PKG1",0,750,3.98);
        DeliveryOutput deliveryOutputSample2 = new DeliveryOutput("PKG2",0,1475,1.78);
        DeliveryOutput deliveryOutputSample3 = new DeliveryOutput("PKG3",0,2350,1.42);
        DeliveryOutput deliveryOutputSample4 = new DeliveryOutput("PKG4",105,1395,0.85);
        DeliveryOutput deliveryOutputSample5 = new DeliveryOutput("PKG5",0,2125,4.19);
        List<DeliveryOutput> deliveryOutputList2 = new ArrayList<>(List.of(
                deliveryOutputSample,deliveryOutputSample2,deliveryOutputSample3,deliveryOutputSample4,deliveryOutputSample5
        ));

        DeliverySummary.getInstance().setDeliveryOutputList(deliveryOutputList2);

        String expectedString2 = "PKG1 0 750 3.98\n" +
                "PKG2 0 1475 1.78\n" +
                "PKG3 0 2350 1.42\n" +
                "PKG4 105 1395 0.85\n" +
                "PKG5 0 2125 4.19";
        assertEquals(expectedString2,DeliverySummary.getInstance().toString());



    }
}
