package tests;

import Selenium_core.DriverManager;
import Selenium_core.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom_classes.*;


import java.util.concurrent.TimeUnit;

public class SCorpTests {


    String URL = "http://34.227.177.61/";

    WebDriver driver;
    DriverManager driverManager;
    StockBrokerCommon stockBrokerCommon;
    StockBrokerCommandPage stockBrokerCommandPage;
    StockBrokerDashboard stockBrokerDashboard;
    StockBrokerLoginPage stockBrokerLoginPage;
    StockBrokerProfilePage stockBrokerProfile;
    StockBrokerPositions stockBrokerPositions;


    @BeforeMethod
    public void setup(){

        driverManager = DriverManagerFactory.getDriverManager("CHROME");
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(URL);

    }

    @Test
    public void verifyThatLoginPageIsLoaded() {

    stockBrokerLoginPage = new StockBrokerLoginPage(driver);

    stockBrokerLoginPage.verifyThatLoginPageIsPresent();
    }

    @Test
    public void loginPageTests() {

        stockBrokerCommon = new StockBrokerCommon(driver);
        stockBrokerDashboard = new StockBrokerDashboard(driver);
        stockBrokerLoginPage = new StockBrokerLoginPage(driver);

        //Verify That User Cannot Proceed To Dashboard If He Submit Only Email.
        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");

        //Verify That User Cannot Proceed To Dashboard If He Submit Only Password.
        stockBrokerLoginPage.clearEmail();
        stockBrokerLoginPage.submitPassword("C3rb3rus");

        //Verify That User Cannot Proceed To Dashboard If He Submit Invalid Email Format.
        stockBrokerLoginPage.submitEmail("stock.app.284gmail.com");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        stockBrokerLoginPage.submitEmail("stock.app.284@gmail");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        stockBrokerLoginPage.submitEmail("stock.app.284.gmail@com");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        //Verify That User Cannot Proceed To Dashboard If He Submit Invalid Password Format.
        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");
        stockBrokerLoginPage.submitPassword("123");
        stockBrokerLoginPage.verifyInvalidPasswordFormat();

        //Verify That User Cannot Proceed To Dashboard If He Submit Wrong Credentials.
        stockBrokerLoginPage.submitEmail("test@gmail.com");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.clickLoginButton();
        stockBrokerLoginPage.verifyInvalidCredentials();

        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");
        stockBrokerLoginPage.submitPassword("1234523!");
        stockBrokerLoginPage.clickLoginButton();
        stockBrokerLoginPage.verifyInvalidCredentials();

        //Verify That User Can Login And Proceed To Dashboard If He Submit Valid Credentials.
        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.clickLoginButton();
        stockBrokerDashboard.verifyThatUserIsOnDashboardPage();
    }

    @Test
    public void dashBoardPageTests(){

        stockBrokerLoginPage = new StockBrokerLoginPage(driver);
        stockBrokerDashboard = new StockBrokerDashboard(driver);
        stockBrokerCommandPage = new StockBrokerCommandPage(driver);
        stockBrokerProfile = new StockBrokerProfilePage(driver);
        stockBrokerPositions = new StockBrokerPositions(driver);


        stockBrokerLoginPage.logInToDashboard();
        stockBrokerDashboard.verifyThatDashBoardIconsArePresent();

        stockBrokerDashboard.verifyThatUserCanTriggerMenuSideBar();

        stockBrokerDashboard.verifyThatUserIsAbleToGoToCommandsPage();
        stockBrokerCommandPage.backToDashBoard();

        stockBrokerDashboard.verifyThatUserIsAbleToGoToPositionsPage();
        stockBrokerPositions.backToDashBoard();

        stockBrokerDashboard.verifyThatUserIsAbleToGoToUserPage();
        stockBrokerProfile.backToDashBoard();

        stockBrokerDashboard.verifyThatUserIsAbleToAccessSearchBar();
        stockBrokerDashboard.closeSearch();


    }

    @Test
    public void profilePageTests(){
        stockBrokerLoginPage = new StockBrokerLoginPage(driver);
        stockBrokerDashboard = new StockBrokerDashboard(driver);
        stockBrokerProfile = new StockBrokerProfilePage(driver);
        stockBrokerCommon = new StockBrokerCommon(driver);

        stockBrokerLoginPage.logInToDashboard();
        stockBrokerDashboard.accessProfileSettings();

        stockBrokerProfile.changeFirstName("Test");
        stockBrokerProfile.changeFirstName("Kyle");

        stockBrokerProfile.changeLastName("Test");
        stockBrokerProfile.changeLastName("Summers");

        stockBrokerProfile.changeEmail("test@test.com");
        stockBrokerProfile.changeEmail("stock.app.284@gmail.com");

        stockBrokerProfile.verifyThatUserCantChangePasswordWithEmptyFields();
        stockBrokerProfile.verifyThatUserCanNotChangePasswordWithInvalidPassFormat("1234");
        stockBrokerProfile.verifyThatUserCanNotChangePasswordWithInvalidPassFormat("qwert");
        stockBrokerProfile.verifyThatUserCanNotChangePasswordWithInvalidPassFormat("SDFS");
        stockBrokerProfile.verifyThatUserIsAbleToChangePassword("Test123");
        stockBrokerProfile.verifyThatUserIsAbleToChangePassword("C3rb3rus");



    }

    @AfterMethod
    public void tearDown(){
        driverManager.quitWebDriver();
    }

}
