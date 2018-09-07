package businessComponent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

public class SeleniumHelper {
	//public static WebDriver driver=null;
	public static WebDriver driver;
	 public SeleniumHelper(WebDriver driver){
		//driver=null;
	}

	public static void initilazedriver(String browser){
		if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sanke\\Documents\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
			
		}
		else{
			driver= new FirefoxDriver();
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		}
		
		
	}
	
	public static void quit() {
		//driver.quit();
	}
	public void get(String url) {
		driver.get(url);
	}
	
	
	Boolean istextPresent(String text){
		try{
			boolean flag=driver.getPageSource().contains(text);
			return flag;
		}
		catch(Exception e){
			return false ;
			
		}
		
	}
	
	public boolean isElementDisplayed(elementSelector selector)
	{
		boolean isElementFound=false;
		try
		{
			isElementFound=this.findelement(selector).isDisplayed();
			return isElementFound;
		}
		catch(NotFoundException ex)
		{
			return isElementFound;
		}
		catch (TimeoutException ex) 
		{
			return isElementFound;
		}
	}
	public  Element findelement(elementSelector ElementSelector){
		Element element=new Element(null, driver.findElement(ElementSelector.selectBy));
		return element;
		
	}
	
	
	public List<Element> findElements(elementSelector ElementSelector)
	{
		List<WebElement> elements=driver.findElements(ElementSelector.selectBy);
		
		List<Element> newElements=new ArrayList<Element>();
		for(WebElement ele:elements)
		{
			newElements.add(new Element(this, ele));
		}
		return newElements;
		
	}
	
}
