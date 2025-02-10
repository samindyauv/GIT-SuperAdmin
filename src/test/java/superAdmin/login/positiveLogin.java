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
        ExtentReportManager.initReport(browser); // Pass the browser name
    }

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        ExtentReportManager.startTest("Positive Login Test"); // Start test in Extent Report
        loadUrl();
        ExtentReportManager.logInfo("📌 Opened the application URL");

        webSteps.login();
        ExtentReportManager.logInfo("🔑 Entered valid credentials and clicked login");
    }

    @Test()
    public void loginWithValidCredentials() throws InterruptedException {
        ExtentReportManager.logInfo("⏳ Verifying login success...");

        boolean urlVerification = driver.getCurrentUrl().contains("dashboard");
        Assert.assertTrue(urlVerification, "Expecting login success but not navigated to dashboard");

        if (urlVerification) {
            ExtentReportManager.logPass("✅ Navigated to dashboard successfully");
        } else {
            ExtentReportManager.logFail("❌ Failed to navigate to dashboard");
        }

        String successMessage = webSteps.getText("successMessage");
        Assert.assertEquals(successMessage, "Successfully logged in", "❌ Unexpected login message");

        if (successMessage.equals("Successfully logged in")) {
            ExtentReportManager.logPass("✅ Login success message verified: " + successMessage);
        } else {
            ExtentReportManager.logFail("❌ Unexpected login success message: " + successMessage);
        }
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.logFail("❌ Test failed: " + result.getThrowable().getMessage());
        } else {
            ExtentReportManager.logPass("✅ Test passed.");
        }

        // Capture screenshot for both passed and failed tests
        ExtentReportManager.captureScreenshot(driver, result);
        tearDown();
    }

    @AfterSuite
    public void finalizeReport() {
        ExtentReportManager.flushReport(); // Ensures the report is generated
        ExtentReportManager.openReport();  // Opens the report automatically
    }
}
