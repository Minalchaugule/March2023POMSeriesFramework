package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.frameworkException.FrameworkException;

//import CustomException.FrameworkException;

public class ElementUtil {

	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		act=new Actions(driver);
		jsUtil=new JavaScriptUtil(driver);
	}
	
	//getElement method -this is base method
		public  WebElement getElement(By locator)
		{
			WebElement element=driver.findElement(locator);
			if(Boolean.parseBoolean(DriverFactory.highlight))
			{
				jsUtil.flash(element);
			}
			return element;
		}
		
		
		
    //SendKeys method
	public void dosendKeys(By locator ,String value)
	{
		if(value==null)
		{
			System.out.println("Value cannot use null in dosendKeys method ");
			throw new FrameworkException("VALUECAN'TBENULL");
		}
		getElement(locator).sendKeys(value);
	}
	

	
	//click method
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	//getText method
	public String doElementGetText(By locator)
	{
		String text=getElement(locator).getText();
		System.out.println("Element text is : " +text);
		return text;
	}
	
	//is displayed method
	public boolean checkElementIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
		 
	}
	
	//get attribute method
	public String GetElementAttribute(By locator,String attributeName)
	{
		return getElement(locator).getAttribute(attributeName);
	}
	
	//isenabled method
	public boolean elementIsEnabled(By locator)
	{
		//return getElement(locator).isEnabled();
		String disabled_val= driver.findElement(locator).getAttribute("disabled");
		if(disabled_val.equals("true"))
		{
			return true;
		}
		return false;
	}
	
	//findElements method
	public  List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	//find total counts
	public int getTotalCountofElements(By locator)
	{
		return getElements(locator).size();
	}
	
	//getText for the element list
	public  List<String> getElementsTextList(By locator)
	{
		List<WebElement> eleList=getElements(locator);
		List<String> eleTextList=new ArrayList<String>();
		for(WebElement e:eleList)
		{
		String text=e.getText();
		if(!text.isEmpty())
			{
			eleTextList.add(text);
			}
		}
		return eleTextList;
			
	}
	
	public  void ClickonElement(By locator,String linkText)
	{
		List<WebElement> linkList=getElements(locator);
		
	      System.out.println("toal no of links : "+linkList.size());
	      
	      for(WebElement e:linkList)
	      {
	    	  String text=e.getText();
	    	  System.out.println(text);
	    	  if(text.equals(linkText))
	    	  {
	    		  e.click();
	    		  break;
	    	  }
	      }
	}
	
	//search method 
	public void doSearch(By searchLocator,By suggLocator,String searchKey,String suggName  ) throws InterruptedException
	{
		
		dosendKeys(searchLocator,searchKey);
		 Thread.sleep(5000);
		 List <WebElement> suggList=getElements(suggLocator);
		 System.out.println(suggList.size());
		 
		 for(WebElement e: suggList)
		 {
			 String text=e.getText();
			 System.out.println(text);
			 if(text.contains(suggName))
			 {
				 e.click();
				 break;
			 }
		 }
		
	}
	
	//************************Drop down Utils*****************************//
	
public void doSelectDropdownbyIndex(By locator,int index) {
		
		if(index<0)
		{
			System.out.println("Pls pass right index");
			return;
		}
		Select sel=new Select(getElement(locator));
		sel.selectByIndex(index);
	}
	
public void doSelectDropdownbyVisibleText(By locator,String visibleText) {
		
		if(visibleText==null)
		{
			System.out.println("Pls pass correct text");
			return;
		}
		Select sel=new Select(getElement(locator));
		sel.selectByVisibleText(visibleText);
	}

public  void doSelectDropdownbyValue(By locator,String value) {
	
	if(value==null)
	{
		System.out.println("Pls pass correct value");
		return;
	}
	Select sel=new Select(getElement(locator));
	sel.selectByVisibleText(value);
}

public ArrayList<String> selectDropdownOptions(By locator)
{
	Select sel=new Select(getElement(locator));
	  List <WebElement>OptionsList=sel.getOptions();
	  ArrayList<String> optList=new ArrayList<String>();
	  System.out.println(OptionsList.size());
	  
	  for(WebElement e:OptionsList)
		  
	  {
		 
		  String text=e.getText();
		  System.out.println(text);
		  optList.add(text);
		  
	  }
	  return optList;
}

public int getDropdownOptionsCount(By locator)
{
	Select sel=new Select(getElement(locator));
	return sel.getOptions().size();
}


public void doSelectDropdownValueUsinggetOptions(By locator,String valueText)
{
	Select sel=new Select(driver.findElement(locator));
	  List<WebElement>optionsList=sel.getOptions();
	  
	  for(WebElement e:optionsList)
	  {
		  String text=e.getText();
		  System.out.println(text);
		  if(text.contains(valueText))
		  {
			  e.click();
			  break;
			  
		  }

}
}

