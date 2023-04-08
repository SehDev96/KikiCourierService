package test.java.com.kiki;

import main.java.com.kiki.model.DeliveryRequest;
import main.java.com.kiki.model.PackageDetails;
import main.java.com.kiki.model.Shipment;
import main.java.com.kiki.model.Vehicle;
import main.java.com.kiki.service.ShipmentManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShipManagerTest {

    @Before
    public void setUp(){
        String inputString = "100 5\n" +
                "PKG1 50 30 OFR001\n" +
                "PKG2 75 125 OFR008\n" +
                "PKG3 175 100 OFR003\n" +
                "PKG4 110 60 OFR002\n" +
                "PKG5 155 95 NA\n" +
                "2 70 200";
        DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
    }

    @Test
    public void testSortPackagesIntoShipments(){
        ShipmentManager shipmentManager = new ShipmentManager();
        List<Shipment> actualShipmentList = shipmentManager.sortPackagesIntoShipments(DeliveryRequest.getInstance().getPackageDetailsList());

        List<PackageDetails> packageDetailsList = DeliveryRequest.getInstance().getPackageDetailsList();
        Shipment shipment1 = new Shipment(List.of(packageDetailsList.get(1), packageDetailsList.get(3)));
        Shipment shipment2 = new Shipment(List.of(packageDetailsList.get(2)));
        Shipment shipment3 = new Shipment(List.of(packageDetailsList.get(4)));
        Shipment shipment4 = new Shipment(List.of(packageDetailsList.get(0)));
        List<Shipment> expectedShipmentList = new ArrayList<>(List.of(shipment1, shipment2, shipment3, shipment4));

        assertEquals(expectedShipmentList.get(0).getPackages().get(0).getPackageId(), actualShipmentList.get(0).getPackages().get(0).getPackageId());
        assertEquals(expectedShipmentList.get(0).getPackages().get(1).getPackageId(), actualShipmentList.get(0).getPackages().get(1).getPackageId());
        assertEquals(expectedShipmentList.get(1).getPackages().get(0).getPackageId(), actualShipmentList.get(1).getPackages().get(0).getPackageId());
        assertEquals(expectedShipmentList.get(2).getPackages().get(0).getPackageId(), actualShipmentList.get(2).getPackages().get(0).getPackageId());
        assertEquals(expectedShipmentList.get(3).getPackages().get(0).getPackageId(), actualShipmentList.get(3).getPackages().get(0).getPackageId());
    }
}
