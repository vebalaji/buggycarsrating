package org.buggy.rating.pages;

import org.buggy.rating.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModelPage extends BasePage {
    @FindBy(xpath = "//button[contains(text(),'Vote!')]")
    private WebElement voteBtn;

    @FindBy(xpath = "//h4[contains(text(),'Votes:')]")
    private WebElement votesCount;

    @FindBy(xpath = "//textarea[@id='comment']")
    private WebElement comment;

    @FindBy(xpath = "//table[@class=\"table\"]")
    private WebElement commentTable;

    @FindBy(xpath = "//p[contains(text(),'Thank you for your vote!')]")
    public WebElement thanksForYourVoteText;

    public ModelPage(WebDriver driver) {
        super(driver);
    }

    public String commentAndVoteForAModel(String commentText) throws InterruptedException {
        String[] tmp,tmp1;
        String commentDate,author,commentVal;
        tmp = votesCount.getText().split(":");
        int preVoteCount = Integer.parseInt(tmp[1].trim());
        comment.sendKeys(commentText);
        voteBtn.click();
        Thread.sleep(500);
        tmp1 = votesCount.getText().split(":");
        int postVoteCount = Integer.parseInt(tmp1[1].trim());

        if (postVoteCount>preVoteCount){
            commentDate=driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
            author = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
            commentVal = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
            return commentDate + "!" + author + "!" + commentVal;
        }

        return null;
    }

}
