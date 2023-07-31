package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private by locators -page locator
	
	private By email=By.id("input-email");
	private By password=By.id("input-password");
	private By Login_btn=By.xpath("//input[@value='Login']");
	private By ForgotLink=By.linkText("Forgotten Password");
	private By registerlink=By.linkText("Register");
	
	//public page constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//Actions
	@Step("....getting login page title....")
	public String getLoginPageTitle()
	{
		String title=eleUtil.waitforTitleIs(AppConstant.LOGIN_PAGE_TITLE,AppConstant.SHORT_TIME_OUT);
		System.out.println("Login page title is :"+title);
		return title;
	}
	
	@Step("....getting login page url....")
	public String getLoginPageUrl()
	{
		String url=eleUtil.waitForURLContains(AppConstant.LOGIN_PAGE_URL_FRACTION,AppConstant.MEDIUM_TIME_OUT);
		System.out.println("Login page url is :"+url);
		return url;
	}
	
	@Step("...checking for forgot password link ....")
	public boolean isForgotPasswordLinkDisplayed()
	{
		return eleUtil.waitForElementVisible(ForgotLink,AppConstant.MEDIUM_TIME_OUT).isDisplayed();
		
	}
	
	@Step("....logging to app using crdentials with username :{0} and password : {1}....")
	public AccountPage doLogin(String username,String pwd) throws InterruptedException
	{
		System.out.println("App credentials are :" +username+ ":"+pwd);
		eleUtil.waitForElementVisible(email,AppConstant.MEDIUM_TIME_OUT).sendKeys(username);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(Login_btn);
		//return eleUtil.waitforTitleIs(AppConstant.ACCOUNTS_PAGE_TITLE,AppConstant.SHORT_TIME_OUT);
		return new AccountPage(driver);
	
		}
	
	
	@Step("....navigating to register page...")
	public RegisterPage navigateToRegisterPage()
	{
	 eleUtil.waitForElementVisible(registerlink,AppConstant.LONG_TIME_OUT).click();;
	 return new RegisterPage(driver);
	}
}
