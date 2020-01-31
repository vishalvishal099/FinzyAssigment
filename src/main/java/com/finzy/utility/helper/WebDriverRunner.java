package com.finzy.utility.helper;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class WebDriverRunner {

    private static WebDriver driver;

    public static WebDriver getWebDriver() throws MalformedURLException {
        return driver;
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
