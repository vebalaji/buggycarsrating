package org.buggy.rating.tests;

import org.buggy.rating.pages.ModelPage;
import org.buggy.rating.pages.OverallPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ModelPageTest extends BaseTest {
    @Test
    public void verifyVotingForAModel() throws InterruptedException {
        int rowCount = 0 , pc=0;
        boolean exit=false;
        String[] result;
        homePage.login(prop.getProperty("username"), prop.getProperty("password"));
        OverallPage overallPage = homePage.clickOverallRating();
        int pageCount = Integer.parseInt(overallPage.totalPages.getText().substring(overallPage.totalPages.getText().lastIndexOf("of ") + 3));
        for (pc = 1; pc <= pageCount; pc++) {
            List<WebElement> rows = driver.findElements(By.xpath("//table[@class='cars table table-hover']/tbody/tr/td[1]"));
            System.out.println("No of rows in Page " + pc + " = " + rows.size());
            rowCount = rows.size();
            for (int i = 1; i <= rowCount; i = i + 1) {
                driver.findElement(By.xpath("//table[@class='cars table table-hover']/tbody/tr[" + i + "]/td[3]/a")).click();
                Thread.sleep(900);
                ModelPage modelPage = new ModelPage(driver);

                if (driver.findElements(By.xpath("//p[contains(text(),'Thank you for your vote!')]")).size()!=0){
                    driver.navigate().back(); // Thank you for your vote! Text is present
                    Thread.sleep(900);
                    if (pc>1) // This is temporary fix as the page back goes to home page from second page
                    {
                        for (int j=1;j<pc;j++)
                            overallPage.nextPage.click(); // This is to go to the correct page number
                    }
                }
                else{
                    result = modelPage.commentAndVoteForAModel(prop.getProperty("comment")).split("!");
                    if (result[1].isEmpty())
                        Assert.fail("Author details are empty post commenting - Please check!");
                    else
                        Assert.assertTrue(result[1].equals(prop.getProperty("author")),"Author details is incorrect for the comment added - Please check!");
                    if (result[2].isEmpty())
                        Assert.fail("Comment details are empty post commenting - Please check!");
                    else
                        Assert.assertTrue(result[2].equals(prop.getProperty("comment")),"Comment details is incorrect for the comment added - Please check!");
                    exit=true;
                    pc = pc+pageCount;
                    break;
                }
            }

        if ((pc < pageCount) && !exit) {
            try {
                overallPage.nextPage.click();
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                overallPage.nextPage.click();
            }
            Thread.sleep(1000);
        }
    }
        if (!exit)
            Assert.fail("Unable to Comment as a comment has been provided for all the models using this username "+prop.getProperty("username"));

    }
 }

