package com.thinknumbers.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class OMLedgerTypePage extends BaseTest {
	
	@FindBy(xpath="//button//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//button//span[text()='Update']")
	WebElement btUpdate;
	
	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(name="CusLedger")
	WebElement inputLedgerType;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public OMLedgerTypePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void enterLedgerType(String ledgerType) {
		inputLedgerType.sendKeys(Keys.CLEAR);
		inputLedgerType.clear();
		inputLedgerType.sendKeys(ledgerType);
	}
	
	public void addLedgerType() {
		btAdd.click();
	}
	
	public void updateLedgerType() {
		btUpdate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void saveLedgerType() {
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void closeLedgerType() {
		btClose.click();
	}
	
	public String searchLedgerType(String ledgerType) {
		return AppReusableComponents.searchTable(ledgerType);
	}

}
