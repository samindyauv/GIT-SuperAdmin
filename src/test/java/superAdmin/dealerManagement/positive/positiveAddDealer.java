package superAdmin.dealerManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.awt.*;
import java.io.IOException;

public class positiveAddDealer extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("dealerManagementTab");
        webSteps.click("clickAddDealer");
    }

    @Test(testName ="Add Dealer without profile image and logo")
    public void addDealerWithoutFiles() throws InterruptedException, AWTException {
        webSteps.type("Samindya","DMfirstNameField");
        webSteps.type("Umayangani","DMlastNameField");
        webSteps.type(webSteps.generateRandomDealerPersonalEmail(),"DMemailField");
        webSteps.type("12345@Aa","DMpasswordField");
        webSteps.type("12345@Aa","DMconfirmPasswordField");
        webSteps.type("North Western Province, Sri Lanka","DMaddressField");
        webSteps.type("971123456789","DMphoneNumberField");
        webSteps.type("971123456789","DMwhatsappNumberField");
        webSteps.select("DMcountryDropdown");
        webSteps.select2("DMStateDropdown");
        webSteps.type(webSteps.generateRandomCompanyName(),"DMcompanyNameField");
        webSteps.type(webSteps.generateRandomDealerCompanyEmail(),"DMcompanyEmailField");
        webSteps.type("TAX12345","DMtaxNumberField");
        webSteps.type("REG67890","DMregistrationCodeField");
        webSteps.type("Western Province, Sri Lanka","DMcompanyAddressField");
        webSteps.type("123456789012","DMcompanyNumberField");
        webSteps.type("123456789012","DMcompanyWhatsappNumberField");
        webSteps.select2("DMcompanyCountryDropdown");
        webSteps.select("DMcompanyStateDropdown");
        webSteps.scrollToElement("DMaddDealerButton");
        webSteps.click("DMaddDealerButton");
        Assert.assertEquals("Dealer added successfully",webSteps.getText("AddDealerToastMessage"), "Passed");
    }

    @Test(testName ="Add Dealer with profile image and logo")
    public void addDealerWithFiles() throws InterruptedException, AWTException {
        webSteps.type("Samindya","DMfirstNameField");
        webSteps.type("Umayangani","DMlastNameField");
        webSteps.type(webSteps.generateRandomDealerPersonalEmail(),"DMemailField");
        webSteps.type("12345@Aa","DMpasswordField");
        webSteps.type("12345@Aa","DMconfirmPasswordField");
        webSteps.type("North Western Province, Sri Lanka","DMaddressField");
        webSteps.type("971123456789","DMphoneNumberField");
        webSteps.type("971123456789","DMwhatsappNumberField");
        webSteps.select("DMcountryDropdown");
        webSteps.select2("DMStateDropdown");
        //profile Image
        webSteps.type(webSteps.generateRandomCompanyName(),"DMcompanyNameField");
        webSteps.type(webSteps.generateRandomDealerCompanyEmail(),"DMcompanyEmailField");
        webSteps.type("TAX12345","DMtaxNumberField");
        webSteps.type("REG67890","DMregistrationCodeField");
        webSteps.type("Western Province, Sri Lanka","DMcompanyAddressField");
        webSteps.type("123456789012","DMcompanyNumberField");
        webSteps.type("123456789012","DMcompanyWhatsappNumberField");
        webSteps.select2("DMcompanyCountryDropdown");
        webSteps.select("DMcompanyStateDropdown");
        //Company Logo
        //company documents
        webSteps.scrollToElement("DMaddDealerButton");
        webSteps.click("DMaddDealerButton");
        Assert.assertEquals("Dealer added successfully",webSteps.getText("AddDealerToastMessage"), "Passed");
    }

    @AfterMethod
    public void close(){
        tearDown();
    }

}
