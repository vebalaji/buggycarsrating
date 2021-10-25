package org.buggy.rating.tests;

import org.buggy.rating.pages.RegisterPage;
import org.buggy.rating.util.Common;
import org.buggy.rating.util.Registration;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest{
    @Test
    public void validateNewRegistration() throws InterruptedException {
        RegisterPage registerPage = homePage.clickRegister();
        String username = Common.randomString(5); // Generate username of random 5 characters
        Registration registrationDetails = new Registration.Builder()
                .username(username)
                .firstname(prop.getProperty("firstname"))
                .lastname(prop.getProperty("lastname"))
                .password(prop.getProperty("password"))
                .confirmpassword(prop.getProperty("password"))
                .build();
        System.out.println("New user registered = "+username);
        String result = registerPage.newRegistration(registrationDetails);
        Assert.assertTrue(result.equals("Registration is successful"),"Registration is not successfull - Please check!");
        homePage.login(username, prop.getProperty("password"));
        String profilename=homePage.getProfileName();
        Assert.assertTrue(profilename.contentEquals("Hi, "+prop.getProperty("firstname")),"Login not successful post registration - Please check!");
        homePage.logout();
    }
}
