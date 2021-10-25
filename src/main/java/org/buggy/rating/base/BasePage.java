package org.buggy.rating.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;

    public BasePage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}
