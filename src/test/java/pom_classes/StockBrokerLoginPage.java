package pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StockBrokerLoginPage extends StockBrokerCommon{
    public StockBrokerLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//h1[@class=\"title\"]")
    WebElement title;
    @FindBy (xpath = "//p[@class=\"sub-title\"]")
    WebElement subTitle;
    @FindBy (xpath = "//input[@id=\"input-email\"]")
    WebElement emailInputBar;
    @FindBy (xpath = "//p[@class=\"error-message ng-star-inserted\"]")
    WebElement emailErrorMessage;
    @FindBy (xpath = "//input[@id=\"input-password\"]")
    WebElement passwordInputBar;
    @FindBy (xpath = "(//p[@class='error-message ng-star-inserted'])[2]")
    WebElement passwordErrorMessage;
    @FindBy (xpath = "//a[@class=\"forgot-password\"]")
    WebElement forgotPassword;
    @FindBy (xpath = "//button[@class=\"status-success appearance-filled full-width size-medium shape-rectangle btn-disabled nb-transition\"]")
    WebElement logInButton;

    //Assertion that login is present
    public void verifyThatLoginPageIsPresent (){

       // Assert.assertTrue(Boolean.parseBoolean(driver.getCurrentUrl()), "http://34.227.177.61/auth/login");
        waiting(title);
        Assert.assertEquals(title.getText(), "Login");
        waiting(subTitle);
        Assert.assertEquals(subTitle.getText(),"Hello! Log in with your email.");
        waiting(emailInputBar);
        Assert.assertTrue(emailInputBar.isDisplayed());
        waiting(passwordInputBar);
        Assert.assertTrue(passwordInputBar.isDisplayed());
        waiting(logInButton);
        Assert.assertTrue(logInButton.isDisplayed());

    }

    //Assertion for login button
    public void verifyThatLoginButtonIsDisabledIfEmailOrPasswordIsBlank (){

        Assert.assertTrue(logInButton.isDisplayed());
        Assert.assertEquals(emailErrorMessage.getText(),"Email is required!");
        Assert.assertEquals(passwordErrorMessage.getText(),"Password is required!");

    }


    //Entering email
    public void submitEmail (String email){
        WebDriverWait wait = new WebDriverWait(driver,WAIT);
        wait.until(ExpectedConditions.visibilityOf(emailInputBar));
        emailInputBar.sendKeys(email);

    }

    //Entering password
    public void submitPassword (String password){
        WebDriverWait wait = new WebDriverWait(driver,WAIT);
        wait.until(ExpectedConditions.visibilityOf(passwordInputBar));
        passwordInputBar.sendKeys(password);

    }
}
