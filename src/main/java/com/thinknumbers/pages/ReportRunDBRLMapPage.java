package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class ReportRunDBRLMapPage extends BaseTest{

	@FindBy(xpath="//button//span[text()='MAP']")
	WebElement btMap;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public ReportRunDBRLMapPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
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
	
	public void mapRL(String entityName, String fromYear, String monthValue) {
		selectEntityName(entityName);
		selectYear(fromYear);
		selectMonth(monthValue);
		btMap.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
}