public  void doSelectDropdownValueUsingLocator(By locator,String valueText)
{
	
	  List<WebElement>optionsList=getElements(locator);
	  
	  for(WebElement e:optionsList)
	  {
		  String text=e.getText();
		  System.out.println(text);
		  if(text.contains(valueText))
		  {
			  e.click();
			  break;
}

}
}
//********************Actions Util***********************//
//rightclick method
public void selectrightClickOptions(By conTextClickLocator,String optionValue)
{
	 act.contextClick(getElement(conTextClickLocator)).perform();	
	 By optionClick=By.xpath("//*[text()='"+optionValue+"']");
	 doClick(optionClick);
}

//level 2 click option method
public  void twoLevelMenuHandling(By Level1Menulocator,By Level2MenuLocator) throws InterruptedException
{
	   act.moveToElement(getElement(Level1Menulocator)).perform();
	   Thread.sleep(2000);
	   doClick(Level2MenuLocator);
}

//
public WebElement getLinkByText(String LinkText)
{
	 return driver.findElement(By.linkText(LinkText));
}

public  void selectMulti4LevelDropdownHandle(By level1Locator,String level2,String level3,String level4) throws InterruptedException
{
	 act.moveToElement(getElement(level1Locator)).perform();
	   Thread.sleep(1500);
	   act.moveToElement(getLinkByText(level2)).perform();
	   Thread.sleep(1500);
	   act.moveToElement(getLinkByText(level3)).perform();
	   Thread.sleep(1500);
	  getLinkByText(level4).click();
}

public  void selectMulti3LevelDropdownHandle(By level1Locator,String level2,String level3) throws InterruptedException
{
	 act.moveToElement(getElement(level1Locator)).perform();
	   Thread.sleep(1500);
	   act.moveToElement(getLinkByText(level2)).perform();
	   Thread.sleep(1500);
	  getLinkByText(level3).click();
}

//sendkeys using act object
public  void DoActionsSendKeys(By locator,String value)
{
	act.sendKeys((getElement(locator)),value).perform();
}

//click using act object
public void DoActionsClick(By locator)
{
	act.click(getElement(locator)).perform();
}

//*********************************************Wait Util*****************************************//
/**
 * An expectation for checking that an element is present on the DOM of a page. 
 * This does not necessarily mean that the element is visible.
 * @param locator
 * @param timeOut
 * @return
 */
public  WebElement waitForElementPresence(By locator ,int timeOut)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	 if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(element);
		}
	 
	 return element;
}

/**
 * An expectation for checking that an element is present on the DOM of a page and visible.
 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
 * @param locator
 * @param timeOut
 * @return
 */

public WebElement waitForElementVisible(By locator,int timeOut)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	 WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	 
	 if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(element);
		}
	 return element;
}


/** 
 * An expectation for checking that there is at least one element present on a web page.
 * @param locator
 * @param timeOut
 * @return
 */

public List<WebElement> waitForWebElements(By locator,int timeOut)
{
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
}
/**
 * An expectation for checking that all elements present on the web page that match the locatorare visible. 
 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
 * @param locator
 * @param timeOut
 * @return
 */

public  List<WebElement> waitForWebElementsVisible(By locator,int timeOut)
{
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
}


/** Wait for title contains
 * 
 */
public  String waitforTitleContains(String titleFraction,int timeOut)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	try {
	if(wait.until(ExpectedConditions.titleContains(titleFraction)))
	{
	return driver.getTitle();
	}
	else
	{
		System.out.println(titleFraction +"Title is not aailable");
		return null;
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(titleFraction +"Title is not aailable");
		return null;	
	}
}

//Wait for exact title

public  String waitforTitleIs(String titleValue,int timeOut)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	try {
	if(wait.until(ExpectedConditions.titleIs(titleValue)))
	{
	return driver.getTitle();
	}
	else
	{
		System.out.println(titleValue +"Title is not aailable");
		return null;
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(titleValue +"Title is not aailable");
		return null;	
	}
}
	
	/**wait for URL contains
	 * 
	 */
