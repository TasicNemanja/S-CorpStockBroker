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
    @FindBy (xpath = "/html/body/ngx-app/nb-auth/nb-layout/div/div/div/div/div/nb-layout-column/nb-card/nb-card-body/nb-auth-block/ngx-login/form/button")
    WebElement logInButton;
    @FindBy (xpath = "//p[@class=\"alert-title\"]")
    WebElement credentialErrorTitle;
    @FindBy (xpath = "//button[@class=\"status-success appearance-filled full-width size-medium shape-rectangle btn-disabled nb-transition\"]")
    WebElement credentialErrorMessage;


    //Assertion that login is present
    public void verifyThatLoginPageIsPresent (){

        Assert.assertEquals(driver.getCurrentUrl(),"http://34.227.177.61/auth/login");
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


    //Assertion for login button (ENABLED)
    public void verifyThatLoginButtonIsEnabled (){

        WebDriverWait wait = new WebDriverWait(driver,WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(logInButton));
        Assert.assertTrue(logInButton.isEnabled());

    }

    //Assertion for blank email
    public void verifyEmptyEmailErrorIsPresent (){

        Assert.assertEquals(emailErrorMessage.getText(),"Email is required!");

    }

    //Assertion for blank password
    public void verifyEmptyPasswordErrorIsPresent (){

        Assert.assertEquals(passwordInputBar.getText(),"Password is required!");

    }

    //Assertion for invalid email format
    public void verifyInvalidEmailFormat (){
        Assert.assertEquals(emailErrorMessage.getText(), "Email should be the real one!");
    }

    //Assertion for invalid password Format
    public void verifyInvalidPasswordFormat(){
        Assert.assertEquals(passwordErrorMessage.getText(),"Password should contains from 4 to 50 characters");
    }

    //Assertion for wrong credentials
    public void verifyInvalidCredentials (){
        WebDriverWait wait = new WebDriverWait(driver,WAIT);
        wait.until(ExpectedConditions.visibilityOfAllElements(credentialErrorTitle,credentialErrorMessage));
        Assert.assertEquals(credentialErrorTitle.getText(),"Oh snap!");
        Assert.assertEquals(credentialErrorMessage.getText(),"We could not verify your credentials. Please double-check and try again.");

    }

    //Entering email
    public void submitEmail (String email){

        WebDriverWait wait = new WebDriverWait(driver,WAIT);
        wait.until(ExpectedConditions.visibilityOf(emailInputBar));
        emailInputBar.clear();
        emailInputBar.sendKeys(email);

    }

    //Entering password
    public void submitPassword (String password){

        WebDriverWait wait = new WebDriverWait(driver,WAIT);
        wait.until(ExpectedConditions.visibilityOf(passwordInputBar));
        passwordInputBar.clear();
        passwordInputBar.sendKeys(password);

    }

    //Assertion For Successful Login.
    WebDriverWait wait= new WebDriverWait(driver, WAIT);



}
