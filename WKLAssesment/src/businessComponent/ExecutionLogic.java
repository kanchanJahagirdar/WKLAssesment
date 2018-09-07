package businessComponent;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;


public class ExecutionLogic extends SeleniumHelper{
	public static final elementSelector linkInloggen =elementSelector.byXPath("//a[@href='/nl/rnwy/account/overzicht']");
	public static final elementSelector txtUserId =elementSelector.byXPath("//input[@id='login_email']");
	public static final elementSelector txtPassword =elementSelector.byXPath("//input[@id='login_password']");
	public static final elementSelector btnLogin =elementSelector.byXPath("//input[@value='Inloggen']");
	public static final elementSelector searchFor =elementSelector.byId("searchfor");
	public static final elementSelector continueSearch =elementSelector.byXPath("//div[@class='fluid-grid__item']");
	public static final elementSelector clickShop =elementSelector.byXPath("//div[contains(@class,'h-boxedright--xs')]");
	public static final elementSelector linkSearch =elementSelector.byXPath("//a[text()='Google Chromecast - Media Streamer']");
	public static final elementSelector addToBasket =elementSelector.byXPath("//a[@data-test='add-to-basket']");
	public static final elementSelector continueShop =elementSelector.byXPath("//a[@data-test='btn-continue-shopping']");
	public static final elementSelector continueOrder =elementSelector.byId("continue_ordering_bottom");
	public static final elementSelector myOrders =elementSelector.byXPath("//div[contains(@class,'fluid-grid--rev')]/div[2]/ul/li[8]/a");
	//public static final elementSelector myBasket =elementSelector.byId("//a[@id='basket']");
	public static final elementSelector myBasket =elementSelector.byXPath("//div[@class='basket__button']"); 
	public static final elementSelector gridOrders =elementSelector.byXPath("//fieldset[contains(@class,'fluid-grid--auto')]");
	public static final elementSelector removeBasket =elementSelector.byXPath("//a[@id='tst_remove_from_basket']");
	public static final elementSelector isEnabled =elementSelector.byXPath("//a[@id='tst_remove_from_basket']");
	public static final elementSelector isDisplayed =elementSelector.byXPath("//div[@class='fluid-grid__item']");
	public static final elementSelector btnSearch =elementSelector.byXPath("//input[@data-test='search-button']");
	public static final elementSelector totalPrice =elementSelector.byXPath("//span[contains(@class,'pricewrap')]");
	public static final elementSelector basketBottom =elementSelector.byXPath("//input[@class='js_basket_bottom_button']");
	public static final elementSelector shippingVal =elementSelector.byXPath("//td[@data-content-section='shipping_value']");
	

	public ExecutionLogic(WebDriver driver) {
		super(driver); 
	}

	public void loginToApplication(String URL,String userId ,String password){
		get(URL);
		findelement(linkInloggen).click();
		findelement(txtUserId).setText(userId);
		findelement(txtPassword).setText(password);
		findelement(btnLogin).click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		
	}
	public void emptyCart(){
		
		//Navigate to my orders
		
		//Empty the cart before proceeding further
		findelement(myBasket).click();
		
		int count=findElements(gridOrders).size();
		//count =0 means your cart is already empty
		if (count!=0) 
		{
			if(findelement(isEnabled).isEnabled())
			{
				//delete the item present in cart
				findelement(removeBasket).click();
				if(findelement(isDisplayed).isDisplayed())
				{
					System.out.println("Cart is empty");				
				}		
		     }
	     }
		
	}
	public void searchProduct(String productId){
		
		//Click on continue shopping
		findelement(continueSearch).click();
		//Click on to the shop
		//findelement(clickShop).click();
		Actions a=new Actions(driver);
		findelement(searchFor).sendKeys(productId);
		findelement(btnSearch).click();	
		a.moveToElement(driver.findElement(By.xpath("//a[text()='Google Chromecast - Media Streamer']"))).click().build().perform();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		
	}
	public void addToCart(){
		
		findelement(addToBasket).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		findelement(continueShop).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		findelement(continueOrder).click();
	}
	
	public void checkPrice()
	{
		String Total_price=findelement(totalPrice).getText().substring(1);
		String tempArr[]=Total_price.split(",");
		int resultPrice = Integer.parseInt(tempArr[0].trim());
		System.out.println(resultPrice);
		//Continue to order
		offerValidation(resultPrice);
	}
	
	public void offerValidation(int resultPrice)
	{
		System.out.println(resultPrice);
		String Shipping_Value = findelement(shippingVal).getText();
		if (resultPrice>20) {
			
			if (Shipping_Value.equalsIgnoreCase("Gratis"))
				System.out.println("Functionality is working fine:Shipping is free");
			else
				System.out.println("Issue in Functionality");
		}
		else {
			System.out.println("Shipping is not free");
		}
			
	}
	
	
}
