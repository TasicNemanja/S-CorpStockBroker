package Selenium_core;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver driver;

    protected abstract void createWebDriver();

    public WebDriver getWebDriver(){
        if(null == driver){
            createWebDriver();
        }

        return driver;
    }

    public void quitWebDriver () {
        if(null != driver){
            driver.quit();
            driver = null;
        }
    }

}
