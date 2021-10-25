package org.buggy.rating.tests;

import org.buggy.rating.pages.OverallPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OverallPageTest extends BaseTest{

    @Test(priority=1)
    public void validateImageDisplay(){
        OverallPage overallPage = homePage.clickOverallRating();
        Assert.assertTrue(overallPage.findBrokenImage(),"Image not displayed properly - Please check!");
        overallPage.gotoHomePage();
    }


}
