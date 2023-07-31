package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//private by locators -page locator
	
	private By productHeader=By.cssSelector("div#content h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By quantity=By.name("quantity");
	private By addToCartBtn=By.id("button-cart");
	private By productmetadata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productpricedata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String,String>ProductMap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	
	@Test
	public String getProductHeaderValue()
	{
	return eleUtil.doElementGetText(productHeader)	;
	}
	
	@Test
	public int getProcuctImagesCount()
	{
	int actProductimgcount=eleUtil.waitForWebElementsVisible(productImages,AppConstant.MEDIUM_TIME_OUT).size();
	System.out.println("Total product image count is : "+actProductimgcount);
	return actProductimgcount;
	}
	
	@Test
	private void getproductMetaData()
	{
		List<WebElement> metaList=eleUtil.waitForWebElementsVisible(productmetadata, AppConstant.MEDIUM_TIME_OUT);
		//Map<String,String> metaMap=new HashMap<String,String>();
		for(WebElement e:metaList)
		{
			String metadata=e.getText();
			String key=metadata.split(":")[0].trim();
			String value=metadata.split(":")[1].trim();
			ProductMap.put(key, value);
			
		}
		//return metaMap;
	}
	
	@Test
	private void getproductPriceData()
	{
		List<WebElement> metapriceList=eleUtil.waitForWebElementsVisible(productpricedata, AppConstant.MEDIUM_TIME_OUT);
		//Map<String,String> metaMap=new HashMap<String,String>();
		
		String actPrice= metapriceList.get(0).getText().trim();
		String taxvalue=metapriceList.get(1).getText().split(":")[0].trim();
		String extaxValue=metapriceList.get(1).getText().split(":")[0].trim();
		
			ProductMap.put(actPrice,extaxValue);
			ProductMap.put(taxvalue, extaxValue);
			
		//return metaMap;
	}
	
	@Test
public Map<String, String> getentireProductMetatData()
{
	ProductMap=new HashMap<String,String>();
		ProductMap.put("productheader",getProductHeaderValue());
		ProductMap.put("productImages",String.valueOf(getProcuctImagesCount()));	
	getproductMetaData();
	getproductPriceData();
	return ProductMap;
		
		
	}
	
	
	

}
