package superAdmin.userManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import java.awt.*;
import java.io.IOException;

public class addUser extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
    loadUrl();
    webSteps.login();
    webSteps.click("userManagementTab");
    webSteps.click("clickUserManagement");
    webSteps.click("clickAddUser");
    }

    @Test()
    public void addUserWithValidData() throws InterruptedException, AWTException {

        webSteps.type("samindya","firstNameField");
        webSteps.type("umayangani","lastNameField");
        webSteps.type(webSteps.generateRandomUserEmail(),"UMemailField");
        webSteps.type("Strongpassword@123","UMpasswordField");
        webSteps.type("Strongpassword@123","UMconfirmPasswordField");
        webSteps.type("123456789012","phoneNumberField");
        webSteps.type("123456789012","whatsappNumberField");
        webSteps.select("userRoleDropdown");
        webSteps.select("userCountryDropdown");
        webSteps.select("userStateDropdown");
        //profileImage
        webSteps.scrollToElement("addUserButton");
        webSteps.click("addUserButton");
        Assert.assertEquals("User added successfully",webSteps.getText("AddUserToastMessage"), "Passed");
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
