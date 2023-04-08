package test.java.com.kiki;

import main.java.com.kiki.model.DeliveryOutput;
import main.java.com.kiki.model.DeliveryRequest;
import main.java.com.kiki.model.DeliverySummary;
import main.java.com.kiki.model.Shipment;
import main.java.com.kiki.service.DeliveryService;
import main.java.com.kiki.service.ShipmentManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeliveryServiceTest {

    @Before
    public void setUp(){
        String inputString = "100 3\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG2 15 5 OFR002\n" +
                "PKG3 10 100 OFR003\n";
        DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
    }

    @Test
    public void testCalculateDeliveryCostService(){
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.calculateDeliveryCost(DeliveryRequest.getInstance());

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
}
