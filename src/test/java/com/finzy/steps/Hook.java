package com.finzy.steps;

import com.finzy.utility.helper.CommonMethods;
import com.finzy.utility.helper.DriverFactory;
import com.finzy.utility.utils.ConfigFileReader;
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

    @Before
    public void driverSetup()  {
        WebDriver driver = DriverFactory.driver();
        driver.get(new ConfigFileReader().getApplicationUrl());


    }
    /**
     * Function to handle the close of the test execution
     *
     * @param scenario
     * @throws IOException
     */
    @After
    public void tearDown(Scenario scenario) throws IOException {
        WebDriver driver =  DriverFactory.driver();
        if (scenario.isFailed()) {
            scenario.embed(CommonMethods.takeSnapshot(driver), "image/png");
        }
        closeBrowser();
    }

    /**
     * Function to quit the browser
     */
     public void closeBrowser() {
       DriverFactory.closeDriver();
    }

}
