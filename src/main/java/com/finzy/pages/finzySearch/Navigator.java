package com.finzy.pages.finzySearch;

import com.finzy.utility.helper.CommonMethods;
import com.finzy.utility.helper.WebDriverRunner;
import com.finzy.utility.utils.ConfigFileReader;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class Navigator {
    CommonMethods commonMethods = new CommonMethods();
    public WebDriver driver = null;
    ConfigFileReader configFileReader = new ConfigFileReader();
    Navigator() throws MalformedURLException {
        driver = WebDriverRunner.getWebDriver();
        driver.get(configFileReader.getApplicationUrl());
        commonMethods.implicitlyWait(driver);
    }
}
