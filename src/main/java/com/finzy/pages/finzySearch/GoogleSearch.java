package com.finzy.pages.finzySearch;

import com.finzy.utility.helper.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearch  {
   private WebDriver driver;
    private By googleInput = By.name("q");
    private By nextPage = By.xpath("//span[contains(text(),'Next')]");
    private By linkHeading = By.xpath("//h3/parent::a");

    public GoogleSearch() {
        this.driver =  DriverFactory.driver();
    }

    int appearPosition = 0;
    int googlePageCounter = 1;
    List<WebElement> webElements;

    public int searchKeywordOverGoogle(String input) throws InterruptedException {
        googleInput(input);
        int fristPage = 1;
        boolean foundSearch;
        do {
            if (fristPage == 1) {
                fristPage++;
                foundSearch = getSearchData();
                if (foundSearch == true) {
                    break;
                }
            } else {
                if (nextPagePresent()) {
                    foundSearch = getSearchData();
                    if (foundSearch == true) {
                        break;
                    }
                    driver.findElement(nextPage).click();
                    googlePageCounter++;

                }
            }
        } while (nextPagePresent());
        return appearPosition;
    }

    private boolean nextPagePresent() {
        try {
            driver.findElement(nextPage);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    public boolean getSearchData() {
        boolean isAppered = false;
        webElements = driver.findElements(linkHeading);
        for (int i = 0; i < webElements.size(); i++) {
            String linkData = webElements.get(i).getText();
            if (linkData.contains("finzy.com") || linkData.contains("finzy.com\n")) {
                appearPosition = i + 1;
                isAppered = true;
                break;
            }
        }
        return isAppered;
    }

    public void googleInput(String input) throws InterruptedException {
        driver.findElement(googleInput).sendKeys(input);
        driver.findElement(googleInput).sendKeys(Keys.ENTER);
        Thread.sleep(300);
    }

    public void verifyPosition(int page, int position) {
        Assert.assertEquals(page, googlePageCounter);
        Assert.assertEquals(position, appearPosition);
    }

    public void getPosition() {
        if (appearPosition > 0) {
            System.out.println("We have found serach position at : " + googlePageCounter + "   page" + "   and  " + appearPosition + "   position");
        } else {
            System.out.println("Sorry ! we haven't found input search related data. " +
                    "Please change the input serach data raleted to finzy.");
        }

    }
}
