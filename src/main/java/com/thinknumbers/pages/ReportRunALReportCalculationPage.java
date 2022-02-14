package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class ReportRunALReportCalculationPage extends BaseTest{

	@FindBy(xpath="//tr[1]//span[contains(text(),'Calculate')]")
	WebElement btCalculateMonthly;
	
	@FindBy(xpath="//tr[2]//span[contains(text(),'Calculate')]")
	WebElement btCalculateQuaterly;
	
	@FindBy(xpath="//tr[3]//span[contains(text(),'Calculate')]")
	WebElement btCalculateDaily;
	
	//@FindBy(xpath="//div[@role='alertdialog']")
	@FindBy(xpath="//div[@id='toast-container']//div//div[1]")
	WebElement alertMessage;
	
	public ReportRunALReportCalculationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void runMonthly(String entityName, String year, String month) throws InterruptedException {
		AppReusableComponents.angularDn("entityName", entityName);
		AppReusableComponents.angularDn("year", year);
		AppReusableComponents.angularDn("monthvalue", month);
		
		Thread.sleep(2000);
		btCalculateMonthly.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void runQuaterly(String entityName, String year, String month) throws InterruptedException {
		AppReusableComponents.angularDn("entityName", entityName);
		AppReusableComponents.angularDn("year", year);
		AppReusableComponents.angularDn("monthvalue", month);
		
		Thread.sleep(2000);
		btCalculateQuaterly.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void runDaily(String entityName, String year, String month) throws InterruptedException {
		AppReusableComponents.angularDn("entityName", entityName);
		AppReusableComponents.angularDn("year", year);
		AppReusableComponents.angularDn("monthvalue", month);
		
		Thread.sleep(2000);
		btCalculateDaily.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
}
