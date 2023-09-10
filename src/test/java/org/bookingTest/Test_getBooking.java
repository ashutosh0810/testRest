package org.bookingTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.booking.Util;
import org.codehaus.groovy.transform.stc.ExtensionMethodNode;
import org.testng.Assert;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@Listeners(TestListeners.class)
public class Test_getBooking extends BaseTest {
    Response response;

    @Test(description = " To validate the status code of get all booking ")
    public void tc01_getAllbookingId() throws IOException {
        ExtentTestManager.getTest().info(" Get all booking body is");

        response = HttpsMethods.get(Util.readConfig("path"), "");
       // log.info(" response is " + response.getBody().asPrettyString());
        ExtentTestManager.getTest().info(response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test(description = " To validate the status code of get specific booking ")
    public void tc02_getbookingId() throws IOException {
        log.info("path is ");
        ExtentTestManager.getTest().info("  tc02_getbookingId body is");
        response = HttpsMethods.get(Util.readConfig("path"), String.valueOf(Commons.bookingid));
        ExtentTestManager.getTest().info(" tc02_getbookingId" + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test(description = " To validate the  404 NOT FOUND status code for wrong booking ")
    public void tc02_Neg_getbookingId() throws IOException {
        ExtentTestManager.getTest().info(" tc02_Neg_getbookingId");
        response = HttpsMethods.get(Util.readConfig("path"), "drew");
        ExtentTestManager.getTest().info(" Body is " + response.getBody().asPrettyString());

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }
}
