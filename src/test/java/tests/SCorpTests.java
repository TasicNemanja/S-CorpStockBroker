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

        //Tests that User can't access to dashboard if he don't enter all valid credentials.
        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com"); //Verify That User Cannot Proceed To Dashboard If User Submit Only Email.

        //Tests that User can't access to dashboard if he don't enter all valid credentials.
        stockBrokerLoginPage.clearEmail();
        stockBrokerLoginPage.submitPassword("C3rb3rus"); //Verify That User Cannot Proceed To Dashboard If User  Submit Only Password.

        //Tests that user can't submit invalid email format.
        stockBrokerLoginPage.submitEmail("stock.app.284gmail.com"); //Verify That User Cannot Proceed To Dashboard If User Submit Invalid Email Format.
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        stockBrokerLoginPage.submitEmail("stock.app.284@gmail");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        stockBrokerLoginPage.submitEmail("stock.app.284.gmail@com");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.verifyInvalidEmailFormat();

        //Tests that user can't submit invalid password format.
        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com"); //Verify That User Cannot Proceed To Dashboard If User Submit Invalid Password Format.
        stockBrokerLoginPage.submitPassword("123");
        stockBrokerLoginPage.verifyInvalidPasswordFormat();

        //Verify That User Cannot Proceed To Dashboard If User Submit Wrong Credentials.
        stockBrokerLoginPage.submitEmail("test@gmail.com");
        stockBrokerLoginPage.submitPassword("C3rb3rus");
        stockBrokerLoginPage.clickLoginButton();
        stockBrokerLoginPage.verifyInvalidCredentials();

        stockBrokerLoginPage.submitEmail("stock.app.284@gmail.com");
        stockBrokerLoginPage.submitPassword("1234523!");
        stockBrokerLoginPage.clickLoginButton();
        stockBrokerLoginPage.verifyInvalidCredentials();

        //Verify That User Can Log in And Proceed To Dashboard If User Submit Valid Credentials.
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
        stockBrokerDashboard.verifyThatDashBoardIconsArePresent(); //Tests that dashboard icons are present and enabled.

        stockBrokerDashboard.verifyThatUserCanTriggerMenuSideBar(); //Tests that user can minimize or maximize side menu.

        stockBrokerDashboard.verifyThatUserIsAbleToGoToCommandsPage(); //Tests can access Commands page.
        stockBrokerCommandPage.backToDashBoard(); //Return user back to dashboard page.

        stockBrokerDashboard.verifyThatUserIsAbleToGoToPositionsPage(); //Tests can access Positions page.
        stockBrokerPositions.backToDashBoard(); //Return user back to dashboard page.

        stockBrokerDashboard.verifyThatUserIsAbleToGoToUserPage(); //Tests can access User page.
        stockBrokerProfile.backToDashBoard(); //Return user back to dashboard page.

        stockBrokerDashboard.verifyThatUserIsAbleToAccessSearchBar(); //Tests can access to Search bar.
        stockBrokerDashboard.closeSearch(); //Tests that user can close Search bar.


    }

    @Test
    public void profilePageTests(){
        stockBrokerLoginPage = new StockBrokerLoginPage(driver);
        stockBrokerDashboard = new StockBrokerDashboard(driver);
        stockBrokerProfile = new StockBrokerProfilePage(driver);
        stockBrokerCommon = new StockBrokerCommon(driver);

        stockBrokerLoginPage.logInToDashboard();
        stockBrokerDashboard.accessProfileSettings();

        stockBrokerProfile.changeTheme("Dark"); //There is Light, Dark, Cosmic and Corporate themes. First letter MUST be Uppercase.
      //  stockBrokerProfile.changeTheme("Cosmic");

        stockBrokerProfile.changeFirstName("Test"); //Tests if First Name can be changed.
        stockBrokerProfile.changeFirstName("Kyle"); //Returns First name in default value.

        stockBrokerProfile.changeLastName("Test"); //Tests if Last Name can be changed.
        stockBrokerProfile.changeLastName("Summers"); //Returns Last name in default value.

        stockBrokerProfile.changeEmail("test@test.com"); //Tests if Email can be changed.
        stockBrokerProfile.changeEmail("stock.app.284@gmail.com");//Returns Email in default value.

        //Password MUST contain of at least one number, one lowercase and one uppercase letter, and Confirm Password MUST be the same as New Password.
        stockBrokerProfile.verifyThatUserCantChangePasswordWithEmptyFields(); //Test that user can't change password if password fields are empty.
        stockBrokerProfile.verifyThatUserCanNotChangePasswordWithInvalidPassFormat("1234"); //Tests that user is not able to change password if password format is not complied.
        stockBrokerProfile.verifyThatUserCanNotChangePasswordWithInvalidPassFormat("qwert");//Tests that user is not able to change password if password format is not complied.
        stockBrokerProfile.verifyThatUserCanNotChangePasswordWithInvalidPassFormat("SDFS"); //Tests that user is not able to change password if password format is not complied.
        stockBrokerProfile.verifyThatUserIsAbleToChangePassword("Test123"); //Tests that user is able to change password if format is complied.
        stockBrokerProfile.verifyThatUserIsAbleToChangePassword("C3rb3rus"); //Returns Password in default value.



    }

    @AfterMethod
    public void tearDown(){
        driverManager.quitWebDriver();
    }

}