public String waitForURLContains(String urlFraction, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	try {
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println(urlFraction + " url value is not present...");
			return null;
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(urlFraction + " url value is not present...");
		return null;
	}
}

	/**wait for exact URL 
	 * 
	 */

	public String waitforurlIs(String urlValue,int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
		if(wait.until(ExpectedConditions.urlToBe(urlValue)))
		{
		return driver.getTitle();
		}
		else
		{
			System.out.println(urlValue +"URL is not available");
			return null;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(urlValue +"URL is not available");
			return null;	
		}
	}
		
		/**Wait for alert to be appeared on page
		 * 
		 */
	
	public Alert waitForJSAlert(int timeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	/**Wait for number of broswer windows
	 * 
	 */
	public  Boolean waitForNoOfBrowserWindows(int timeOut,int numberOfWindowsToBe)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindowsToBe));
			
	}
	/***Wait for Frame to be available and switch to it
	 * 
	 */

	public void waitForFrameByLocator(By FrameLocator ,int timeOut)
	{
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameLocator));	
	}
	
	public void waitForFrameByIndex(int FrameIndex ,int timeOut)
	{
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameIndex));	
	}
	
	public void waitForFrameByFrameNameOrID(String FrameNameorID ,int timeOut)
	{
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameNameorID));	
	}
	
	public void waitForFrameElement(WebElement FrameElement ,int timeOut)
	{
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));	
	}
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void waitforElementToBeClickable(By locator,int timeOut)
	{
		  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;	
	}
	
	//***********************************FluentWait Utils***********************************//
	
	/**
	 * Fluent wait for element visible
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @return
	 */
	
	public WebElement waitForElementVisibleWithFluentWait(By locator,int timeOut,int PollingTimeOut)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(PollingTimeOut))
						.ignoring(NoSuchElementException.class)
							.withMessage("Element is not found after waiting till timeout.. " +locator);
		
      return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * Fluent wait for element present
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @return
	 */
	
	public WebElement waitForElementPresenceWithFluentWait(By locator,int timeOut,int PollingTimeOut)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(PollingTimeOut))
						.ignoring(NoSuchElementException.class)
							.withMessage("Element is not found after waiting till timeout.. " +locator);
		
      return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * Fluent wait for Alert Present
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @return
	 */
	public Alert waitForJSAlertWithFluentWait(int timeOut,int PollingTimeOut)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(PollingTimeOut))
						.ignoring(NoAlertPresentException.class)
							.withMessage("Timeout is done.. No JS Alert found...");
		
      return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * Fluent wait for Frame with id/name
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @return
	 */
	public WebDriver waitForJSFrameWithFluentWait(String FrameNameorID,int timeOut,int PollingTimeOut)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(PollingTimeOut))
						.ignoring(NoSuchFrameException.class)
							.withMessage("Timeout is done.. No frame found with id/name..." +FrameNameorID);
		
      return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameNameorID));
	}
	
	/**
	 * WebDriver wait util for fluent wait feature for element and enter value
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @param value
	 */
	
	public void waitForElementAndEnterValue(By locator,int timeOut,int PollingTimeOut,String value)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait
			.pollingEvery(Duration.ofSeconds(PollingTimeOut))
				.ignoring(NoSuchElementException.class)
					.withMessage("Element is not found with this locator..." +locator)
						.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(value);
		
	}
	
	/**
	 * WebDriver wait util for fluent wait feature for element and click
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @param value
	 */
	
	public void waitForElementAndClick(By locator,int timeOut,int PollingTimeOut)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait
			.pollingEvery(Duration.ofSeconds(PollingTimeOut))
				.ignoring(NoSuchElementException.class)
					.withMessage("Element is not found with this locator..." +locator)
						.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
		
	}
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	/**
	 * Converting WebDriver wait into dynamic wait
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @param value
	 */
	public  WebElement retryingElement(By locator ,int timeOut)
	{
		WebElement element=null;
		int attempts=0;
		
		while(attempts < timeOut) {
			
		try {
			element=getElement(locator);
			System.out.println("Element is found ..." + locator +" in attempt " +attempts);
			break;
		}
		
		catch(NoSuchElementException e)
		{
			System.out.println("Element is found ..." + locator +" in attempt " +attempts);
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e1)
			{
			e1.printStackTrace();
			}
		}
		attempts++;	
		}
		if(element==null)
		{
			System.out.println("Element is found ...we tried for " +attempts +"secs ");	
		}
		return element;
	}
	
	/**
	 * Converting WebDriver wait into dynamic wait with Polling time
	 * @param locator
	 * @param timeOut
	 * @param PollingTimeOut
	 * @param value
	 */
	
	
	public  WebElement retryingElementwithPollingTime(By locator ,int timeOut,long Pollingtime)
	{
		WebElement element=null;
		int attempts=0;
		
		while(attempts < timeOut) {
			
		try {
			element=getElement(locator);
			System.out.println("Element is found ..." + locator +" in attempt " +attempts);
			break;
		}
		
		catch(NoSuchElementException e)
		{
			System.out.println("Element is found ..." + locator +" in attempt " +attempts);
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e1)
			{
			e1.printStackTrace();
			}
		}
		attempts++;	
		}
		if(element==null)
		{
			System.out.println("Element is found ...we tried for " +attempts +"secs "+"with interval of "+Pollingtime+"milli secs");	
		}
		return element;
	}

}
