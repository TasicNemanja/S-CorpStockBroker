package pom_classes;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    WebElement Header;




    public void verifyThatUserIsOnDashboardPage (){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Assert.assertEquals(driver.getCurrentUrl(), "http://34.227.177.61/pages/dashboard");
    }









}
