package superAdmin.packages.negative;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;

import java.awt.*;
import java.io.IOException;

public class negativeAddAddon extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("packagesTab");
        webSteps.click("clickAddOn");
        webSteps.click("clickAddNewAddon");
    }

    @DataProvider(name="addonData")
    public Object [][] negativeAddonDataProvider(){

        String [][] data = {
                {"","addonGroupTypeDropdown","addonActivateRadio","This addon created for testing purpose.","pricingMethodDropdown","Name is required."},
                {"Ad","addonGroupTypeDropdown","addonDeactivateRadio","This addon created for testing purpose.","pricingMethodDropdown","Addon name must be at least 3 characters"},
                {"TestingAddonName","addonGroupTypeDropdown","addonActivateRadio","This addon created for testing purpose.","pricingMethodDropdown","Addon name cannot exceed 15 characters."},
                {"!@#$%^&*","addonGroupTypeDropdown","addonDeactivateRadio","This addon created for testing purpose.","pricingMethodDropdown","Add-on Name cannot contain special characters."},
                {"IOS","addonGroupTypeDropdown","addonActivateRadio","This addon created for testing purpose.","pricingMethodDropdown","The name has already been taken."},
                {"Testing Addon","addonGroupTypeDropdown","addonActivateRadio","","pricingMethodDropdown","Addon Description is required."},
                {"Testing Addon","addonGroupTypeDropdown","addonDeactivateRadio","This addon created for testing purpose. This addon created for testing purpose.","pricingMethodDropdown","Description only contain 50 characters."},
                {"Testing Addon","","addonActivateRadio","This addon created for testing purpose.","pricingMethodDropdown","Group Type is required."},
                {"Testing Addon","addonGroupTypeDropdown","addonActivateRadio","This addon created for testing purpose.","","Pricing Method is required."},
//                {"","","addonActivateRadio","","","All fields required"},
                //all fill and cancel
                //cancel
        };
        return data;
    }

    @Test(dataProvider ="addonData")
    public void addAddonWithInvalidData(String addonName,String addonGroupTypeDropdown,String addonStatus, String addonDescription,String pricingMethodDropdown,String expValidation) throws InterruptedException, AWTException {

        webSteps.type(addonName,"addonNameField");
        if(! addonGroupTypeDropdown.isEmpty()){
            webSteps.select(addonGroupTypeDropdown);
        }
        webSteps.click(addonStatus);
        webSteps.type(addonDescription,"addonDescription");
        if(! pricingMethodDropdown.isEmpty()){
            webSteps.select(pricingMethodDropdown);
        }
        webSteps.scrollToElement("addAddonButton");
        webSteps.waiting();
        webSteps.click("addAddonButton");


        switch (expValidation) {
            case "Name is required." -> {
                Assert.assertEquals("Name is required.", webSteps.getText("addonNameValidation"), "Passed");
            }
            case "Addon name must be at least 3 characters" -> {
                Assert.assertEquals("Addon name must be at least 3 characters", webSteps.getText("addonNameValidation"), "Passed");
            }
            case "Addon name cannot exceed 15 characters." -> {
                Assert.assertEquals("Addon name cannot exceed 15 characters.", webSteps.getText("addonNameValidation"), "Passed");
            }
            case "Add-on Name cannot contain special characters." -> {
                Assert.assertEquals("Add-on Name cannot contain special characters.", webSteps.getText("addonNameValidation"), "Passed");
            }
            case "The name has already been taken." -> {
                Assert.assertEquals("The name has already been taken.", webSteps.getText("addonNameValidation"), "Passed");
                Assert.assertEquals("Addon create failed", webSteps.getText("addAddonToastMessage"), "Passed");
            }
            case "Addon Description is required." -> {
                Assert.assertEquals("Description is required.", webSteps.getText("addonDescriptionValidation"), "Passed");
            }
            case "Description only contain 50 characters." -> {
                Assert.assertEquals("Description only contain 50 characters.", webSteps.getText("addonDescriptionValidation"), "Passed");
            }
            case "Group Type is required." -> {
                Assert.assertEquals("Group Type is required.", webSteps.getText("addonGroupTypeValidation"), "Passed");
            }
            case "Pricing Method is required." -> {
                Assert.assertEquals("Pricing Method is required.", webSteps.getText("addonPricingMethodValidation"), "Passed");
            }

            case "All fields required" -> {
                Assert.assertEquals("Name is required.", webSteps.getText("addonNameValidation"), "Passed");
                Assert.assertEquals("Group Type is required.", webSteps.getText("addonGroupTypeValidation"), "Passed");
                Assert.assertEquals("Description is required.", webSteps.getText("addonDescriptionValidation"), "Passed");
                Assert.assertEquals("Pricing Method is required.", webSteps.getText("addonPricingMethodValidation"), "Passed");
            }
        }
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
