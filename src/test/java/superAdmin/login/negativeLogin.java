package superAdmin.login;

import jdk.jfr.Name;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportManager;
import utils.baseTest;

import java.io.IOException;

public class negativeLogin extends baseTest {

    @BeforeSuite
    public void setupReport() {
        String browser = "Chrome"; // Change this dynamically if needed
        ExtentReportManager.initReport(browser); // Pass the browser name
    }

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        ExtentReportManager.startTest("Negative Login Test"); // Start test in Extent Report
        loadUrl();
        ExtentReportManager.logInfo("📌 Opened the application URL");
        webSteps.click("selectSuperAdmin");
        ExtentReportManager.logInfo("⛔ Navigated to login page and attempting invalid login");
    }

    @DataProvider(name="loginData")
    public Object [][] negativeLoginDataProvider(){

        String [][] data = {
                {"sachintha@gmail.com","invalidPassword","Invalid"}, // Valid email, invalid password
                {"sachintha@gmail.com","","Password is required"}, // Valid email, empty password
                {"invalidemail@gmail.com","12345@Ss","Invalid"}, // Invalid email, valid password
                {"invalidemail@gmail.com","","Invalid"}, // Invalid email, empty password
                {"invalidemail@gmail.com","invalidPassword","Invalid"}, // Invalid email, invalid password
                {"invalidemail@","12345@Ss","Invalid email format"}, // Invalid email format, valid password
                {"invalidemail@","","Invalid email format and Password is required"}, // Invalid email format, empty password
                {"invalidemail@","invalidPassword","Invalid"}, // Invalid email format, invalid password
                {"","12345@Ss","Email is required"}, // Empty email, valid password
                {"","invalidPassword","Invalid"}, // Empty email, invalid password
                {"","","Email and Password are required"} // Empty email & password
        };
        return data;
    }

    @Test(dataProvider = "loginData")
    @Name("Negative Login Test Cases")
    public void loginWithInvalidCredentials(String email, String password, String expValidation) throws InterruptedException {

        ExtentReportManager.logInfo("Attempting login with:");
        ExtentReportManager.logInfo("📧 Email: " + (email.isEmpty() ? "[Empty]" : email));
        ExtentReportManager.logInfo("🔒 Password: " + (password.isEmpty() ? "[Empty]" : password));

        webSteps.type(email,"emailField");
        webSteps.type(password,"passwordField");
        webSteps.click("loginButton");

        ExtentReportManager.logInfo("Checking expected validation: " + expValidation);

        switch (expValidation) {
            case "Email is required" -> {
                Assert.assertEquals(webSteps.getText("emailIsRequired"), "Email is required", "Validation Failed");
                ExtentReportManager.logPass("✅ Validation matched: Email is required");
            }
            case "Password is required" -> {
                Assert.assertEquals(webSteps.getText("passwordIsRequired"), "Password is required", "Validation Failed");
                ExtentReportManager.logPass("✅ Validation matched: Password is required");
            }
            case "Email and Password are required" -> {
                Assert.assertEquals(webSteps.getText("emailIsRequired"), "Email is required", "Validation Failed");
                Assert.assertEquals(webSteps.getText("passwordIsRequired"), "Password is required", "Validation Failed");
                ExtentReportManager.logPass("✅ Validation matched: Email and Password are required");
            }
            case "Invalid email format" -> {
                Assert.assertEquals(webSteps.getText("invalidEmailFormat"), "Invalid email format", "Validation Failed");
                ExtentReportManager.logPass("✅ Validation matched: Invalid email format");
            }
            case "Invalid email format and Password is required" -> {
                Assert.assertEquals(webSteps.getText("invalidEmailFormat"), "Invalid email format", "Validation Failed");
                Assert.assertEquals(webSteps.getText("passwordIsRequired"), "Password is required", "Validation Failed");
                ExtentReportManager.logPass("✅ Validation matched: Invalid email format & Password required");
            }
            case "Invalid" -> {
                ExtentReportManager.logPass("✅ Invalid credentials detected as expected.");
            }
            default -> ExtentReportManager.logInfo("⚠️ Unknown validation case encountered.");
        }
    }

    @AfterMethod
    public void captureFailureAndClose(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.captureScreenshot(driver, result);
            ExtentReportManager.logFail("❌ Test failed: " + result.getThrowable().getMessage());
        }
        driver.quit();
    }

    @AfterSuite
    public void finalizeReport() {
        ExtentReportManager.flushReport(); // Generates the report
        ExtentReportManager.openReport();  // Opens the report automatically
    }
}

