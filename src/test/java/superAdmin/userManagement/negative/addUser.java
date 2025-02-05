package superAdmin.userManagement.negative;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class addUser extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("userManagementTab");
        webSteps.click("clickUserManagement");
        webSteps.click("clickAddUser");
    }

    @DataProvider(name="userData")
    public Object [][] negativeUserDataProvider(){

        String [][] data = {
                {"","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","First name is required."},
                {"Sa","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","First name must be at least 3 characters"},
                {"SamindyaUmayanganiVass","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","First name cannot exceed 15 characters."},
                {"!@#$%^&*","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","First name can only contain letters and numbers. No spaces allowed"},
                {"Samindya","","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","Last name is required."},
                {"Samindya","Um","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","Last name must be at least 3 characters"},
                {"Samindya","SamindyaUmayanganiVass","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","Last name cannot exceed 15 characters."},
                {"Samindya","!@#$%^&*","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","Lastname can only contain letters and numbers. No spaces allowed"},
                {"Samindya","Umayangani","","Strong@12345","Strong@12345","123456789012","123456789012","Email is required"},
                {"Samindya","Umayangani","samindya","Strong@12345","Strong@12345","123456789012","123456789012","Invalid email format"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012","The email has already been taken."},
                {"Samindya","Umayangani","samindya@gmail.com","","","123456789012","123456789012","Password is required"},
                {"Samindya","Umayangani","samindya@gmail.com","pw","pw","123456789012","123456789012","Password must be at least 8 characters"},
                {"Samindya","Umayangani","samindya@gmail.com","abcdefgh","abcdefgh","123456789012","123456789012","Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","","123456789012","123456789012","Confirm password is required"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","password","123456789012","123456789012","Passwords must match"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","","123456789012","123456789012","Confirm password is required"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","","123456789012","Phone number is required"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123","123456789012","Phone number must be at least 12 characters long"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012345","123456789012","Phone number cannot be more than 12 characters long"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","samin!@#%^&*","123456789012","Only numeric values are allowed"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","","Whatsapp number is required"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123","Whatsapp number must be at least 12 characters long"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","123456789012345","Whatsapp number cannot be more than 12 characters long"},
                {"Samindya","Umayangani","samindya@gmail.com","Strong@12345","Strong@12345","123456789012","samin!@#%^&*","Only numeric values are allowed"},
                //3 dropdowns required
                //all empty
                //fill and cancel
                //cancel

        };
        return data;
    }

    @Test(dataProvider ="userData")
    public void addUserWithInvalidData(String firstname,String lastName,String email,String password,String confirmPassword,String phoneNumber,String whatsAppNumber,String expValidation) throws InterruptedException, AWTException {
        //int loop = ThreadLocalRandom.current().nextInt(1, 5);

        webSteps.type(firstname,"firstNameField");
        webSteps.type(lastName,"lastNameField");
        webSteps.type(email,"UMemailField");
        webSteps.type(password,"UMpasswordField");
        webSteps.type(confirmPassword,"UMconfirmPasswordField");
        webSteps.type(phoneNumber,"phoneNumberField");
        webSteps.type(whatsAppNumber,"whatsappNumberField");
        //webSteps.selectRandom("userRoleDropdown",loop,1);
        webSteps.select("userRoleDropdown");
        webSteps.select("userCountryDropdown");
        webSteps.select("userStateDropdown");
        webSteps.scrollToElement("addUserButton");
        webSteps.waiting();
        webSteps.click("addUserButton");


        switch (expValidation) {
            case "First Name is required." -> {
                Assert.assertEquals("First name is required", webSteps.getText("UMfirstNameValidation"), "Passed");
            }
            case "First name must be at least 3 characters" -> {
                Assert.assertEquals("First name must be at least 3 characters", webSteps.getText("UMfirstNameValidation"), "Passed");
            }
            case "First name cannot exceed 15 characters" -> {
                Assert.assertEquals("First name cannot exceed 15 characters", webSteps.getText("UMfirstNameValidation"), "Passed");
            }
            case "First name can only contain letters and numbers. No spaces allowed" -> {
                Assert.assertEquals("First name can only contain letters and numbers. No spaces allowed", webSteps.getText("UMfirstNameValidation"), "Passed");
            }
            case "Last Name is required." -> {
                Assert.assertEquals("Last name is required", webSteps.getText("UMlastNameValidation"), "Passed");
            }
            case "Last name must be at least 3 characters" -> {
                Assert.assertEquals("Last name must be at least 3 characters", webSteps.getText("UMlastNameValidation"), "Passed");
            }
            case "Last name cannot exceed 15 characters" -> {
                Assert.assertEquals("Last name cannot exceed 15 characters", webSteps.getText("UMlastNameValidation"), "Passed");
            }
            case "Last name can only contain letters and numbers. No spaces allowed" -> {
                Assert.assertEquals("Lastname can only contain letters and numbers. No spaces allowed", webSteps.getText("UMlastNameValidation"), "Passed");
            }
            case "Email is requireds" -> {
                Assert.assertEquals("Email is required", webSteps.getText("UMemailValidation"), "Passed");
            }
            case "Invalid email format" -> {
                Assert.assertEquals("Invalid email format", webSteps.getText("UMemailValidation"), "Passed");
            }
            case "The email has already been taken." -> {
                Assert.assertEquals("The email has already been taken.", webSteps.getText("UMduplicateEmail"), "Passed");
            }
            case "Password is required" -> {
                Assert.assertEquals("Password is required", webSteps.getText("UMpasswordValidation"), "Passed");
                Assert.assertEquals("Confirm password is required", webSteps.getText("UMConfirmPasswordValidation"), "Passed");
            }
            case "Password must be at least 8 characters" -> {
                Assert.assertEquals("Password must be at least 8 characters", webSteps.getText("UMpasswordValidation"), "Passed");
            }
            case "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character" -> {
                Assert.assertEquals("Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character", webSteps.getText("UMpasswordValidation"), "Passed");
            }
            case "Confirm password is required" -> {
                Assert.assertEquals("Confirm password is required", webSteps.getText("UMConfirmPasswordValidation"), "Passed");
            }
            case "Passwords must match" -> {
                Assert.assertEquals("Passwords must match", webSteps.getText("UMConfirmPasswordValidation"), "Passed");
            }
            case "Phone number must be at least 12 characters long" -> {
                Assert.assertEquals("Phone number must be at least 12 characters long", webSteps.getText("UMphoneNumberValidation"), "Passed");
            }
            case "Phone number cannot be more than 12 characters long" -> {
                Assert.assertEquals("Phone number cannot be more than 12 characters long", webSteps.getText("UMphoneNumberValidation"), "Passed");
            }
            case "Only numeric values are allowed" -> {
                Assert.assertEquals("Only numeric values are allowed", webSteps.getText("UMphoneNumberValidation"), "Passed");
            }
            case "WhatsApp number is required" -> {
                Assert.assertEquals("WhatsApp number is required", webSteps.getText("UMwhatsAppNumberValidation"), "Passed");
            }
            case "WhatsApp number must be at least 12 characters long" -> {
                Assert.assertEquals("WhatsApp number must be at least 12 characters long", webSteps.getText("UMwhatsAppNumberValidation"), "Passed");
            }
            case "WhatsApp number cannot be more than 12 characters long" -> {
                Assert.assertEquals("WhatsApp number cannot be more than 12 characters long", webSteps.getText("UMwhatsAppNumberValidation"), "Passed");
            }
            case "Only numeric values are allowed." -> {
                Assert.assertEquals("Only numeric values are allowed", webSteps.getText("UMwhatsAppNumberValidation"), "Passed");
            }

        }
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
