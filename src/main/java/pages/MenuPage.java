package pages;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseClass;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MenuPage extends BaseClass{
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver
    public MenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    public By clickOrder = By.xpath("//button[normalize-space()='ORDER ONLINE NOW']");
    private By clickAddress = By.xpath("//input[@placeholder='Enter your delivery address']");
    private By clickBox = By.xpath("//input[@placeholder='Enter Area / Locality']");
    private By suggestion = By.xpath("//div[@class='sc-fBuWsC eMOfwp']//child::li[@data-label='location_[object Object]'][1]");
    private By popup = By.xpath("//button[@onclick='moeRemoveBanner()']");
    private By extraPopup = By.xpath("//div[@data-label='add extra cheese']");
    private By checkBox = By.xpath("//input[@data-label='checkbox']");
    private By thanks = By.xpath("//span[normalize-space()='NO THANKS']");
    private By decreasePepsiQty = By.xpath("//div[@data-label='Pepsi 475ml']//following-sibling::div//div[@data-label='decrease']");
    private By checkout = By.xpath("//button[@data-label='miniCartCheckout']");
    private By subtotal = By.xpath("//span[@data-label='total-minicart']");
    private By indivitualPrice = By.xpath("//span[@class='crt-cnt-qty-prc-txt']//child::span");
   
   
    // Click actions
    public void clickOrderOnline() {
    	clickElement(clickOrder);
//        driver.findElement(clickOrder).click();
    }

    public void setClickDelivery() {
        driver.findElement(clickAddress).click();
    }

    public void enderPincode(String postalCode) {
    	enterText(clickBox, postalCode);
    }

    public void selectSuggestion() {
    	clickElement(suggestion);
    }

    public void clickPopup() {
        if (isElement(popup)) {
        	clickElement(popup);
        } else {
            System.out.println("No Popup to close");
        }
    }


    public void clickNoThanks() {
        if (isElement(thanks)) {
        	clickElement(checkBox);
        	clickElement(thanks);
        }  else {
        System.out.println("No Popup to close");
        }
    }

  // Helper method to check if an element is present
    private boolean isElement(By locator) {
        try {
        	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
	public void removeVegPizza(int Quantity, String VegPizza) {
	    	By dec = By.xpath("//div[@data-label='Veg Pizza']//div[@data-label='" + VegPizza + "']//following-sibling::div//child::div[@data-label]//child::div[@data-label='decrease']");
	    	for (int i = 0; i < Quantity; i++) {
	            WebElement marga = wait.until(ExpectedConditions.visibilityOfElementLocated(dec));
	           Actions actions = new Actions(driver);
	           actions.moveToElement(marga).click().perform();
	        }
	    }
    
    public void addVegPizza(String VegPizza) {
    	
        By dynamicProductLocator = By.xpath("//div[@data-label='Veg Pizza']//div[@data-label='" + VegPizza + "']//button[@data-label='addTocart']");
        try {
        	clickElement(dynamicProductLocator);

            clickNoThanks();
        } catch (NoSuchElementException e) {
            System.out.println("Product button not found for: " + VegPizza + " - " + e.getMessage());
        }
    }
    
    public void increaseVegPizza(String VegPizza,int Quantity) {
    	
    	for (int i = 0; i < Quantity-1; i++) {
        By dynamicProductLocator = By.xpath("//div[@data-label='Veg Pizza']//div[@data-label='" + VegPizza + "']//following-sibling::div//div[@data-label='increase']");
        clickElement(dynamicProductLocator);
    	}
    }
      
    
    public void addCooldrinks(String productName) {
        By dynamicProductLocator = By.xpath("//div[@data-label='" + productName + "']//button[@data-label='addTocart']");
        try {
        	clickElement(dynamicProductLocator);
        } catch (NoSuchElementException e) {
            System.out.println("Product button not found for: " + productName + " - " + e.getMessage());
        }
    }
    
    // Method to get the current quantity for a specific product
    public int getProductQuantity(String productName) {
        By productQuantity = By.xpath("//div[@data-label='" + productName + "']//following-sibling::div//div[@data-label='quantity']//child::span");
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productQuantity));
        String quantityText = quantityElement.getText();
        return Integer.parseInt(quantityText);
    }
    
 // Method to click the add button until the desired quantity is reached
    public void addMorequantity(int desiredQuantity, String productName) {
       
        for (int i = 0; i < desiredQuantity-1; i++) {
        	By increaseBtn = By.xpath(" //div[@data-label='" + productName + "']//following-sibling::div//div[@data-label='increase']");
        	 WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(increaseBtn));
        	 productElement.click();		
		}
        int currentQuantity = getProductQuantity(productName);
        
        System.out.println("Desired quantity of " + desiredQuantity + " reached." + currentQuantity);
        
    }
    
    public void clickDecreasePepsiQty(int Quantity) {
    	
    	for (int i = 0; i < Quantity; i++) {
            WebElement Decreasepepsi = wait.until(ExpectedConditions.visibilityOfElementLocated(decreasePepsiQty));
           Decreasepepsi.click();
        }
    }

    // Amount handling

    public double getAllProductTotalAmount() {
        double totalSum = 0.0;
        List<String> amounts = new ArrayList<>();
        List<WebElement> elements = driver.findElements(indivitualPrice);
        
        for (WebElement element : elements) {
            String amount = element.getText().trim(); // Adjust as necessary
            try {
                double amountValue = Double.parseDouble(amount);
                totalSum += amountValue;
                amounts.add(amount);
                System.out.println("Captured Amount: " + amount);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing amount: " + amount);
            }
        }
        
        System.out.println("Total Sum: " + totalSum);
        return totalSum; // Return the total sum instead of the list
    }
 

    public double getSubtotal() {
        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotal));
        String subtotalText = subtotalElement.getText();
        System.out.println("Displayed Subtotal: " + subtotalText);
        
        // Remove commas and convert to double
        double subtotalAmount = Double.parseDouble(subtotalText.replace(",", ""));
        return subtotalAmount;
    }
    
    public void checkoutkart() {
    	clickElement(checkout);
	}
}
