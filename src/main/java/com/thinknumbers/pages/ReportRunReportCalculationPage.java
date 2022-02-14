package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class ReportRunReportCalculationPage extends BaseTest{

	@FindBy(xpath="//span[text()='Calculate']")
	WebElement btCalculate;
	
	@FindBy(xpath="//span[text()='Reset']")
	WebElement btReset;
	
	@FindBy(xpath="//select")
	WebElement dnEntries;
	
	@FindBy(xpath="//option[@value='100']")
	WebElement opt100;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//label[contains(text(),'All')]//span")
	WebElement selectAll;
	
	public ReportRunReportCalculationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void selectEntity(String entityName) {
		AppReusableComponents.angularDn("entityName", entityName);
	}
	
	public void selectYear(String year) {
		AppReusableComponents.angularDn("year", year);
	}
	
	public void selectMonth(String month) {
		AppReusableComponents.angularDn("monthvalue", month.toUpperCase());
	}
	
	public void calculateReport(String entityName, String year, String month) throws InterruptedException {
		selectEntity(entityName);
		selectYear(year);
		selectMonth(month);
		
		dnEntries.click();
		Thread.sleep(1000);
		opt100.click();
		Thread.sleep(2000);
		
		selectAll.click();
		Thread.sleep(2000);
		
		btCalculate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
}
