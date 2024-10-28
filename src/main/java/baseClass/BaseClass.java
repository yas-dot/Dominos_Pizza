package baseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BaseClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
        // Initialize WebDriverWait when the driver is set
        wait.set(new WebDriverWait(driverInstance, Duration.ofSeconds(10))); // Set your timeout here
    }

    public static WebDriverWait getWait() {
        return wait.get();
    }

    public static void closeDriver() {
        WebDriver driverInstance = driver.get();
        if (driverInstance != null) {
            driverInstance.quit();
            driver.remove();
            wait.remove(); // Clean up the WebDriverWait as well
        }
    }

    public static void initializeDriver(String browser) {
        WebDriver driverInstance;

        switch (browser.toLowerCase()) {
            case "chrome":
                driverInstance = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driverInstance = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        setDriver(driverInstance);
        getDriver().manage().window().maximize();
    }

    // Example of using the WebDriverWait
    public void clickElement(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Clicked on element: " + locator);
        } catch (Exception e) {
            System.err.println("Failed to click on element: " + locator + ". Error: " + e.getMessage());
        }
    }
    
    public void enterText(By locator, String text) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear(); // Clear existing text
            element.sendKeys(text);
            System.out.println("Entered text: '" + text + "' into element: " + locator);
        } catch (Exception e) {
            System.err.println("Failed to enter text into element: " + locator + ". Error: " + e.getMessage());
        }
    }
}
