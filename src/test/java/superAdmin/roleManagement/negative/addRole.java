package superAdmin.roleManagement.negative;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class addRole extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("userManagementTab");
        webSteps.click("clickAddRole");
    }

    @DataProvider(name="roleData")
    public Object [][] negativeRoleDataProvider(){

        String [][] data = {
                {"","activateRole","userPermissionSA","addRoleButton","emptyRoleName"},
                {"!@#$%^&*","activateRole","userPermissionSA","addRoleButton","addRoleWithSpecialCharName"},
                {"QA","deactivateRole","userPermissionSA","addRoleButton","addRoleWithShortName"},
                {"QualityAssuranceEngineer","activateRole","userPermissionSA","addRoleButton","addRoleWithLongName"},
                {"Admin","activateRole","userPermissionSA","addRoleButton","checkForDuplicateRoleName"},
        };
        return data;
    }

    @Test(dataProvider ="roleData")
    public void addRoleWithEmptyName(String roleName, String roleStatus,String rolePermission,String addRoleButton, String expValidation) throws InterruptedException {
        webSteps.type(roleName,"RoleNameField");
        webSteps.click(roleStatus);
        webSteps.click(rolePermission);
        webSteps.scrollToElement(addRoleButton);
        webSteps.click(addRoleButton);

        switch (expValidation) {
            case "Role Name is required" -> {
                Assert.assertEquals("Role Name is required", webSteps.getText("roleNameRequired"), "Passed");
            }
            case "Role Name can only contain letters and numbers." -> {
                Assert.assertEquals("Role Name can only contain letters and numbers.", webSteps.getText("roleWithSpecialCharName"), "Passed");
            }
            case "Role Name must be at least 3 characters" -> {
                Assert.assertEquals("Role Name must be at least 3 characters", webSteps.getText("roleWithShortName"), "Passed");
            }
            case "Role name must be between 3 and 15 characters." -> {
                Assert.assertEquals("Role name must be between 3 and 15 characters.", webSteps.getText("roleWithLongName"), "Passed");
            }
            case "Role Name must be unique." -> {
                Assert.assertEquals("Role Name must be unique.", webSteps.getText("duplicateRoleName"), "Passed");
            }
        }
    }

    @Test()
    public void addRoleWithNoPermission() throws InterruptedException {
        webSteps.type(webSteps.generateRandomRoleName(), "RoleNameField");
        webSteps.click("deactivateRole");
        webSteps.scrollToElement("addRoleButton");
        webSteps.click("addRoleButton");

        Assert.assertEquals("At least one permission must be selected.",webSteps.getText("rolePermissionRequired"), "Passed");
        System.out.println("Test passed: Actual and expected messages match!");
    }

    @AfterMethod
    public void close(){
        tearDown();
    }
}


