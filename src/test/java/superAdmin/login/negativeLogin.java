package superAdmin.login;

import jdk.jfr.Name;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class negativeLogin extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.click("selectSuperAdmin");
    }

    @DataProvider(name="loginData")
    public Object [][] negativeLoginDataProvider(){

        String [][] data = {
                {"superadmin@gmail.com","invalidPassword","Invalid"}, //valid email and invalid password
                {"superadmin@gmail.com","","Password is required"}, //valid email and empty password
                {"invalidemail@gmail.com","12345@Aa","Invalid"}, //invalid email and valid password
                {"invalidemail@gmail.com","","Invalid"}, //invalid email and empty password
                {"invalidemail@gmail.com","invalidPassword","Invalid"}, //invalid email and invalid password
                {"invalidemail@","12345@Aa","Invalid email format"}, //invalid email format and valid password
                {"invalidemail@","","Invalid email format and Password is required"}, //invalid email format and empty password
                {"invalidemail@","invalidPassword","Invalid"}, //invalid email format and invalid password
                {"","12345@Aa","Email is required"}, //empty email and valid password
                {"","invalidPassword","Invalid"}, //empty email and invalid password
                {"","","Email and Password are required"} //empty email and empty password
        };
        return data;
    }

    @Test(dataProvider = "loginData")
    @Name("Negative Login Test Cases")
    public void loginWithInvalidCredentials(String email, String password, String expValidation) throws InterruptedException {

        webSteps.type(email,"emailField");
        webSteps.type(password,"passwordField");
        webSteps.click("loginButton");

        switch (expValidation) {
            case "Email is required" -> {
                Assert.assertEquals("Email is required", webSteps.getText("emailIsRequired"), "Passed");
            }
            case "Password is required" -> {
                Assert.assertEquals("Password is required", webSteps.getText("passwordIsRequired"), "Passed");
            }
            case "Email and Password are required" -> {
                Assert.assertEquals("Email is required", webSteps.getText("emailIsRequired"), "Passed");
                Assert.assertEquals("Password is required", webSteps.getText("passwordIsRequired"), "Passed");
            }
            case "Invalid email format" -> {
                Assert.assertEquals("Invalid email format", webSteps.getText("invalidEmailFormat"), "Passed");
            }
            case "Invalid email format and Password is required" -> {
                Assert.assertEquals("Invalid email format", webSteps.getText("invalidEmailFormat"), "Passed");
                Assert.assertEquals("Password is required", webSteps.getText("passwordIsRequired"), "Passed");
            }
        }
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
