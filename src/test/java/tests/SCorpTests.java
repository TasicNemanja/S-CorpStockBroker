package tests;

import Selenium_core.DriverManager;
import Selenium_core.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom_classes.StockBrokerCommandPage;
import pom_classes.StockBrokerCommon;
import pom_classes.StockBrokerDashboard;
import pom_classes.StockBrokerLoginPage;


import java.util.concurrent.TimeUnit;

public class SCorpTests {


    String URL = "http://34.227.177.61/";

    WebDriver driver;
    DriverManager driverManager;
    StockBrokerCommon stockBrokerCommon;
    StockBrokerCommandPage stockBrokerCommandPage;
    StockBrokerDashboard stockBrokerDashboard;
    StockBrokerLoginPage stockBrokerLoginPage;


    @BeforeMethod
    public void setup(){

        driverManager = DriverManagerFactory.getDriverManager("CHROME");
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(URL);

    }

    @Test
    public void verifyThatLoginPageIsLoaded() {

    stockBrokerCommon = new StockBrokerCommon(driver);
    stockBrokerDashboard = new StockBrokerDashboard(driver);
    stockBrokerLoginPage = new StockBrokerLoginPage(driver);

    stockBrokerLoginPage.verifyThatLoginPageIsPresent();


    }

    @Test
    public void verifyThatUserIsAbleToLoginWithValidCredentials() {

        stockBrokerCommon = new StockBrokerCommon(driver);
        stockBrokerDashboard = new StockBrokerDashboard(driver);
        stockBrokerLoginPage = new StockBrokerLoginPage(driver);

        //Verify That User Cannot Proceed To Dashboard if he do not submit required fields
        stockBrokerLoginPage.verifyThatLoginButtonIsDisabledIfEmailOrPasswordIsBlank();

        stockBrokerLoginPage.submitEmail("test.com");
        stockBrokerLoginPage.submitPassword("123456");

    }

    @AfterMethod
    public void tearDown(){
        driverManager.quitWebDriver();
    }

}
