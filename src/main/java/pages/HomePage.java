package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get the page title
    public String getHomePageTitle() {
        return driver.getTitle();
    }

    // Method to navigate to the home page
    public void navigateToHomePage() {
        driver.get("https://www.dominos.co.in/");
    }

    // Locators
    private By clickOrder = By.xpath("//button[normalize-space()='ORDER ONLINE NOW']");
    private By clickAddress = By.xpath("//input[@placeholder='Enter your delivery address']");
    private By clickBox = By.xpath("//input[@placeholder='Enter Area / Locality']");
    private By suggestion = By.xpath("//div[@class='sc-fBuWsC eMOfwp']//child::li[@data-label='location_[object Object]'][1]");
    private By popup = By.xpath("//button[@onclick='moeRemoveBanner()']");
    private By checkBox = By.xpath("//input[@data-label='checkbox']");
    private By thanks = By.xpath("//span[normalize-space()='NO THANKS']");

    // Method to click on 'Order Online' button
    public void clickOrderOnline() {
        clickElement(clickOrder);
    }

    // Method to click on delivery address input
    public void setClickDelivery() {
        clickElement(clickAddress);
    }

    // Method to enter the postal code
    public void setClickBox(String pinCode) {
        WebElement boxElement = driver.findElement(clickBox);
        boxElement.clear();
        boxElement.sendKeys(pinCode);
    }

    // Method to select the first suggestion
    public void selectSuggestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement suggestionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
        suggestionElement.click();
    }

    // Method to click the popup button if visible
    public void clickPopup() {
        clickElementIfVisible(popup);
    }

    // Method to click the checkbox if visible
    public void clickCheckBox() {
        clickElementIfVisible(checkBox);
    }

    // Method to click 'No Thanks' button if visible
    public void noThanks() {
        clickElementIfVisible(thanks);
    }

    // Helper method to click an element
    private void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    // Helper method to click an element if it's visible
    private void clickElementIfVisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                element.click();
            }
        } catch (Exception e) {
            System.out.println("Element not found: " + locator);
        }
    }
}

