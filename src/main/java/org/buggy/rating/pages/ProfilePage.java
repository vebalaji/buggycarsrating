package org.buggy.rating.pages;

import org.buggy.rating.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='gender']")
    private WebElement gender;

    @FindBy(xpath = "//input[@id='age']")
    private WebElement age;

    @FindBy(xpath = "//textarea[@id='address']")
    private WebElement address;

    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phone;

    @FindBy(xpath = "//select[@id='hobby']")
    private WebElement hobby;

    @FindBy(xpath = "//input[@id='currentPassword']")
    private WebElement currentPassword;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPassword;

    @FindBy(xpath = "//input[@id='newPasswordConfirmation']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement saveBtn;

    @FindBy(xpath = "//a[contains(text(),'Cancel')]")
    private WebElement cancelBtn;

    @FindBy(xpath = "//div[contains(@class,'result alert')]")
    private WebElement result;



    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getFirstName(){
        return firstName.getAttribute("value");
    }

    public String getLastName(){
        return lastName.getAttribute("value");
    }

    public String getGender(){
        return gender.getAttribute("value");
    }

    public String getAge(){
        return age.getAttribute("value");
    }

    public String getAddress(){
        return address.getAttribute("value");
    }

    public String getPhone(){
        return phone.getAttribute("value");
    }

    public String getHobby(){
        return hobby.getAttribute("value");
    }

    public String updateBasicInfo(String firstNameVal, String lastNameVal){
        firstName.clear();
        firstName.sendKeys(firstNameVal);
        lastName.clear();
        lastName.sendKeys(lastNameVal);
        saveBtn.click();
        return result.getText();
    }

    public String updateAdditionalInfo(String genderVal, String ageVal,String addressVal, String phoneVal, String hobbyVal ){
        gender.clear();
        gender.sendKeys(genderVal);
        age.clear();
        age.sendKeys(ageVal);
        address.clear();
        address.sendKeys(addressVal);
        phone.clear();
        phone.sendKeys(phoneVal);
        Select hobbyDropdown = new Select(hobby);
        hobbyDropdown.selectByVisibleText(hobbyVal);
        saveBtn.click();
        return result.getText();
    }

    public void clickCancel(){
        cancelBtn.click();
    }
}
