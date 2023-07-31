package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private by locators -page locator
	
	private By Logout_Link=By.linkText("Logout");
	private By AccountHeader=By.cssSelector("div#content h2");
	private By Search=By.name("search");
	private By Search_icon=By.xpath("//span[@class='input-group-btn']");
	
	
	public AccountPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	public String AccountPageTitle()
	{
	return eleUtil.waitforTitleContains(AppConstant.ACCOUNTS_PAGE_TITLE,AppConstant.SHORT_TIME_OUT);	
	}
	
	public boolean LogoutLinkisExist()
	{
		return eleUtil.waitForElementPresence(Logout_Link,AppConstant.SHORT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountPageHeader()
	{
		List<WebElement>headerList=eleUtil.waitForWebElementsVisible(AccountHeader,AppConstant.MEDIUM_TIME_OUT);
		List<String>headerValueList=new ArrayList<String>();
		for(WebElement e:headerList)
		{
			String header=e.getText();
			headerValueList.add(header);
			System.out.println("Header list values are :" +headerValueList);
		}
		return headerValueList;
	}
	
	public int getAccountHeaderSize()
	{
		return eleUtil.waitForWebElementsVisible(AccountHeader,AppConstant.MEDIUM_TIME_OUT).size();
	}
	
	public SearchResultsPage doSearch(String searchKey)
	{
		eleUtil.waitForElementVisible(Search, AppConstant.MEDIUM_TIME_OUT).clear();
		eleUtil.waitForElementVisible(Search, AppConstant.MEDIUM_TIME_OUT).sendKeys(searchKey);
		
		eleUtil.doClick(Search_icon);
		return new SearchResultsPage(driver);//TDD approach
	}

}
