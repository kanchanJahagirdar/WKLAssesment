package businessSuite;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import beforeSuite.BaseTestClass;
import businessComponent.ExecutionLogic;

public class ShoppingBol extends BaseTestClass{
	
	public ExecutionLogic exeLogic;
	public WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() {
		exeLogic=new ExecutionLogic(driver);
		
	}
	
	@Parameters({"URL","EmailId","Password","Product"})
	@Test(priority = 1) 
	public void executeTestCase(String URL,String emailId,String password,String Product)  
	{
		exeLogic.loginToApplication(URL,emailId,password);
		exeLogic.emptyCart();
		exeLogic.searchProduct(Product);
		exeLogic.addToCart();
		exeLogic.checkPrice();
	}
	
}

