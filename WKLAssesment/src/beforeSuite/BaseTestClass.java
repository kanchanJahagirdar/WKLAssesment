package beforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import businessComponent.SeleniumHelper;


public class BaseTestClass {
	public WebDriver driver;
	public BaseTestClass() {
	}

	@BeforeMethod
	public void beforeBaseMethod(Method res) {
		SeleniumHelper.initilazedriver("chrome");
	}

	@AfterMethod
	public void afterBaseMethod() {
		SeleniumHelper.quit();
	}


}
