package org.buggy.rating.pages;

import org.buggy.rating.base.BasePage;
import org.buggy.rating.util.Registration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userName;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[contains(text(),'Register')]")
    private WebElement registerBtn;

    @FindBy(xpath = "//div[contains(@class,'result alert')]")
    private WebElement result;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public String newRegistration(Registration registrationDetails) {
        userName.clear();
        userName.sendKeys(registrationDetails.username);
        firstName.clear();
        firstName.sendKeys(registrationDetails.firstname);
        lastName.clear();
        lastName.sendKeys(registrationDetails.lastname);
        password.clear();
        password.sendKeys(registrationDetails.password);
        confirmPassword.clear();
        confirmPassword.sendKeys(registrationDetails.confirmpassword);
        registerBtn.click();
        return result.getText();

    }
}
