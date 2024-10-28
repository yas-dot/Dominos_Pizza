package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import baseClass.BaseClass;

public class CheckoutPage {
	 private WebDriver driver;
	    private WebDriverWait wait;

	    // Constructor to initialize WebDriver
	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	    
	    
	    private By finalSubtotal = By.xpath("//span[@data-label='Sub Total']//following-sibling::span//child::span");

	    
	    public double getFinalSubtotal() throws InterruptedException {
	    	Thread.sleep(2000);
	        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(finalSubtotal));
	        String subtotalText = subtotalElement.getText();
	        System.out.println("Displayed Subtotal: " + subtotalText);
	        
	        // Remove commas and convert to double
	        double subtotalAmount = Double.parseDouble(subtotalText.replace(",", ""));
	        return subtotalAmount;
	    }
	    
}

