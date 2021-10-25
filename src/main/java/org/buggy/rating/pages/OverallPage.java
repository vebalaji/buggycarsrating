package org.buggy.rating.pages;

import org.buggy.rating.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.*;

public class OverallPage extends BasePage {

    @FindBy(xpath = "//div[@class='pull-xs-right' and contains(.,'page')]")
    public WebElement totalPages;

    @FindBy(xpath = "//a[contains(text(),'»')]")
    public WebElement nextPage;

    @FindBy(xpath = "//a[contains(text(),'Buggy Rating')]")
    private WebElement buggyRating;

    public OverallPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void gotoHomePage(){
        buggyRating.click();
    }

    public String findPopularModel() throws InterruptedException {
        String currentModel=null,maxModel=null;
        String currentMake=null,maxMake=null;
        int currentVote=0,maxVote=0;
        int rowCount=0,colCount=0;

        List<String> makeList = new ArrayList<String>();

        List<WebElement> cols = driver.findElements(By.xpath("//table[@class='cars table table-hover']/thead/tr/th"));
        System.out.println("No of cols = "+cols.size());


        int pageCount = Integer.parseInt(totalPages.getText().substring(totalPages.getText().lastIndexOf("of ")+3));
        System.out.println("Total Pages = "+totalPages.getText().substring(totalPages.getText().lastIndexOf("of ")+3));

        for (int pc=1;pc<=pageCount;pc++)
        {
            List<WebElement> rows = driver.findElements(By.xpath("//table[@class='cars table table-hover']/tbody/tr/td[1]"));
            System.out.println("No of rows in Page "+pc+" = "+rows.size());
            rowCount = rows.size();
            for (int i=1;i<= rowCount;i=i+1){

                currentVote =  Integer.parseInt(driver.findElement(By.xpath("//table[@class='cars table table-hover']/tbody/tr["+i+"]/td[5]")).getText());
                currentModel = driver.findElement(By.xpath("//table[@class='cars table table-hover']/tbody/tr["+i+"]/td[3]")).getText();
                currentMake = driver.findElement(By.xpath("//table[@class='cars table table-hover']/tbody/tr["+i+"]/td[2]")).getText();
                makeList.add(currentMake);
                if (currentVote>maxVote){
                    maxVote = currentVote;
                    maxModel = currentModel;
                    maxMake = currentMake;
                }
            }
            if (pc<pageCount){
                try {
                    nextPage.click();
                }
                catch (org.openqa.selenium.StaleElementReferenceException ex){
                    nextPage.click();
                }
                Thread.sleep(1000);
            }
        }

        System.out.println("Maximum Votes = "+ maxVote + " and the Model = "+maxModel+" and the Make = "+maxMake);

        Set<String> uniqueMake = new HashSet<String>(makeList);

        System.out.println("Unique Make List count: " + uniqueMake.size() + "  Unique Make List ="+uniqueMake);

        return (maxMake+","+maxModel+","+maxVote);
    }

    public boolean findBrokenImage(){
        int invalidImageCount=0;
        try {
                int pageCount = Integer.parseInt(totalPages.getText().substring(totalPages.getText().lastIndexOf("of ")+3));
                for (int pc=1;pc<=pageCount;pc++)
                {
                    List<WebElement> imagesList = driver.findElements(By.tagName("img"));
                    System.out.println("Total no. of images are " + imagesList.size());
                    for (WebElement imgElement : imagesList) {
                        if (imgElement != null) {
                            if (((imgElement.getAttribute("naturalWidth").equals("0")) || (imgElement.getAttribute("naturalWidth").equals(null)))
                            &&  ((imgElement.getAttribute("naturalWidth").equals("0")) || (imgElement.getAttribute("naturalheight").equals(null))))
                                invalidImageCount++;
                        }
                    }
                    if (pc<pageCount){
                        try {
                            nextPage.click();
                        }
                        catch (org.openqa.selenium.StaleElementReferenceException ex){
                            nextPage.click();
                        }
                        Thread.sleep(1000);
                    }
                }
                System.out.println("Total no. of invalid images are "	+ invalidImageCount);
            }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if (invalidImageCount>0)
            return false;
        return true;
    }
}
