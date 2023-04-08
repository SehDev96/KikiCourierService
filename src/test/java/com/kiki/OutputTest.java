package test.java.com.kiki;

import main.java.com.kiki.model.DeliveryOutput;
import main.java.com.kiki.model.DeliverySummary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OutputTest {

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
