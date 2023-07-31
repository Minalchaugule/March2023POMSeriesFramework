package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.pages.AccountPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Listeners(value={"ExtentReportListener.class"})

@Epic("")
@Story("")
public class LoginPageTest extends BaseTest {
	
@Description("This is login page title test")
@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void getLoginPageTitleTest()
	{
	String actTitle=loginpage.getLoginPageTitle();
	Assert.assertEquals(actTitle,AppConstant.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void getLoginPageUrlTest()
	{
		String acturl=loginpage.getLoginPageUrl();
		Assert.assertTrue(acturl.contains(AppConstant.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test(priority=3)
	public void checkForgotPasswordLink()
	{
		Assert.assertTrue(loginpage.isForgotPasswordLinkDisplayed());
		
	}
	
	@Test(priority=4)
	public void dologin() throws InterruptedException
	{
		AccountPage accPageTitle=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password")); //use prop.property when reading from property file
		//AccountPage accPageTitle=loginpage.doLogin(prop.getProperty("username"),System.getProperty("Apppassword")); //use System.property when running from environment
//		Assert.assertEquals(accPageTitle,AppConstant.ACCOUNTS_PAGE_TITLE);
		
		
	}
	
	@Test(priority=5)
	public void clickonRegisterLinkTest()
	{
		loginpage.navigateToRegisterPage();
	}

}
