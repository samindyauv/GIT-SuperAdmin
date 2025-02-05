package superAdmin.dealerManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.awt.*;
import java.io.IOException;

public class dealerFilter extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException, AWTException {
        loadUrl();
        webSteps.login();
        webSteps.click("dealerManagementTab");
        webSteps.select2("DMdealerFilter");
    }

    @Test(testName = "Filter Dealers by Active status")
    public void filterDealersByActiveStatus() throws InterruptedException, AWTException {
        //webSteps.select2("DMdealerFilter");
        webSteps.waiting();
        webSteps.click("DMdealerFilterActive");

        Assert.assertEquals("Active", webSteps.getText("DMdealerSelectedStatusActive"));
    }

    @Test(testName = "Filter Dealers by Inactive status")
    public void filterDealersByInactiveStatus() throws InterruptedException {
        //webSteps.click("DMdealerFilter");
        webSteps.click("DMdealerFilterInactive");

        Assert.assertEquals("Inactive", webSteps.getText("DMdealerSelectedStatusInactive"));
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
