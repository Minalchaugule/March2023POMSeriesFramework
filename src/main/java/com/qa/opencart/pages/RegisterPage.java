package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;

import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}
	
private By firstname=By.id("input-firstname");
private By lastname=By.id("input-lastname");
private By email=By.id("input-email");
private By phnumber=By.id("input-telephone");
private By pwd=By.id("input-password");
private By confirmpwd=By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']");
	
	private By agree_checkbox=By.xpath("//input[@name='agree']");
	private By continue_btn=By.xpath("//input[@type='submit']");
	
	
	private By successmessage=By.cssSelector("div#content h1");
	private By continue_button=By.linkText("Continue");
	private By Logout_link=By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	
	
	public String RegisterPageTitle()
	{
	return eleUtil.waitforTitleContains(AppConstant.REGISTER_PAGE_TITLE,AppConstant.SHORT_TIME_OUT);	
	}
	
	public boolean doRegistration(String firsttname,String lasttname,String email,String phnumber,String pwd,String subscribe)
	{
		System.out.println("Started Registration process now :");
		eleUtil.waitForElementVisible(this.firstname,AppConstant.MEDIUM_TIME_OUT).sendKeys(firsttname);
		eleUtil.dosendKeys(this.lastname, lasttname);
		eleUtil.dosendKeys(this.email, email);
		eleUtil.dosendKeys(this.phnumber,phnumber);
		eleUtil.dosendKeys(this.pwd, pwd);
		eleUtil.dosendKeys(this.confirmpwd,pwd);
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);	
		}
		eleUtil.doClick(agree_checkbox);
		eleUtil.doClick(continue_btn);
		
	String successmsg=eleUtil.waitForElementVisible(successmessage,AppConstant.MEDIUM_TIME_OUT).getText();
	System.out.println(successmsg);
	if(successmsg.contains(AppConstant.REGISTER_PAGE_SUCCESS_MESSAGE))
	{
		eleUtil.doClick(Logout_link);
		eleUtil.doClick(registerLink);
		return true;
	}
	return false;
	
	}
	
	public AccountPage moveBacktoAccountPage()
	{
		eleUtil.clickElementWhenReady(continue_button,AppConstant.SHORT_TIME_OUT);
		return new AccountPage(driver);
	}
	
	public void DoLogoutPage() {
		eleUtil.waitForElementPresence(Logout_link,AppConstant.SHORT_TIME_OUT);
	}
	
	
	

}
