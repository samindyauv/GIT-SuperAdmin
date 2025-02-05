package superAdmin.login;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class positiveLogin extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
    }

    @Test()
    public void loginWithValidCredentials() throws InterruptedException {
        boolean urlVerification = driver.getCurrentUrl().contains("dashboard");
        Assert.assertTrue(urlVerification,"Expecting login success but not navigated to dashboard");

        Assert.assertEquals("Successfully logged in",webSteps.getText("successMessage"), "Passed");
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
