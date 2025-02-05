package superAdmin.userManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class userFilter extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("userManagementTab");
        webSteps.click("clickUserManagement");
        webSteps.click("userFilter");
    }

    @Test(testName = "Filter Users by Active status")
    public void filterUsersByActiveStatus() throws InterruptedException {
        webSteps.click("userFilterActive");

        Assert.assertEquals("Active", webSteps.getText("")); //no status column
    }

    @Test(testName = "Filter Users by Inactive status")
    public void filterUsersByInactiveStatus() throws InterruptedException {
        webSteps.click("userFilterInactive");

        Assert.assertEquals("Inactive", webSteps.getText("")); //no status column
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
