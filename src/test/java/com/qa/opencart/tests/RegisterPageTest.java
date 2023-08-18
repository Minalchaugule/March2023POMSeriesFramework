package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
ExcelUtil excellUtil;
	
	@BeforeClass
	public void RegisterPageTest()
	{
		registerpage=loginpage.navigateToRegisterPage();
	}
	
	
	@Test(priority=1)
	public void getRegisterPageTitleTest()
	{
		String actRegisterPageTitle=registerpage.RegisterPageTitle();
	Assert.assertEquals(actRegisterPageTitle,AppConstant.REGISTER_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void doRegistrationTest()
	{
		Assert.assertTrue(registerpage.doRegistration("Prisha", "chaugule",getRandomEmailID(),"2345678654356","Prish@123","yes"));
		
	}
	
	public String getRandomEmailID()
	{
		return "openauto"+System.currentTimeMillis()+"@open.com";
	}
	@DataProvider
	public Object[][]multipleRegistrationTest()
	{
		return new Object[][] 
				{
			{"User1","Test1" ,"34567789765","Userone@123","yes",},
			{"User2","Test2" ,"34567789766","Usertwo@123","no"},
			{"User3","Test3" ,"34567789767","Userthree@123","yes"},
			{"User4","Test4" ,"34567789768","Userfour@123","no"}
				};
			}


	
	@Test(dataProvider="multipleRegistrationTest",priority=2)
	public void doMultipleRegistrationTest(String ftname,String ltname,String phno,String password,String subscribe)
	{
		Assert.assertTrue(registerpage.doRegistration(ftname, ltname,getRandomEmailID(), phno, password, subscribe));
		
		
	}
	
	@DataProvider
	public Object[][] getUserRegSheetData() {
	
		return ExcelUtil.getTestData(AppConstant.SHEET_NAME);
	}	
	
	
	@Test(dataProvider = "getUserRegSheetData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerpage.doRegistration(firstName, lastName, getRandomEmailID(), telephone,  password,  subscribe));
		
	}


}
