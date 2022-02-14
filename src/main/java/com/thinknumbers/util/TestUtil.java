package com.thinknumbers.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thinknumbers.base.BaseTest;

public class TestUtil extends BaseTest {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/java/com/thinknumbers/testdata/ThinkNumbersTestData.xlsx";
	public static String CUSTOMTESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/java/com/thinknumbers/testdata/ScriptLevelTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
    static Row row;
    static Cell cell;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
		
	public static void takeScreenshotOfFailedTest(String testResult, String testMethodName) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testMethodName + "_" + testResult + "_" + dateName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  static void SwitchToChildWindow() {
		Set<String> openWindows = driver.getWindowHandles();
		Iterator<String> i1=openWindows.iterator();		
			
	        while(i1.hasNext())			
	        {		
	            String ChildWindow=i1.next();				
	            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
	            {    		                 
	                    driver.switchTo().window(ChildWindow);	                                                                                                           
	            }		
	        }
	}
	
	public static void SwitchToMainWindow() {
		
		driver.switchTo().window(MainWindow);
	}
	
	public static void WaitTill_PageLoads(long lngTimeOutSeconds){
     
        //reportUtils.log("Log: Inside method - "+ strMethodName, strMethodName);
        System.out.println("Log: Inside method - "+ PAGE_LOAD_TIMEOUT);        

        try
        {
            long lngTime = 0;
            JavascriptExecutor oJsEngine = (JavascriptExecutor) driver;
            String sStatus = "";
            Thread.sleep(1000);

            while(!sStatus.equalsIgnoreCase("complete") && lngTime <= lngTimeOutSeconds)
            {
                System.out.println("From try block of waitTill_PageLoads, Inside while......");
                sStatus = oJsEngine.executeScript("return document.readyState").toString();
                Thread.sleep(1000);
                lngTime++;
            }
            System.out.println("From try block of waitTill_PageLoads, Outside while......");
            WebDriverWait oWait;

            oWait = new WebDriverWait(driver, lngTimeOutSeconds);
            oWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));//wait till body tag is visible
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("From catch block of waitTill_PageLoads......");
        }
    }
	
	
	public static String getCellData(String searchField, int rowNum) {
		String sheetName = "TestData";
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(CUSTOMTESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		String value = "0";
		for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
			data[0][k] = sheet.getRow(0).getCell(k).toString();
						
			
			if(((String) data[0][k]).equals(searchField)) {			
				value = sheet.getRow(rowNum).getCell(k).toString();
				}

			}
		return value;
	}
	
	public static void javaScriptClickEvent(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", e);
		}
	
	public static void javaScriptScroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
	}
	
	public static void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px red')", element);
		
		try {
			Thread.sleep(500);
		}
		catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		//js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white')", element);
	}
	
	public static void generateAlert(String message) throws InterruptedException {
		if (driver == null) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("alert('" + message + "')");
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} else {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("alert('" + message + "')");
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}
	

	public static void waitUntilVisible(long lngTimeOutSeconds, WebElement element) {
		 System.out.println("Log: Inside method - "+ PAGE_LOAD_TIMEOUT);        

	        try
	        {
	            long lngTime = 0;
	            JavascriptExecutor oJsEngine = (JavascriptExecutor) driver;
	            String sStatus = "";
	            Thread.sleep(1000);

	            while(!sStatus.equalsIgnoreCase("complete") && lngTime <= lngTimeOutSeconds)
	            {
	                System.out.println("From try block of waitTill_PageLoads, Inside while......");
	                sStatus = oJsEngine.executeScript("return document.readyState").toString();
	                Thread.sleep(1000);
	                lngTime++;
	            }
	            System.out.println("From try block of waitTill_PageLoads, Outside while......");
	            WebDriverWait oWait;

	            oWait = new WebDriverWait(driver, lngTimeOutSeconds);
	            oWait.until(ExpectedConditions.visibilityOf(element)); //wait till body tag is visible
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	            System.out.println("From catch block of waitTill_PageLoads......");
	        }

	}
	
	public static void waitUntilClickable(long lngTimeOutSeconds, WebElement element) {
		 System.out.println("Log: Inside method - "+ PAGE_LOAD_TIMEOUT);        

	        try
	        {
	            long lngTime = 0;
	            JavascriptExecutor oJsEngine = (JavascriptExecutor) driver;
	            String sStatus = "";
	            Thread.sleep(1000);

	            while(!sStatus.equalsIgnoreCase("complete") && lngTime <= lngTimeOutSeconds)
	            {
	                System.out.println("From try block of waitTill_PageLoads, Inside while......");
	                sStatus = oJsEngine.executeScript("return document.readyState").toString();
	                Thread.sleep(1000);
	                lngTime++;
	            }
	            System.out.println("From try block of waitTill_PageLoads, Outside while......");
	            WebDriverWait oWait;

	            oWait = new WebDriverWait(driver, lngTimeOutSeconds);
	            oWait.until(ExpectedConditions.elementToBeClickable(element)); //wait till body tag is visible
	        }catch(Exception e)
	        {
	            e.printStackTrace();
	            System.out.println("From catch block of waitTill_PageLoads......");
	        }
	}

}
