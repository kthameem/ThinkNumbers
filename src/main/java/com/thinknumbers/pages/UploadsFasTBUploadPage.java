package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsFasTBUploadPage extends BaseTest{
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//button//span[text()='Validate']")
	WebElement btValidate;
	
	@FindBy(xpath="//button//span[text()='Upload']")
	WebElement btUpload;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//a[text()='FasTB List']")
	WebElement fasTBList;
	
	@FindBy(xpath="//a[text()='FasTB Validate']")
	WebElement fasTBValidate;
	
	public UploadsFasTBUploadPage() {
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
		AppReusableComponents.angularDn("fromYear", fromYear);
	}
	
	public void selectMonth(String monthValue) {
		AppReusableComponents.angularDn("monthvalue", monthValue);
	}
	
	public void validateFasTB(String entityName, String fromYear, String monthValue, String filePath) {
		selectEntityName(entityName);
		selectYear(fromYear);
		selectMonth(monthValue);
		uploadFile.sendKeys(filePath);
		btValidate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
		TestUtil.waitUntilVisible(1000, btUpload);
	}
	
	public void uploadfasTB() {
		btUpload.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
		

}
