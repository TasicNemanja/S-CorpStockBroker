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



        //Verify That User Cannot Proceed To Dashboard If He Submit Only Email.
        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");


        //Verify That User Cannot Proceed To Dashboard If He Submit Only Password.
        stockBrokerLoginPage.submitPassword(" #kyle713!");


        //Verify That User Cannot Proceed To Dashboard If He Submit Invalid Email Format.
        stockBrokerLoginPage.submitEmail("stock.app.284gmail.com");
        stockBrokerLoginPage.submitPassword("kyle713!");
        stockBrokerLoginPage.verifyInvalidEmailFormat();


        stockBrokerLoginPage.submitEmail("stock@app@284gmail.com");
        stockBrokerLoginPage.submitPassword("kyle713!");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        stockBrokerLoginPage.submitEmail("stock.app.284@gmail");
        stockBrokerLoginPage.submitPassword("kyle713!");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        stockBrokerLoginPage.submitEmail("stock.app.284.gmail@com");
        stockBrokerLoginPage.submitPassword("kyle713!");
        stockBrokerLoginPage.verifyInvalidEmailFormat();


        //Verify That User Cannot Proceed To Dashboard If He Submit Invalid Password Format.
        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");
        stockBrokerLoginPage.submitPassword("123");
        stockBrokerLoginPage.verifyInvalidPasswordFormat();


        //Verify That User Cannot Proceed To Dashboard If He Submit Wrong Credentials.
        stockBrokerLoginPage.submitEmail("test@gmail.com");
        stockBrokerLoginPage.submitPassword("kyle713!");
        stockBrokerLoginPage.verifyInvalidCredentials();

        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");
        stockBrokerLoginPage.submitPassword("1234523!");
        stockBrokerLoginPage.verifyInvalidCredentials();

        //Verify That User Can Login And Proceed To Dashboard If He Submit Valid Credentials.


    }

//    @AfterMethod
//    public void tearDown(){
//        driverManager.quitWebDriver();
//    }

}
