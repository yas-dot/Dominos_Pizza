package stepdefine;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import baseClass.BaseClass;
import pages.CheckoutPage;
import pages.MenuPage;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Stepdefine extends BaseClass {
    private WebDriver driver;
    private MenuPage menuPage;
    private CheckoutPage checkoutPage;
    private double totalProductAmount;
    	

    @Given("I launch the {string} browser")
    public void i_launch_the_browser(String browser) {
        // Initialize WebDriver based on the provided browser type
        initializeDriver(browser);
        driver = getDriver();
        menuPage = new MenuPage(driver);
        checkoutPage =  new CheckoutPage(driver);
    }

    @When("I navigate to the homepage")
    public void i_navigate_to_the_homepage() {
        menuPage.navigateToHomePage();
    }

    @Then("I verify that the page title is displayed correctly")
    public void i_verify_that_the_page_title_is_displayed_correctly() {
        String title = menuPage.getHomePageTitle();
        System.out.println("Page title: " + title);
    }

    @Then("I click on {string}")
    public void i_click_on(String buttonName) {
    	 menuPage.clickOrderOnline();
    	 System.out.println("Order clicked");
    }

    @Then("I enter the address and pin code {string}, selecting the first suggestion")
    public void i_enter_the_address_and_pin_code_selecting_the_first_suggestion(String PinCode) throws InterruptedException {
        menuPage.setClickDelivery(); // Open delivery address field
        menuPage.enderPincode(PinCode); // Adjust to enter a valid pin code
        Thread.sleep(5000);
        menuPage.selectSuggestion(); // Select the first suggestion
        System.out.println("Pin clicked");
        Thread.sleep(5000);
    }
    
    @Then("I navigate to the Veg Pizza section to add {string} with a quantity of {int}")
    public void i_navigate_to_the_veg_pizza_section_to_add_with_a_quantity_of(String Piza, Integer Quantity) {
    	
    	menuPage.addVegPizza(Piza);
    	menuPage.increaseVegPizza(Piza, Quantity);
  
    }
 
    @Then("add {int} {string} pizzas and {int} {string} pizzas to my cart")
    public void add_pizzas_and_pizzas_to_my_cart(Integer quantity1, String pizza1, Integer quantity2, String pizza2) {
    	menuPage.addVegPizza(pizza1);
    	menuPage.increaseVegPizza(pizza1, quantity1);
    	menuPage.addVegPizza(pizza2);
    	menuPage.increaseVegPizza(pizza2, quantity2);
    
    }

    @Then("go to Beverages section and select {string} with a quantity of {int}")
    public void go_to_beverages_section_and_select_with_a_quantity_of(String beverageName, Integer quantity) {
        
    	menuPage.addCooldrinks(beverageName);
    	menuPage.addMorequantity(quantity, beverageName);
    	
    	
    }	

    @Then("I verify the sum of each product's quantities and their total amount")
    public void i_verify_the_sum_of_each_product_s_quantities_and_their_total_amount() {
    	double finalSubtotal = menuPage.getAllProductTotalAmount(); // Retrieve amounts for validation
    	 totalProductAmount = menuPage.getSubtotal();
    	double delta = 0.01;
        // Assert that both values are equal
        Assert.assertEquals("Total amounts do not match!", finalSubtotal, totalProductAmount, delta);
        System.out.println("Assertion Passed: Both values are equal.");
        
    }

    @Then("I remove {int} {string} pizza from my cart")
    public void i_remove_pizza_from_my_cart(Integer quantity, String pizzaName) {
    	menuPage.removeVegPizza(quantity, pizzaName);
       
    }

    @Then("remove {int} {string} from my cart")
    public void remove_from_my_cart(Integer quantity, String beverageName) {
        menuPage.clickDecreasePepsiQty(quantity);
    }

    @Then("verify that the cart subtotal matches the sum of the remaining products values and quantities")
    public void verify_that_the_cart_subtotal_matches_the_sum_of_the_remaining_products_values_and_quantities() {
    	double productSubtotal = menuPage.getAllProductTotalAmount(); // Retrieve amounts for validation
    	totalProductAmount = menuPage.getSubtotal();
    	double delta = 0.01;
        // Assert that both values are equal
        Assert.assertEquals("Total amounts do not match!", productSubtotal, totalProductAmount, delta);
        System.out.println("Assertion Passed: Both values are equal.");
    }

    @Then("checkout and validate subtotal")
    public void checkout_and_validate_subtotal() throws InterruptedException {
    	menuPage.checkoutkart();
    	double FinalSubtotal = checkoutPage.getFinalSubtotal();
    	double delta = 0.01;
        // Assert that both values are equal
        Assert.assertEquals("Total amounts do not match!", totalProductAmount, FinalSubtotal, delta);
        System.out.println("Assertion Passed: Both values are equal.");
    	
    }

    
}
