package superAdmin.roleManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class searchRole extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("userManagementTab");
        webSteps.click("clickAddRole");
    }

    @DataProvider(name="searchRoleData")
    public Object [][] searchRoleDataProvider(){

        String [][] data = {
                {"Admin","searchRole"},
                {"ADMIN","searchRoleUpper"},
                {"admin","searchRoleLower"},
                {"AdMiN","searchRoleMixed"},
                {"!@#$%^&*","searchRoleWithSpecialChar"}
        };
        return data;
    }

    @Test(dataProvider = "searchRoleData")
    public void searchRole(String roleName, String expValidation) throws InterruptedException {

        webSteps.type(roleName,"roleSearchField");

        switch (expValidation) {
            case "Admin" -> {
                Assert.assertEquals("Admin", webSteps.getText("roleSearchResult"), "Passed");
            }
            case "No Role Data" -> {
                Assert.assertEquals("No Role Data", webSteps.getText("noRoleData"), "Passed");
            }
        }
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
