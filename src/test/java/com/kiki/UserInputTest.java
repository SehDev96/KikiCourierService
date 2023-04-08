package test.java.com.kiki;

import main.java.com.kiki.model.DeliveryRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class UserInputTest {

    @Test
    public void testUserInput(){
        String inputString = "100 3\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG2 15 5 OFR002\n" +
                "PKG3 10 100 OFR003\n";

        DeliveryRequest deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString);
        assertEquals(100,deliveryRequest.getBaseDeliveryCost());
        assertEquals(3,deliveryRequest.getNumberOfPackages());
        assertEquals("PKG1",deliveryRequest.getPackageDetailsList().get(0).getPackageId());
        assertEquals(5,deliveryRequest.getPackageDetailsList().get(0).getPackageWeight());
        assertEquals(5,deliveryRequest.getPackageDetailsList().get(0).getPackageDistance());
        assertEquals("OFR001",deliveryRequest.getPackageDetailsList().get(0).getCouponCode());

        assertEquals("PKG3",deliveryRequest.getPackageDetailsList().get(2).getPackageId());
        assertEquals(10,deliveryRequest.getPackageDetailsList().get(2).getPackageWeight());
        assertEquals(100,deliveryRequest.getPackageDetailsList().get(2).getPackageDistance());
        assertEquals("OFR003",deliveryRequest.getPackageDetailsList().get(2).getCouponCode());

        String inputStringWithVehicleDetails = "100 5\n" +
                "PKG1 50 30 OFR001\n" +
                "PKG2 75 125 OFR008\n" +
                "PKG3 175 100 OFR003\n" +
                "PKG4 110 60 OFR002\n" +
                "PKG5 155 95 NA\n" +
                "2 70 200";
        deliveryRequest = DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputStringWithVehicleDetails);
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
    public void testUserInputExceptionHandling(){

        // Exception test for empty input
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> DeliveryRequest.getInstance().parseDeliveryRequestFromString(""));
        String expectedMessage = "Input is empty. Please enter delivery request according to the standard input format";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        // Exception test for delivery parameter is less than 2 integer
        String inputString = "100\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG1 15 5 OFR002\n";
        exception = assertThrows(IllegalArgumentException.class, () -> DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString));
        expectedMessage = "Invalid Input. Delivery parameters not entered in proper format.";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        // Exception test for package details is empty or higher than 4 elements
        String inputString2 = "100 2\n" +
                "PKG1 5 5 OFR001 123\n" +
                "PKG1 15 5 OFR002\n";
        exception = assertThrows(IllegalArgumentException.class, () -> DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString2));
        expectedMessage = "Invalid Input. Package details not entered in proper format";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        // Exception test to ensure uniqueness of package id
        String inputString3 = "100 2\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG1 15 5 OFR002\n";
        exception = assertThrows(IllegalArgumentException.class, () -> DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString3));
        expectedMessage = "Duplicated package id found. Please make sure package id's are unique";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        // Exception package number does not match package details given
        String inputString4 = "100 5\n" +
                "PKG1 5 5 OFR001\n" +
                "PKG1 15 5 OFR002\n";
        exception = assertThrows(IllegalArgumentException.class, () -> DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString4));
        expectedMessage = "Duplicated package id found. Please make sure package id's are unique";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        // Exception to check number parsing
        String inputString5 = "100 2\n" +
                "PKG1 5 asd OFR001\n" +
                "PKG1 15 5 OFR002\n";
        exception = assertThrows(IllegalArgumentException.class, () -> DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString5));
        expectedMessage = "For input string:";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        // Exception to ensure at least 2 lines of input is given
        String inputString6 = "100 2\n";
        exception = assertThrows(IllegalArgumentException.class, () -> DeliveryRequest.getInstance().parseDeliveryRequestFromString(inputString6));
        expectedMessage = "Invalid input. Input needs to have at least two lines.";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}
