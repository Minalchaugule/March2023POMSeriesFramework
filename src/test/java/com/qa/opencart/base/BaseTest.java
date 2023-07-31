package com.qa.opencart.base;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;


public class BaseTest {
	
	WebDriver driver;
	protected LoginPage loginpage;
	protected AccountPage accountpage;
	protected SearchResultsPage searchresultspage;
	protected ProductInfoPage productinfopage;
	protected RegisterPage registerpage;
	
	DriverFactory df;
	public Properties prop;
	
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void Setup()
	{
		df=new DriverFactory();
		 
			prop=df.initProp();
		
		 
		driver=df.initDriver(prop);
		loginpage=new LoginPage(driver);
		softAssert=new SoftAssert();
	    
	}
	
	@AfterTest
	public void Teardown()
	{
		driver.quit();
	}
	
	
	

}
