package pom_classes;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class StockBrokerDashboard extends StockBrokerCommon{
    public StockBrokerDashboard(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    @FindBy (css = "html>body>ngx-app>ngx-pages>ngx-one-column-layout>nb-layout>div>div>div>div>div>nb-layout-column>ngx-dashboard>h1")
    WebElement header;
    @FindBy (css = "html>body>ngx-app>ngx-pages>ngx-one-column-layout>nb-layout>div>div>div>div>div>nb-layout-column>ngx-dashboard>h1")
    WebElement welcomeTextHeader;
    @FindBy (xpath = "//p[text()='To set up your Ameritrade account click on your Name in the upper right corner and go to Profile']")
    WebElement welcomeText1;
    @FindBy (xpath = "//p[text()='To add or modify commands go to Settings']")
    WebElement welcomeText2;
    @FindBy (xpath = "//p[text()=' If you want to look up a stock, click on the magnifying glass and Search for the Stock Symbol you want to check out.']")
    WebElement welcomeText3;




    public void verifyThatUserIsOnDashboardPage (){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(logo));

        Assert.assertEquals(driver.getCurrentUrl(), "http://34.227.177.61/pages/dashboard");
        Assert.assertEquals(driver.getTitle(), "Stock Broker");
        Assert.assertEquals(welcomeTextHeader.getText(), "Welcome to Stock Broker test enviroment.");
        Assert.assertEquals(welcomeText1.getText(), "To set up your Ameritrade account click on your Name in the upper right corner and go to Profile");
        Assert.assertEquals(welcomeText2.getText(),"To add or modify commands go to Settings");
        Assert.assertEquals(welcomeText3.getText(),"If you want to look up a stock, click on the magnifying glass and Search for the Stock Symbol you want to check out.");
    }

    public void verifyThatDashBoardIconsArePresent(){

        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOfAllElements(commandsIcon,positionsIcon,searchIcon,userMenuIcon));

        Assert.assertTrue(commandsIcon.isDisplayed());
        Assert.assertTrue(commandsIcon.isEnabled());
        Assert.assertTrue(positionsIcon.isDisplayed());
        Assert.assertTrue(positionsIcon.isEnabled());
        Assert.assertTrue(searchIcon.isDisplayed());
        Assert.assertTrue(searchIcon.isEnabled());
        Assert.assertTrue(userMenuIcon.isDisplayed());
        Assert.assertTrue(userMenuIcon.isEnabled());
    }

    public void verifyThatUserIsAbleToGoToCommandsPage(){
        clickOnElement(commandsIcon);
    }

    public void verifyThatUserIsAbleToGoToUserPage(){
        clickOnElement(userMenuIcon);
    }

    public void verifyThatUserIsAbleToAccessSearchBar(){
        clickOnElement(searchIcon);
    }

    public void verifyThatUserCanTriggerMenuSideBar(){
        clickOnElement(sidebarToggle);

    }









}
