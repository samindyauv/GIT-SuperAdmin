package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static dataProviders.repositoryFileReader.constructElement;
import static dataProviders.repositoryFileReader.findElementRepo;

public class webSteps {
    protected static WebDriver driver;
    private final String email;
    private final String password;

    public webSteps(WebDriver driver) {
        webSteps.driver = driver;

        // Load email and password from properties file
        Properties properties = propertyLoader.loadProperties("src/main/resources/dataset.properties");
        this.email = properties.getProperty("email");
        this.password = properties.getProperty("password");
    }

    public void login() throws InterruptedException {
        waiting();
        click("selectSuperAdmin");
        waiting();
        type(email, "emailField");
        type(password, "passwordField");
        click("loginButton");
        waiting();
    }

    // Common method to type text into an input field
    public void type(String text, String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement inputField = driver.findElement(xpath);
        inputField.clear();
        inputField.sendKeys(text);
        waiting();
    }


    // Common method to click an element
    public void click(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement button =  driver.findElement(xpath);
        button.click();
        waiting();
    }

    // Common method to get text from an element
    public String getText(String locator) {
        By xpath = constructElement(findElementRepo(locator));
        return driver.findElement(xpath).getText();
    }

    public void select(String locator) throws InterruptedException, AWTException {
        By xpath = constructElement(findElementRepo(locator));
        click(locator);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        waiting();
    }

    public void select2(String locator) throws InterruptedException, AWTException {
        By xpath = constructElement(findElementRepo(locator));
        click(locator);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        waiting();
    }

    public void selectRandom(String locator, int loop, int bool) throws InterruptedException, AWTException {
        By xpath = constructElement(findElementRepo(locator));
        click(locator);

        Robot robot = new Robot();

        if(bool==1){
            for (int i=1;i<=loop; i++){
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);

                Thread.sleep(100);
            }

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


        }else if(bool==0){
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } else {
            System.out.println("The boolean value is invalid");
        }

        waiting();
    }



    // Method to wait 1000ms
    public void waiting() throws InterruptedException {
        Thread.sleep(500);
    }

    // Method for scroll to given element
    public void scrollToElement(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
        waiting();

    }
    public String generateRandomRoleName() {
        String randomRoleName = "Role" + ThreadLocalRandom.current().nextInt(0, 100);
        return randomRoleName;
    }
    public String generateRandomAddonGroupName() {
        String RandomAddonGroupName = "Addon Group" + ThreadLocalRandom.current().nextInt(0, 100);
        return RandomAddonGroupName;
    }
    public String generateRandomAddonName(){
        String RandomAddonName = "Addon" + ThreadLocalRandom.current().nextInt(0,100);
        return RandomAddonName;
    }
    public String generateRandomUserEmail() {
        String email = "user" + ThreadLocalRandom.current().nextInt(0, 1000) + "@example.com";
        return email;
    }
    public String generateRandomDealerPersonalEmail() {
        String email = "dealer" + ThreadLocalRandom.current().nextInt(0, 1000) + "@example.com";
        return email;
    }
    public String generateRandomCompanyName() {
        String randomCompanyName = "Com" + ThreadLocalRandom.current().nextInt(0, 100);
        return randomCompanyName;
    }
    public String generateRandomDealerCompanyEmail() {
        String email = "company" + ThreadLocalRandom.current().nextInt(0, 1000) + "@example.com";
        return email;
    }


}
