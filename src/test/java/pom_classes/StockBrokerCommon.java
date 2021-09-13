package pom_classes;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static java.awt.SystemColor.text;

public class StockBrokerCommon {
    WebDriver driver;
    int WAIT = 20;

    public StockBrokerCommon(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //Locators
    @FindBy(xpath = "//a[@class=\"logo\"]")
    WebElement logo;
    @FindBy(xpath = "//a[@class=\"sidebar-toggle\"]")
    WebElement sidebarToggle;
    @FindBy(xpath = "//button[@class=\"start-search appearance-ghost size-medium shape-rectangle icon-start icon-end status-basic nb-transition\"]")
    WebElement searchIcon;
    @FindBy(xpath = "//input[@class=\"search-input\"]")
    WebElement searchInput;
    @FindBy(xpath = "//div[@class=\"user-picture initials ng-star-inserted\"]")
    WebElement userMenuIcon;
    @FindBy(xpath = "//nb-icon[@class=\"menu-icon ng-tns-c81-0 ng-star-inserted\"]")
    WebElement commandsIcon;
    @FindBy(xpath = "//nb-icon[@class=\"menu-icon ng-tns-c81-1 ng-star-inserted\"]")
    WebElement positionsIcon;
    @FindBy(xpath = "//span[@class=\"created-by\"]")
    WebElement footer;
    @FindBy (xpath = "//a[@title=\"Profile\"]")
    WebElement profileIcon;
    @FindBy (css = "[data-name=\"close\"]")
    WebElement closeSearch;
    @FindBy (xpath = "//body[@class=\"pace-done nb-theme-default\"]")
    WebElement assertDefaultTheme;
    @FindBy (xpath = "//body[@class=\"pace-done nb-theme-dark\"]")
    WebElement assertDarkTheme;
    @FindBy (xpath = "//body[@class=\"pace-done nb-theme-cosmic\"]")
    WebElement assertCosmicTheme;
    @FindBy (xpath = "//body[@class=\"pace-done nb-theme-corporate\"]")
    WebElement assertCorporateTheme;






    //Method for clicking on elements
    public void verifyCommons(){
        Assert.assertTrue(logo.isDisplayed());
        Assert.assertTrue(logo.isEnabled());
        Assert.assertTrue(sidebarToggle.isDisplayed());
        Assert.assertTrue(sidebarToggle.isEnabled());
        Assert.assertTrue(searchIcon.isDisplayed());
        Assert.assertTrue(searchIcon.isEnabled());
        Assert.assertTrue(userMenuIcon.isDisplayed());
        Assert.assertTrue(userMenuIcon.isEnabled());
        Assert.assertTrue(footer.isDisplayed());
    }
    public void clickOnElement (WebElement element){

        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

    }
    public void enterText(WebElement element, String text){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }
    public void hoverOverElement(WebElement element){
        Actions actions = new Actions(driver);
        waiting(element);
        actions.moveToElement(element).build().perform();
    }
    public void waiting(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver,WAIT);
            wait.until(ExpectedConditions.visibilityOf(element));

            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element);
                actions.build().perform();
                actions.click();
            } catch (StaleElementReferenceException e) {
                element.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    public void backToDashBoard(){
        waiting(logo);
        clickOnElement(logo);
    }
    public void closeSearch() {
        clickOnElement(closeSearch);
    }





}
