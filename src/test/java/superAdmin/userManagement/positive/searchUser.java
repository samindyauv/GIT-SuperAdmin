package superAdmin.userManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class searchUser extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("userManagementTab");
        webSteps.click("clickUserManagement");
    }

    @DataProvider(name="searchUserData")
    public Object [][] searchUserDataProvider(){

        String [][] data = {
                {"Samindya","Search user"},
                {"SAMINDYA","Search user with uppercase"},
                {"samindya","Search user with lowercase"},
                {"sAmInDYa","Search user with mixcased"},
                {"!@#$%^&*","Search user with special characters"}
        };
        return data;
    }

    @Test(dataProvider = "searchUserData")
    public void searchUser(String userName, String expValidation) throws InterruptedException {

        webSteps.type(userName,"userSearchField");

        switch (expValidation) {
            case "Search" -> {
                Assert.assertEquals("Samindya", webSteps.getText("userSearchResult"), "Passed"); //set xPath/full name issue
            }
            case "No Role Data" -> {
                Assert.assertEquals("No User Data", webSteps.getText("noUserData"), "Passed"); //set xPath/full name issue
            }
        }
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
