package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void goToSearchPageTest() throws InterruptedException
	{
		accountpage=loginpage.doLogin("minal.chaugule88@gmail.com","Password@123");
		searchresultspage=accountpage.doSearch("macbook");
	}
	@Test
	public void getSearchResultPageTitleTest()
	{
		String searchpageTitle=searchresultspage.getSearchResultsPageTitle();
		Assert.assertTrue(searchpageTitle.contains(AppConstant.SEARCH_RESULT_PAGE_TITLE_FRACTION));
	}
	

		
		
}
