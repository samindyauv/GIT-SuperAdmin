package superAdmin.packages.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.awt.*;
import java.io.IOException;

public class positiveAddAddon extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("packagesTab");
        webSteps.click("clickAddOn");
        webSteps.click("clickAddNewAddon");
    }

    @Test(testName = "Add Addon with Active status")
    public void addAddonWithActiveStatus() throws InterruptedException, AWTException {
        webSteps.type(webSteps.generateRandomAddonName(), "addonNameField");
        webSteps.select("addonGroupTypeDropdown");
        webSteps.click("addonActivateRadio");
        webSteps.type("Addon Sample","addonDescription");
        webSteps.select("pricingMethodDropdown");
        webSteps.click("addAddonButton");
        Assert.assertEquals("Addon created successfully",webSteps.getText("addAddonToastMessage"), "Passed");
    }

    @Test(testName = "Add Addon with Deactivate status")
    public void addAddonWithDeactiveStatus() throws InterruptedException, AWTException {
        webSteps.type(webSteps.generateRandomAddonName(), "addonNameField");
        webSteps.select("addonGroupTypeDropdown");
        webSteps.click("addonDeactivateRadio");
        webSteps.type("Addon Sample","addonDescription");
        webSteps.select("pricingMethodDropdown");
        webSteps.click("addAddonButton");
        Assert.assertEquals("Addon created successfully",webSteps.getText("addAddonToastMessage"), "Passed");
    }

    @Test
    public void addAddon2() throws InterruptedException, AWTException {
        webSteps.type(webSteps.generateRandomAddonName(), "addonNameField");
        webSteps.select("addonGroupTypeDropdown");
        webSteps.click("addonActivateRadio");
        webSteps.type("Addon Sample","addonDescription");
        webSteps.select2("pricingMethodDropdown");
        webSteps.click("addAddonButton");
        Assert.assertEquals("Addon created successfully",webSteps.getText("addAddonToastMessage"), "Passed");
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
