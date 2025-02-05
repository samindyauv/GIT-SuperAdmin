package superAdmin.roleManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class roleFilter extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("userManagementTab");
        webSteps.click("clickRoleManagement");
    }

    @Test(testName = "Filter Roles by Active status")
    public void filterRolesByActiveStatus() throws InterruptedException {
        webSteps.click("roleFilter");
        webSteps.click("roleFilterActive");

        Assert.assertEquals("Active", webSteps.getText("SelectedStatusActive"));
    }

    @Test(testName = "Filter Roles by inactive status")
    public void filterRolesByInactiveStatus() throws InterruptedException {
        webSteps.click("roleFilter");
        webSteps.click("roleFilterInactive");

        Assert.assertEquals("Inactive", webSteps.getText("SelectedStatusInactive"));
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
