package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	By searchResultCount=By.xpath("//div[contains(@class,'product-layout')]");
	

	public SearchResultsPage(WebDriver driver)
	{
		// TODO Auto-generated constructor stub
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public int getSearchResultCount()
	{
		return eleUtil.waitForWebElements(searchResultCount,AppConstant.MEDIUM_TIME_OUT).size();
	}
	
	public String getSearchResultsPageTitle()
	{
		return eleUtil.waitforTitleContains(AppConstant.SEARCH_RESULT_PAGE_TITLE_FRACTION,AppConstant.MEDIUM_TIME_OUT);
		
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		eleUtil.clickElementWhenReady(By.linkText(productName),AppConstant.MEDIUM_TIME_OUT);
		return new ProductInfoPage(driver);
	}
	
	

}
