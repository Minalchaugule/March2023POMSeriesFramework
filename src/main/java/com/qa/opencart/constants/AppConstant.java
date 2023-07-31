package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstant {
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
	
	
	public static final String ACCOUNTS_PAGE_TITLE="My Account";
	public static final int ACCOUNTPAge_HEADER_COUNT=4;
	public static final List<String>EXP_ACC_HEADER_LIST=Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	//********Search Result Page******************??
	public static final String SEARCH_RESULT_PAGE_TITLE_FRACTION="Search - ";
	
	
//**********register page******************//
	
	public static final String REGISTER_PAGE_TITLE="Register Account";
	public static final String REGISTER_PAGE_SUCCESS_MESSAGE="Your Account Has Been Created!";
	
	
	
	//*******************default timeout values*******************//
	public static final int SHORT_TIME_OUT=5;
	public static final int MEDIUM_TIME_OUT=10;
	public static final int LONG_TIME_OUT=15;
	
	//*******************excel sheet*****************//
	public static final String SHEET_NAME = "register";
	public static final String ACCOUNT_SHEET_NAME = "accounts";
	

}
