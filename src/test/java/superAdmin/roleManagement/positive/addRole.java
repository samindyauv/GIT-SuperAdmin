package superAdmin.roleManagement.positive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class addRole extends baseTest {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("userManagementTab");
        webSteps.click("clickRoleManagement");
        webSteps.click("clickAddRole");
    }

    @Test(testName = "Add role with Active status")
    public void addActivatedRole() throws InterruptedException {
        webSteps.type(webSteps.generateRandomRoleName(), "RoleNameField");
        webSteps.click("activateRole");
        webSteps.click("userPermissionSA");
        webSteps.scrollToElement("addRoleButton");
        webSteps.click("addRoleButton");

        Assert.assertEquals("Role created successfully",webSteps.getText("AddRoleToastMessage"), "Passed");
    }

    @Test(testName = "Add role with Deactive status")
    public void addDeactivatedRole() throws InterruptedException {
        webSteps.type(webSteps.generateRandomRoleName(), "RoleNameField");
        webSteps.click("deactivateRole");
        webSteps.click("userPermissionSA");
        webSteps.scrollToElement("addRoleButton");
        webSteps.click("addRoleButton");

        Assert.assertEquals("Role created successfully",webSteps.getText("AddRoleToastMessage"), "Passed");
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}
