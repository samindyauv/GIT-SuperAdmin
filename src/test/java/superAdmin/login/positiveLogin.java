package superAdmin.login;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportManager;
import utils.baseTest;

import java.io.IOException;

public class positiveLogin extends baseTest {

    @BeforeSuite
    public void setupReport() {
        String browser = "Chrome"; // Change this dynamically if needed
        ExtentReportManager.initReport(browser); // Pass the browser name as an argument
    }

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        ExtentReportManager.startTest("Positive Login Test"); //new
        loadUrl();
        webSteps.login();
        ExtentReportManager.logInfo("Navigated to login page and performed login"); //new
    }

    @Test()
    public void loginWithValidCredentials() throws InterruptedException {
        boolean urlVerification = driver.getCurrentUrl().contains("dashboard");
        Assert.assertTrue(urlVerification, "Expecting login success but not navigated to dashboard");
        ExtentReportManager.logPass("Navigated to dashboard successfully"); //new

        Assert.assertEquals("Successfully logged in", webSteps.getText("successMessage"), "Passed");
        ExtentReportManager.logPass("Login success message verified"); //new
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        ExtentReportManager.captureScreenshot(driver, result);
        tearDown();
    }

    @AfterSuite
    public void finalizeReport() {
        ExtentReportManager.flushReport(); // Ensures the report is generated
        ExtentReportManager.openReport();  // Opens the report automatically
    }
}
