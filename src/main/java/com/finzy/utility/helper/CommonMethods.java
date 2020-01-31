package com.finzy.utility.helper;


import com.finzy.utility.utils.ConfigFileReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Class containing all the common methods required for the framework
 * Author :vishal singh
 */
public class CommonMethods {

    ConfigFileReader configFileReader = new ConfigFileReader();
    /**
     * Takes the screenshot of the current browser window
     *
     * @param driver
     * @throws IOException
     */
    public static byte[] takeSnapshot(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        final byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
        return screenshotBytes;
    }

    public void implicitlyWait(WebDriver driver) {
          driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(),TimeUnit.SECONDS);
    }

    /**
     * Method to wait for the element to be visible for param seconds
     *
     * @param driver
     * @param by      attribute identifier
     * @param seconds
     * @return
     */
    public WebDriver waitForElement(WebDriver driver, By by, int seconds) {
        try {
            sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public WebDriver switchToFrame(WebDriver driver, By by) {
        driver.switchTo().frame(driver.findElement(by));
        return driver;
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


