package com.finzy.steps;

import com.finzy.utility.helper.CommonMethods;
import com.finzy.utility.helper.DriverFactory;
import com.finzy.utility.helper.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;


public class Hook {

    /**
     * Function to launch the browser
     *
     * @throws MalformedURLException
     */
    WebDriverRunner webDriverRunner = new WebDriverRunner();
    @Before
    public void driverSetup() throws MalformedURLException {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver driver = driverFactory.getDriver();
        WebDriverRunner.setDriver(driver);
    }
    /**
     * Function to handle the close of the test execution
     *
     * @param scenario
     * @throws IOException
     */
    @After
    public void tearDown(Scenario scenario) throws IOException {
        WebDriver driver = WebDriverRunner.getWebDriver();
        if (scenario.isFailed()) {
            scenario.embed(CommonMethods.takeSnapshot(driver), "image/png");
        }
        closeBrowser(driver);
    }

    /**
     * Function to quit the browser
     */
    public void closeBrowser(WebDriver driver) {
        driver.quit();
    }

}
