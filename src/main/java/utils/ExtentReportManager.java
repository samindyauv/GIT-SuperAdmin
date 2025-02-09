package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORT_PATH = "test-output/ExtentReport.html";
    private static final String SCREENSHOT_PATH = "test-output/screenshots/";
    private static LocalDateTime executionStartTime;
    private static LocalDateTime executionEndTime;
    private static String browserName;

    // Initialize Extent Report (Default Navbar, Dark Mode)
    public static void initReport(String browser) {
        if (extent == null) {
            browserName = browser;
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Execution Report");
            sparkReporter.config().setTheme(Theme.DARK); // Set Default Dark Mode

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Capture Execution Start Time
            executionStartTime = LocalDateTime.now();

            // System Info
            extent.setSystemInfo("Execution Start Time", executionStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            extent.setSystemInfo("Tester", "Samindya Vass");
            extent.setSystemInfo("Environment", "Staging");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", browserName);
        }
    }

    // Start a new test
    public static void startTest(String testName) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized. Call initReport() first.");
        }
        test = extent.createTest(testName);
    }

    // Log Info
    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    // Log Pass
    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
    }

    // Capture Screenshot for Failed Tests
    public static void captureScreenshot(WebDriver driver, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = SCREENSHOT_PATH + result.getName() + ".png";
                FileUtils.copyFile(screenshot, new File(screenshotPath));

                test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/" + result.getName() + ".png").build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Finalize and Open Report
    public static void flushReport() {
        if (extent != null) {
            executionEndTime = LocalDateTime.now();
            Duration executionTime = Duration.between(executionStartTime, executionEndTime);
            String formattedExecutionTime = String.format("%02d min, %02d sec",
                    executionTime.toMinutes(),
                    executionTime.toSecondsPart());

            extent.setSystemInfo("Execution End Time", executionEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            extent.setSystemInfo("Total Execution Time", formattedExecutionTime);
            extent.flush();
            openReport();
        }
    }

    // Automatically Open Report After Execution
    public static void openReport() {
        try {
            File reportFile = new File(REPORT_PATH);
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
