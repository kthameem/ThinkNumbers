package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsBudgetDataPage extends BaseTest{

	@FindBy(xpath="//span[text()='Upload']")
	WebElement btUpload;
	
	@FindBy(xpath="//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public UploadsBudgetDataPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectEntity(String entityName) {
		AppReusableComponents.angularDn("endityGroup", entityName);
	}
	
	public void uploadBudgetData(String entityName, String filePath) {
		btUpload.click();
		selectEntity(entityName);
		uploadFile.sendKeys(filePath);
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
}
