package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.util.AppReusableComponents;

public class SubMasterLedgerPage extends TestBase {
	
	@FindBy(xpath="//button//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//button//span[text()='Upload']")
	WebElement btUpload;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement inputSearch;
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Update']")
	WebElement btUpdate;

	@FindBy(xpath="//button//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//input[@name='ledger_code']")
	WebElement inputLedgerCode;
	
	@FindBy(xpath="//input[@name='ledger_name']")
	WebElement inputLedgerName;
	
	public SubMasterLedgerPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectLedgerType(String ledgerType) {
		AppReusableComponents.angularDn("ledger_type", ledgerType);
	}
	
	public void selectLedgerSubType(String ledgerSubType) {
		AppReusableComponents.angularDn("ledger_subtype", ledgerSubType);
	}
	
	public void selectLedgerGroup(String ledgerGroup) {
		AppReusableComponents.angularDn("ledger_group", ledgerGroup);
	}
	
	public void selectStatus(String status) {
		AppReusableComponents.angularDn("ledger_propstatus", status);
	}
	
	public void addLedger(String ledgerCode, String ledgerName, String ledgerType, String ledgerSubType, String ledgerGroup, String status) {
		btAdd.click();
		inputLedgerCode.clear();
		inputLedgerCode.sendKeys(ledgerCode);
		inputLedgerName.clear();
		inputLedgerName.sendKeys(ledgerName);
		selectLedgerType(ledgerType);
		selectLedgerSubType(ledgerSubType);
		selectLedgerGroup(ledgerGroup);
		selectStatus(status);
		btSave.click();
	}
	
	public void editLedger(String ledgerCode) {
		driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+ledgerCode+"')]//following-sibling::td//i")).click();
		inputLedgerName.clear();
		inputLedgerCode.clear();
		inputLedgerCode.sendKeys("test1");
		inputLedgerName.sendKeys("test1");
		btUpdate.click();
	}
	
	public void incorrectLedgerTypeCombo(String ledgerCode, String ledgerType, String ledgerSubType) {
		driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+ledgerCode+"')]//following-sibling::td//i")).click();
		selectLedgerType(ledgerType);
		selectLedgerSubType(ledgerSubType);
		btUpdate.click();
	}
	
	public void closeLedger() {
		btClose.click();
	}
	
	public void uploadLedger(String filePath) {
		btUpload.click();
		uploadFile.sendKeys(filePath);
		btSave.click();
		btClose.click();
	}
	
	public String searchLedger(String searchName) {
		inputSearch.sendKeys(searchName);
		return driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+searchName+"')]//following-sibling::td//i")).getText();
	}
	
}
