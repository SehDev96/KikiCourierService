package test.java.com.kiki;

import main.java.com.kiki.model.DeliveryOutput;
import main.java.com.kiki.model.DeliveryRequest;
import main.java.com.kiki.model.DeliverySummary;
import main.java.com.kiki.model.PackageDetails;
import main.java.com.kiki.service.DeliveryService;
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

    }
}
