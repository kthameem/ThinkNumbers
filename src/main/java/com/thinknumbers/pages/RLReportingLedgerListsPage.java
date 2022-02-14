package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class RLReportingLedgerListsPage extends BaseTest{

	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Update']")
	WebElement btUpdate;

	@FindBy(xpath="//button//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//input[@name='rl_name']")
	WebElement inputRLName;
	
	@FindBy(xpath="//input[@name='rl_display']")
	WebElement inputDisplayName;
	
	@FindBy(xpath="//input[@name='dashboard_display_name']")
	WebElement inputDashboardDisplayName;
	
	@FindBy(xpath="//select")
	WebElement dnEntries;
	
	@FindBy(xpath="//option[@value='100']")
	WebElement opt100;
	
	public RLReportingLedgerListsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectType(String type) {
		AppReusableComponents.angularDn("rlmaintype_det", type);
	}
	
	public void selectSubType(String subType) {
		AppReusableComponents.angularDn("rltype_det", subType);
	}
	
	public void selectCalc(String calc) {
		AppReusableComponents.angularDn("isCalculation", calc);
	}
	
	public void selectStatus(String status) {
		AppReusableComponents.angularDn("status", status);
	}
	
	public void addRL(String rlName, String dispName, String dashDispName, String type, String subType, String calc, String status) {
		btAdd.click();
		inputRLName.sendKeys(rlName);
		inputDisplayName.sendKeys(dispName);
		inputDashboardDisplayName.sendKeys(dashDispName);
		selectType(type);
		selectSubType(subType);
		selectCalc(calc);
		selectStatus(status);
		btSave.click();
	}
	
	public String[] getRepLedName() throws InterruptedException {
		
		Thread.sleep(500);
		dnEntries.click();
		opt100.click();
		Thread.sleep(500);
		int tableSize = driver.findElements(By.xpath("//tbody//tr")).size();
		String[] repLedgerName = new String[tableSize];
		for(int i=1;i<=tableSize;i++) {
			repLedgerName[i-1] = driver.findElement(By.xpath("//tbody//tr["+i+"]//td[2]")).getText();
		}	
		return repLedgerName;
	}


}
