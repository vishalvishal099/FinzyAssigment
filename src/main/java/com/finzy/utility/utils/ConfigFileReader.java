package com.finzy.utility.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to read the values of the config file and use it in the framework
 */
public class ConfigFileReader {

    private Properties properties;
    private String filepath = "src/main/resources/configFile.properties";

    /**
     * Load the data from the config file and make it accessible to the Properties variable
     */
    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + filepath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("DriverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the configFile.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("ImplicitlyWaitTime");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the CconfigFile.properties file.");
    }



    public String getApplicationUrl() {
        String url = properties.getProperty("URL");
        if (url != null) return url;
        else throw new RuntimeException("Url not specified in the configFile.properties file.");
    }

    public String getPassword() {
        String password = properties.getProperty("PASSWORD");
        if (password != null) return password;
        else throw new RuntimeException("Password not specified in the configFile.properties file.");
    }

    public boolean getHttpProxySetStatus() {
        String setProxy = properties.getProperty("HTTP_PROXY");
        if (setProxy != null && setProxy.equals("true")) return true;
        else return false;
    }

    public String getProxyURL() {
        String proxyUrl = properties.getProperty("PROXY_URL");
        if (proxyUrl != null) return proxyUrl;
        else throw new RuntimeException("Proxy URL not specified in the configFile.properties file.");
    }

    public String getBrowser() {
        String browser = properties.getProperty("BROWSER");
        if (browser != null) return browser;
        else throw new RuntimeException("Browser is not specified in the configFile.properties file.");
    }

    public String getMode() {
        String mode = properties.getProperty("MODE");
        if (mode != null) return mode;
        else throw new RuntimeException("Mode is not specified in the configuration.propertied file");
    }

    public String getHubURL(){
        String hubURL = properties.getProperty("HUB_URL");
        if(hubURL != null)  return hubURL;
        else throw  new RuntimeException("HUB_URL is not specified in the configuration.propertied file");

    }

}



