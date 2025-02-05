package superAdmin.packages.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class positiveAddAddonGroup extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("packagesTab");
        webSteps.click("clickAddOn");
        webSteps.click("clickAddNewGroup");
    }

    @Test
    public void addAddonGroup() throws InterruptedException {
        webSteps.type(webSteps.generateRandomAddonGroupName(), "groupNameField");
        webSteps.click("addGroupButton");

        Assert.assertEquals("Group created successfully",webSteps.getText("addAddonGroupToastMessage"), "Passed");
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
