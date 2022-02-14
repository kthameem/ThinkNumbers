package com.thinknumbers.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class OMOperatingMetricsPage extends BaseTest{

	@FindBy(xpath="//button//span[text()='Upload']")
	WebElement btUpload;
	
	@FindBy(xpath="//button//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//button//span[text()='Update']")
	WebElement btUpdate;
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadFile;
	
	@FindBy(name="OprMetName")
	WebElement inputName;
	
	@FindBy(name="OprDisName")
	WebElement inputDisName;
	
	@FindBy(name="dashboardDisName")
	WebElement inputDashDisName;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public OMOperatingMetricsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectCategory(String category) {
		AppReusableComponents.angularDn("categoryType", category);
	}
	
	public void selectType(String ledgerType) {
		AppReusableComponents.angularDn("ledgerType", ledgerType);
	}
	
	public void selectFigConversion(String figConversion) {
		AppReusableComponents.angularDn("figConversion", figConversion);
	}
	
	public void selectExchRate(String exchangeRate) {
		AppReusableComponents.angularDn("exchangeRate", exchangeRate);
	}
	
	public void selectYTD(String yTD) {
		AppReusableComponents.angularDn("idCalculation", yTD);
	}
	
	public void addOM() {
		btAdd.click();
	}
	
	public void enterOM(String name, String disName, String dashDisName, String category, String ledgerType, String figConversion, String exchangeRate, String yTD) {
		inputName.sendKeys(Keys.CLEAR);
		inputName.clear();
		inputName.sendKeys(name);
		
		inputDisName.sendKeys(Keys.CLEAR);
		inputDisName.clear();
		inputDisName.sendKeys(disName);
		
		inputDashDisName.sendKeys(Keys.CLEAR);
		inputDashDisName.clear();
		inputDashDisName.sendKeys(dashDisName);
		
		selectCategory(category);
		selectType(ledgerType);
		selectFigConversion(figConversion);
		selectExchRate(exchangeRate);
		selectYTD(yTD);
	}
	
	public void saveOM() {
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void closeOM() {
		btClose.click();
	}
	
	public void updateOM() {
		btUpdate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void uploadOM(String filePath) {
		btUpload.click();
		uploadFile.sendKeys(filePath);
		btSave.click();
		TestUtil.waitUntilVisible(5000, alertMessage);
	}
	
	public void editOM() {
		AppReusableComponents.editTable("OM1");
		TestUtil.waitUntilVisible(1000, btUpdate);
		btUpdate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	
}
