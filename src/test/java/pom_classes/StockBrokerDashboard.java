package pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class StockBrokerDashboard extends StockBrokerCommon{
    public StockBrokerDashboard(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }





}
