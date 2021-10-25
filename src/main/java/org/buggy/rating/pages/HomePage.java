package org.buggy.rating.pages;

import org.buggy.rating.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@name='login']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath="//button[contains(text(),'Login')]")
    private WebElement loginBtn;

    @FindBy(xpath="//a[contains(text(),'Logout')]")
    private WebElement logoutBtn;

    @FindBy(xpath="//a[contains(text(),'Profile')]")
    private WebElement profileLink;

    @FindBy(xpath="//span[contains(text(),'Hi')]")
    private WebElement profileName;

    @FindBy(xpath = "//a[contains(@href,\"/overall\")]")
    private WebElement overallRating;

    @FindBy(xpath = "//*[text() = 'Popular Model']/..//h3")
    private WebElement popularModelDetails;

    @FindBy(xpath = "//*[text() = 'Popular Make']/..//h3")
    private WebElement popularMakeDetails;

    @FindBy(xpath = "//a[contains(text(),'Register')]")
    private WebElement registerBtn;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public OverallPage clickOverallRating(){
        overallRating.click();
        return new OverallPage(driver);
    }
    public String getPopularModelText(){
        return popularModelDetails.getText();
    }
    public void login(String usernameVal, String passwordVal){
        username.sendKeys(usernameVal);
        password.sendKeys(passwordVal);
        loginBtn.click();
    }

    public String getProfileName(){
        return profileName.getText();
    }

    public ProfilePage viewProfile(){
        profileLink.click();
        return new ProfilePage(driver);
    }

    public String logout(){
        logoutBtn.click();
        return driver.getTitle();
    }

    public RegisterPage clickRegister(){
        registerBtn.click();
        return new RegisterPage(driver);
    }
}
