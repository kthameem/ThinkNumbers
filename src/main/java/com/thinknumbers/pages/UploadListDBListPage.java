package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class UploadListDBListPage extends BaseTest{
			
	@FindBy(xpath="//span[text()='Search']")
	WebElement btSearch;
	
	@FindBy(xpath="//tbody//tr[1]//td[1]")
	WebElement tableData;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement inputSearch;
	
	@FindBy(xpath="//div//ul[@class='pagination']//li")
	WebElement pages;
	
	@FindBy(xpath="//div[text()='Showing 0 to 0 of 0 entries']")
	WebElement noDaybook;
		
	public UploadListDBListPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void showOption() {
		Select select = new Select(driver.findElement(By.name("DataTables_Table_2_length")));
		select.selectByValue("100");
	}
	
	public void selectEntityName(String entityName) {
		AppReusableComponents.angularDn("entityName", entityName);
	}
	
	public void selectYear(String fromYear) {
		AppReusableComponents.angularDn("year", fromYear);
	}
	
	public void selectMonth(String monthValue) {
		AppReusableComponents.angularDn("monthvalue", monthValue);
	}
	
	public void searchDaybookList(String entityName, String year, String monthValue) {
		selectEntityName(entityName);
		selectYear(year);
		selectMonth(monthValue);
		btSearch.click();
	}
	
	public String tableDataAvailable() {
		return tableData.getText();
	}
	
	public String searchDaybook(String searchString) {
		inputSearch.sendKeys(searchString);
		return AppReusableComponents.searchTable(searchString);
	}
	
	public boolean isDaybookDataAvailable() {
		return noDaybook.isDisplayed();
	}
	
}
