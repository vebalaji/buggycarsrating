package org.buggy.rating.tests;


import org.buggy.rating.pages.OverallPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;


public class HomePageTest extends BaseTest{

    @Test(priority=1)
    public void verifyTitle(){
        Assert.assertTrue(driver.getTitle().equals("Buggy Cars Rating"));
    }

    @Test(priority=2)
    public void validatePopularModel() throws InterruptedException {
        String[] expectedPopularModelDetails;
        String[] actualPopularModelDetails;
        String[] temp;

        actualPopularModelDetails = homePage.getPopularModelText().split(" ");

        OverallPage overallPage = homePage.clickOverallRating();

        expectedPopularModelDetails=overallPage.findPopularModel().split(",");

        overallPage.gotoHomePage();

        for (int i=0;i<actualPopularModelDetails.length;i++){
            if (actualPopularModelDetails[i].contains("(")){
                actualPopularModelDetails[i] = actualPopularModelDetails[i].replace("(","");
            }
        }
        temp = actualPopularModelDetails[1].split("\\s+");
        actualPopularModelDetails= new String[]{actualPopularModelDetails[0], temp[0], temp[1]};
        Assert.assertTrue(Arrays.equals(expectedPopularModelDetails,actualPopularModelDetails),"Popular Model details is incorrect - Please check!");

    }

    @Test(priority=3)
    public void validateLogin() {
        homePage.login(prop.getProperty("username"), prop.getProperty("password"));
        String profilename=homePage.getProfileName();
        Assert.assertTrue(profilename.contentEquals("Hi, "+prop.getProperty("firstname")));
        homePage.logout();
    }
}
