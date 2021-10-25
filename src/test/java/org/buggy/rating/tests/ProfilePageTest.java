package org.buggy.rating.tests;

import org.buggy.rating.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfilePageTest extends BaseTest{
    @Test(priority=1)
    public void verifyInformationUpdate(){
        homePage.login(prop.getProperty("username"), prop.getProperty("password"));
        ProfilePage profilePage = homePage.viewProfile();

        Assert.assertTrue(profilePage.updateBasicInfo(prop.getProperty("firstname"),prop.getProperty("lastname")).equals("The profile has been saved successful"),"Profile Update failed - Please check!");
        Assert.assertTrue(profilePage.updateAdditionalInfo(prop.getProperty("gender"),prop.getProperty("age"),prop.getProperty("address"),prop.getProperty("phone"),prop.getProperty("hobby")).equals("The profile has been saved successful"),"Profile Update failed - Please check!");

        profilePage.clickCancel();
        homePage.viewProfile();
        Assert.assertTrue(profilePage.getFirstName().equals(prop.getProperty("firstname")),"FirstName is not updated - Please Check!");
        Assert.assertTrue(profilePage.getLastName().equals(prop.getProperty("lastname")),"LastName is not updated - Please Check!");
        Assert.assertTrue(profilePage.getGender().equals(prop.getProperty("gender")),"Gender is not updated - Please Check!");
        Assert.assertTrue(profilePage.getAge().equals(prop.getProperty("age")),"Age is not updated - Please Check!");
        Assert.assertTrue(profilePage.getAddress().equals(prop.getProperty("address")),"Address is not updated - Please Check!");
        Assert.assertTrue(profilePage.getPhone().equals(prop.getProperty("phone")),"Phone is not updated - Please Check!");
        Assert.assertTrue(profilePage.getHobby().equals(prop.getProperty("hobby")),"Hobby is not updated - Please Check!");
        profilePage.clickCancel();
        homePage.logout();
    }
}
