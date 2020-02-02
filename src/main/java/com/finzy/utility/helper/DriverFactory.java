package com.finzy.utility.helper;

import com.finzy.utility.utils.ConfigFileReader;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Class to handle the operations related to the driver
 * Author : Vishal Singh
 */
public class DriverFactory {
    //Initialization of objects and assigning references to the object.
    ConfigFileReader configFileReader = new ConfigFileReader();
    private static HashMap<Long, WebDriver> driverHashMap = new HashMap<>();

    public static WebDriver driver() {
        long threadId = Thread.currentThread().getId();
        if (driverHashMap.get(threadId) != null) {
            return driverHashMap.get(threadId);
        }
        WebDriver driver = null;
        try {
            driver = new DriverFactory().getDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driverHashMap.put(threadId, driver);
        return driver;
    }

    public static void closeDriver() {
        WebDriver curretDriver = driverHashMap.get(Thread.currentThread().getId());
        curretDriver.close();
        driverHashMap.remove(Thread.currentThread().getId());
    }

    /**
     * Function to create a browser
     *
     * @return browser reference
     * @throws MalformedURLException
     */
    private WebDriver getDriver() throws MalformedURLException {
        WebDriver driver = null;
        if (configFileReader.getMode().equalsIgnoreCase("grid")) {
            driver = remoteDriver();
        } else {
            driver = browser();
        }
        return driver;
    }

    /**
     * Function to create a remote driver
     *
     * @return browser reference
     * @throws MalformedURLException
     */
    public WebDriver remoteDriver() throws MalformedURLException {
        switch (configFileReader.getBrowser().toLowerCase()) {

            case "chrome":
                WebDriver chromDriver;
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities().chrome();
                if (configFileReader.getHttpProxySetStatus()) {
                    desiredCapabilities.setCapability(CapabilityType.PROXY, setUpProxy());
                    chromDriver = new RemoteWebDriver(new URL(configFileReader.getHubURL()), desiredCapabilities);
                } else {
                    chromDriver = new RemoteWebDriver(new URL(configFileReader.getHubURL()), desiredCapabilities);
                }
                configureDriver(chromDriver);
                return chromDriver;

            case "firefox":
                WebDriver firefoxDriver;
                DesiredCapabilities ffCapability = new DesiredCapabilities().firefox();
                if (configFileReader.getHttpProxySetStatus()) {
                    ffCapability.setCapability(CapabilityType.PROXY, setUpProxy());
                    firefoxDriver = new RemoteWebDriver(new URL(configFileReader.getHubURL()), ffCapability);
                } else {
                    firefoxDriver = new RemoteWebDriver(new URL(configFileReader.getHubURL()), ffCapability);
                }
                configureDriver(firefoxDriver);
                return firefoxDriver;

            case "ie":
                WebDriver IEDriver;
                DesiredCapabilities IEcapability = new DesiredCapabilities().internetExplorer();
                if (configFileReader.getHttpProxySetStatus()) {
                    IEcapability.setCapability(CapabilityType.PROXY, setUpProxy());
                    IEDriver = new RemoteWebDriver(new URL(configFileReader.getHubURL()), IEcapability);
                } else {
                    IEDriver = new RemoteWebDriver(new URL(configFileReader.getHubURL()), IEcapability);
                }
                configureDriver(IEDriver);
                return IEDriver;
        }
        throw new RuntimeException(" Remote driver not found available, Please verify your driver !!!");
    }

    /**
     * Function to check for the os, browser and select the appropriate driver
     *
     * @return respective browser references according to the required properties
     */
    public WebDriver browser() {
        /**
         * Using the OS and the required browser by using the conditional statements, to return the browser reference
         */
        String os = System.getProperty("os.name");
        String driverPath = System.getProperty("user.dir") + configFileReader.getDriverPath();
        if (os.contains("Windows") && configFileReader.getBrowser().toLowerCase().equals("chrome")) {
            driverPath = driverPath + "win64/chromedriver.exe";
            return setPropertyAndInitChromeDriver(driverPath);
        } else if (os.contains("Windows") && configFileReader.getBrowser().toLowerCase().equals("firefox")) {
            driverPath = driverPath + "win64/geckodriver.exe";
            return setPropertyAndInitFirefoxDriver(driverPath);
        } else if (os.contains("Windows") && configFileReader.getBrowser().toLowerCase().equals("ie")) {
            driverPath = driverPath + "win32/IEDriverServer.exe";
            return setPropertyAndInitIEDriver(driverPath);
        } else if (os.contains("Mac") && configFileReader.getBrowser().toLowerCase().equals("chrome")) {
            driverPath = driverPath + "mac64/chromedriver";
            return setPropertyAndInitChromeDriver(driverPath);
        } else if (os.contains("Mac") && configFileReader.getBrowser().toLowerCase().equals("firefox")) {
            driverPath = driverPath + "mac64/geckodriver";
            return setPropertyAndInitFirefoxDriver(driverPath);
        }
        throw new RuntimeException(" Not such driver available, Please verify your driver !!!");
    }

    /**
     * Function to configure the properties for Chrome browser
     *
     * @param path
     * @return browser reference
     */
    private WebDriver setPropertyAndInitChromeDriver(String path) {
        WebDriver chromeDriver = null;
        System.setProperty("webdriver.chrome.driver", path);
        //Setting the proxy for the driver by getting the configuration from configFile.properties
        if (configFileReader.getHttpProxySetStatus()) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(CapabilityType.PROXY, setUpProxy());
            chromeDriver = new ChromeDriver(desiredCapabilities);

        } else {
            chromeDriver = new ChromeDriver();
        }
        configureDriver(chromeDriver);
        return chromeDriver;
    }

    /**
     * Function to configure the properties of Firefox browser
     *
     * @param path
     * @return browser reference
     */
    public WebDriver setPropertyAndInitFirefoxDriver(String path) {
        //For latest version of firefox we need to configure the gecko driver
        System.setProperty("webdriver.gecko.driver", path);
        WebDriver firefoxDriver = null;
        if (configFileReader.getHttpProxySetStatus()) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setCapability(CapabilityType.PROXY, setUpProxy());
            firefoxDriver = new FirefoxDriver(desiredCapabilities);
        } else {
            firefoxDriver = new FirefoxDriver();
        }

        configureDriver(firefoxDriver);
        return firefoxDriver;
    }

    /**
     * Function to configure the properties of Internet Explorer browser
     *
     * @param path
     * @return reference to the ie driver
     */
    private WebDriver setPropertyAndInitIEDriver(String path) {
        System.setProperty("webdriver.ie.driver", path);
        WebDriver ieDriver = null;
        if (configFileReader.getHttpProxySetStatus()) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setCapability(CapabilityType.PROXY, setUpProxy());
            ieDriver = new InternetExplorerDriver(desiredCapabilities);
        } else {
            ieDriver = new InternetExplorerDriver();
        }
        configureDriver(ieDriver);
        return ieDriver;
    }


    public Proxy setUpProxy() {
        String proxyURL = configFileReader.getProxyURL();
        Proxy proxy = new Proxy();
        return proxy.setHttpProxy(proxyURL).setFtpProxy(proxyURL).setSslProxy(proxyURL).setSslProxy(proxyURL);
    }

    /**
     * Function to make some changes to the object / reference
     *
     * @param driver
     */
    private void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
    }

}

