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
    @FindBy (css = "[class=\"menu-sidebar left compacted\"]")
    WebElement sideBarCompact;
    @FindBy (css = "[class=\"menu-sidebar left expanded\"]")
    WebElement sideBarExpended;






    public void verifyThatUserIsOnDashboardPage (){
        waiting(logo);
        verifyCommons();
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
        Assert.assertEquals(driver.getCurrentUrl(),"http://34.227.177.61/pages/commands");
        verifyCommons();
    }

    public void verifyThatUserIsAbleToGoToPositionsPage(){
        clickOnElement(positionsIcon);
        Assert.assertEquals(driver.getCurrentUrl(),"http://34.227.177.61/pages/positions");
        verifyCommons();
    }

    public void verifyThatUserIsAbleToGoToUserPage(){

        clickOnElement(userMenuIcon);
        hoverOverElement(profileIcon);
        clickOnElement(profileIcon);
        Assert.assertEquals(driver.getCurrentUrl(), "http://34.227.177.61/pages/profile");

    }

    public void verifyThatUserIsAbleToAccessSearchBar(){
        clickOnElement(searchIcon);
        Assert.assertTrue(searchInput.isDisplayed());
        Assert.assertTrue(searchInput.isEnabled());
    }

    public void verifyThatUserCanTriggerMenuSideBar(){
        clickOnElement(sidebarToggle);
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(sideBarCompact));
        Assert.assertTrue(sideBarCompact.isDisplayed());
        clickOnElement(sidebarToggle);
        wait.until(ExpectedConditions.visibilityOf(sideBarExpended));
        Assert.assertTrue(sideBarExpended.isDisplayed());

    }









}
