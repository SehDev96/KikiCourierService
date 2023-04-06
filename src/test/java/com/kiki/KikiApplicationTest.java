package test.java.com.kiki;

import main.java.com.kiki.model.DeliveryOutput;
import main.java.com.kiki.model.DeliveryRequest;
import main.java.com.kiki.model.DeliverySummary;
import main.java.com.kiki.model.PackageDetails;
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
