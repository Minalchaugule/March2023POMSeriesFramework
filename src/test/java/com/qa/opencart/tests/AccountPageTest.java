package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.utils.ExcelUtil;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void AccountPageSetup() throws InterruptedException
	{
		 accountpage=loginpage.doLogin("minal.chaugule88@gmail.com","Password@123");
		
	}
	@Test
	public void getAccountPageTitleTest()
	{
		String actAccPageTitle=accountpage.AccountPageTitle();
		Assert.assertEquals(actAccPageTitle,AppConstant.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void LogoutLinkIsExist()
	{
		 Assert.assertTrue(accountpage.LogoutLinkisExist());
	}
	
	@Test
	public void getAccountPageHeaderCount()
	
	{
		int actaccPageHeaderCount=accountpage.getAccountHeaderSize();
		System.out.println("Actual account header size is :" +actaccPageHeaderCount);
		Assert.assertEquals(actaccPageHeaderCount,AppConstant.ACCOUNTPAge_HEADER_COUNT);
	}
	
	@Test
	public void getAccountPageHeaders()
	{
		List<String> accHeadList=accountpage.getAccountPageHeader();
		Assert.assertEquals(accHeadList,AppConstant.EXP_ACC_HEADER_LIST);
	}
	
	@DataProvider
	public Object[][]SearchProductTest()
	{
		return new Object[][] 
				{
			{"macbook",3},
			{"samsung",2},
			{"imac",1},
			{"canon",1}
				};
			}
	
	@Test(dataProvider="SearchProductTest")
	public void searchTest(String searchValue,int searchKey)
	{
		searchresultspage=accountpage.doSearch(searchValue);
		int actResultCount=searchresultspage.getSearchResultCount();
		Assert.assertEquals(actResultCount,searchKey);
		System.out.println("search value is : "+searchValue+ " & count is : "+actResultCount);
	}
	
	
	@DataProvider
	public Object[][] getAccountSheetData() {
		return ExcelUtil.getTestData(AppConstant.ACCOUNT_SHEET_NAME);
	}	
	
	
	@Test(dataProvider = "getAccountSheetData")
	public void searchUsingExcelUtilTest(String searchKey,String searchValue)
	{
		searchresultspage=accountpage.doSearch(searchKey);
		int actResultCount=searchresultspage.getSearchResultCount();
		//Assert.assertEquals(actResultCount,searchValue);
		System.out.println("search value is : "+searchValue+ " & count is : "+actResultCount);
	}
	}

	


