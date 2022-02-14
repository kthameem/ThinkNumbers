package com.thinknumbers.util;

import org.openqa.selenium.By;

import com.thinknumbers.base.BaseTest;

public class AppReusableComponents extends BaseTest{
	
	public String entityName;
	public String year;
	public String month;
	
	/**
     * <p>
     * Allows to get the EntityName from test data excel sheet
     * 
     */
	public static String getEntityName() {
		return TestUtil.getCellData("EntityName", 1);
	}

	/**
     * <p>
     * Allows to get the Year from test data excel sheet
     * 
     */
	public static String getYear() {
		return TestUtil.getCellData("Year", 1);
	}

	/**
     * <p>
     * Allows to get the Month from test data excel sheet
     * 
     */
	public static String getMonth() {
		return TestUtil.getCellData("Month", 1);
	}
	
    /**
     * <p>
     * Allows to select option from drop down
     * for the Angular based pages
     * 
     * @param dnName 
     *      Enter the name of element
     *      
     * @param dnValue 
     *      Visible text displayed in drown down option
     */
	public static void angularDn(String dnName, String dnValue) {
		TestUtil.WaitTill_PageLoads(10);
		driver.findElement(By.name(dnName)).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +dnValue +"')]")).click();
	}
	
    /**
     * <p>
     * Returns text of WebElement from Table when search string is passed
     * 
     * @param searchName 
     *      Enter the name of search string
     *      
     */
	public static String searchTable(String searchName) {
		return driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+searchName+"')]")).getText();
	}
	
    /**
     * <p>
     * Clicks the record matching search String from Table
     * 
     * @param searchName 
     *      Enter the name of search string
     *      
     */
	public static void viewTable(String searchName) {
		driver.findElement(By.xpath("//tbody//tr//td[text()='"+searchName+"']//following-sibling::td//i[@title='View']")).click();
	}
	
	/**
     * <p>
     * Clicks on Edit record matching search String from Table
     * 
     * @param searchName 
     *      Enter the name of search string
     *      
     */
	public static void editTable(String searchName) {
		driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+searchName+"')]//following-sibling::td//i[@title='Edit']")).click();
	}

}
