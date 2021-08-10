package Selenium_core;

public class DriverManagerFactory {
    public static DriverManager getDriverManager(String driverType) {
        DriverManager driverManager;

        switch (driverType) {
            case "Chrome": {
                driverManager = new ChromeDriverManager();
            }
            break;
            default:
                driverManager = new ChromeDriverManager();
        }
        return driverManager;
    }
}