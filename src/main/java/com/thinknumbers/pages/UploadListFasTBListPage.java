package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class UploadListFasTBListPage extends BaseTest{

	@FindBy(xpath="//span[text()='Search']")
	WebElement btSearch;
	
	@FindBy(xpath="//table//tbody//tr")
	WebElement hasData;
	
	@FindBy(xpath="//div[text()='Showing 0 to 0 of 0 entries']")
	WebElement noData;
	
	public UploadListFasTBListPage() {
		PageFactory.initElements(driver, this);
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
	
	public void searchFasTB(String entityName, String fromYear, String monthValue) {
		selectEntityName(entityName);
		selectYear(fromYear);
		selectMonth(monthValue);
		btSearch.click();
	}
	
	public boolean hasFasTBData() {
		return hasData.isDisplayed();
	}
	
	public boolean noFasTBData() {
		return noData.isDisplayed();
	}
}
