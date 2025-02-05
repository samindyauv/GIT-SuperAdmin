package superAdmin.packages.negative;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;

import java.io.IOException;

public class negativeAddAddonGroup extends baseTest {
    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loadUrl();
        webSteps.login();
        webSteps.click("packagesTab");
        webSteps.click("clickAddOn");
        webSteps.click("clickAddNewGroup");
    }

    @DataProvider(name="addonGroupData")
    public Object [][] negativeGroupDataProvider(){

        String [][] data = {
                {"","addGroupButton","Group name is required."},
                {"!@#$%^&*","addGroupButton","Group Name can only contain letters and numbers."},
                {"AG","addGroupButton","Group Name must be at least 3 characters."},
                {"Testing AddOn Group","addGroupButton","Group name must be between 3 and 15 characters."},
                {"Annual Package","addGroupButton","Group name has already been taken."},
        };
        return data;
    }

    @Test(dataProvider ="addonGroupData")
    public void addGroupWithInvalidData(String groupName,String addGroupButton, String expValidation) throws InterruptedException {
        webSteps.type(groupName,"groupNameField");
        webSteps.click(addGroupButton);

        switch (expValidation) {
            case "Name is required." -> {
                Assert.assertEquals("Name is required.", webSteps.getText("groupNameIsRequired"), "Passed");
            }
            case "Group Name can only contain letters and numbers." -> {
                Assert.assertEquals("Group Name can only contain letters and numbers.", webSteps.getText("groupWithSpecialCharName"), "Passed");
            }
            case "Group Name must be at least 3 characters." -> {
                Assert.assertEquals("Group Name must be at least 3 characters.", webSteps.getText("groupWithShortName"), "Passed");
            }
            case "Group name must be between 3 and 15 characters." -> {
                Assert.assertEquals("Group name must be between 3 and 15 characters.", webSteps.getText("groupWithLongName"), "Passed");
            }
            case "Group Name must be unique." -> {
                Assert.assertEquals("The name has already been taken.", webSteps.getText("duplicateAddOnGroupNameTM"), "Passed");
                Assert.assertEquals("Group create failed", webSteps.getText("duplicateAddOnGroupName"), "Passed");
            }
        }
    }
    @AfterMethod
    public void close(){
        tearDown();
    }
}
