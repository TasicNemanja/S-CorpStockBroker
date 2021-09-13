package pom_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.Assertion;

public class StockBrokerProfilePage extends StockBrokerCommon{
    public StockBrokerProfilePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (id = "inputFirstName")
    WebElement firstName;
    @FindBy (id = "inputLastName")
    WebElement lastName;
    @FindBy (id = "inputEmail")
    WebElement profileEmail;
    @FindBy (id = "inputPassword")
    WebElement newPassword;
    @FindBy (id = "inputPassword2")
    WebElement confirmPassword;
    @FindBy (xpath = "//button[text()='Submit']")
    WebElement submitButton;
    @FindBy (xpath = "//button[@class=\"select-button\"]")
    WebElement themeDropDown;
    @FindBy (id = "nb-option-0")
    WebElement lightTheme;
    @FindBy (id = "nb-option-1")
    WebElement darkTheme;
    @FindBy (id = "nb-option-2")
    WebElement cosmicTheme;
    @FindBy (id = "nb-option-3")
    WebElement corporateTheme;
    @FindBy (xpath = "//span[@class=\"title subtitle\"]")
    WebElement changedUserDataMessageTitle;
    @FindBy (xpath = "//div[@class=\"message\"]")
    WebElement changedUserDataMessage;
    @FindBy (xpath = "//label[text()=' Must have at least one number, one lowercase and one uppercase letter. ']")
    WebElement emptyNewPasswordMessage;
    @FindBy (xpath = "//label[text()=' Passwords must match. ']")
    WebElement emptyConfirmPasswordMessage;

    public void changeFirstName(String name){
        waiting(firstName);
        enterText(firstName, name);
        clickOnElement(submitButton);
        Assert.assertEquals(changedUserDataMessageTitle.getText(),"Success");
        Assert.assertEquals(changedUserDataMessage.getText(), "Profile Data has been updated!");
    }
    public void changeLastName(String name){
        waiting(lastName);
        enterText(lastName, name);
        clickOnElement(submitButton);
        Assert.assertEquals(changedUserDataMessageTitle.getText(),"Success");
        Assert.assertEquals(changedUserDataMessage.getText(), "Profile Data has been updated!");
    }
    public void changeEmail(String email){
        waiting(profileEmail);
        enterText(profileEmail, email);
        clickOnElement(submitButton);
        Assert.assertEquals(changedUserDataMessageTitle.getText(),"Success");
        Assert.assertEquals(changedUserDataMessage.getText(), "Profile Data has been updated!");

    }
    public void verifyThatUserCantChangePasswordWithEmptyFields(){
        clickOnElement(newPassword);
        clickOnElement(confirmPassword);
        Assert.assertEquals(emptyNewPasswordMessage.getText(), "Must have at least one number, one lowercase and one uppercase letter.");
        Assert.assertEquals(emptyConfirmPasswordMessage.getText(),"Passwords must match.");
        Assert.assertFalse(submitButton.isEnabled());
    }
    public void verifyThatUserCanNotChangePasswordWithInvalidPassFormat(String invalidPassFormat){
        newPassword.clear();
        newPassword.sendKeys(invalidPassFormat);
        confirmPassword.clear();
        confirmPassword.sendKeys(invalidPassFormat);
        Assert.assertEquals(emptyNewPasswordMessage.getText(), "Must have at least one number, one lowercase and one uppercase letter.");
        Assert.assertEquals(emptyConfirmPasswordMessage.getText(),"Passwords must match.");
        Assert.assertFalse(submitButton.isEnabled());

    }
    public void verifyThatUserIsAbleToChangePassword(String passwordAtLeastOneNumberOneLowercaseOneUppercase){
        waiting(newPassword);
        enterText(newPassword, passwordAtLeastOneNumberOneLowercaseOneUppercase);
        enterText(confirmPassword, passwordAtLeastOneNumberOneLowercaseOneUppercase);
        clickOnElement(submitButton);
        Assert.assertTrue(submitButton.isEnabled());
        Assert.assertEquals(changedUserDataMessageTitle.getText(),"Success");
        Assert.assertEquals(changedUserDataMessage.getText(), "Profile Data has been updated!");

    }
    public void changeTheme(String theme){
        clickOnElement(themeDropDown);
    }




}

