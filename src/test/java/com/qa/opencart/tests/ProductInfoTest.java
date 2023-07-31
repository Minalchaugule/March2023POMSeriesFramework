package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() throws InterruptedException
	{
		accountpage=loginpage.doLogin("minal.chaugule88@gmail.com","Password@123");
		
	}
	
	
	@DataProvider
	public Object[][]ProductTestData()
	{
		return new Object[][] 
				{
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
				};
			}
	
	@Test(dataProvider="ProductTestData")
	public void productHeaderTest(String searchKey ,String productName)
	{
		searchresultspage=accountpage.doSearch(searchKey);	
		productinfopage=searchresultspage.selectProduct(productName);
		String actProductHeader=productinfopage.getProductHeaderValue();
		Assert.assertEquals(actProductHeader,productName);
	}
	
	@DataProvider
	public Object[][]ProductData()
	{
		return new Object[][] 
				{
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Samsung","Samsung Galaxy Tab 10.1",7}
				};
			}
	
	@Test(dataProvider="ProductData")
	public void productImagesCount(String searchKey,String productName,int count)
	{
		searchresultspage=accountpage.doSearch(searchKey);	
		productinfopage=searchresultspage.selectProduct(productName);
		int actProductImageCount=productinfopage.getProcuctImagesCount();
		Assert.assertEquals(actProductImageCount,count);
	}
	
	//AAA-Act Arrange Assert
	@Test
	public void getProductInfoTest()
	{
		searchresultspage=accountpage.doSearch("macbook");	
		productinfopage=searchresultspage.selectProduct("MacBook Pro");	
		Map<String,String>actProductData=productinfopage.getentireProductMetatData();
		System.out.println(actProductData);
		softAssert.assertEquals(actProductData.get("Brand"),"Apple");
		softAssert.assertEquals(actProductData.get("Availability"),"In Stock");
		softAssert.assertEquals(actProductData.get("Product Code"),"Product 18");
		softAssert.assertEquals(actProductData.get("Reward Points"),"800");
		softAssert.assertAll();
		
	}

}
