package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class FasMastersLedgerListPage extends BaseTest{
	
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
	
	@FindBy(xpath="//table//div[text()='100%']")
	WebElement progressBar;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//input[@name='ledger_code']")
	WebElement inputLedgerCode;
	
	@FindBy(xpath="//input[@name='ledger_name']")
	WebElement inputLedgerName;
	
	public FasMastersLedgerListPage() {
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
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void editLedger(String ledgerCode) {
		driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+ledgerCode+"')]//following-sibling::td//i")).click();
		btUpdate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void incorrectLedgerTypeCombo(String ledgerCode, String ledgerType, String ledgerSubType) {
		driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+ledgerCode+"')]//following-sibling::td//i")).click();
		selectLedgerType(ledgerType);
		selectLedgerSubType(ledgerSubType);
		btUpdate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void closeLedger() {
		btClose.click();
	}
	
	public void uploadLedger(String filePath) {
		btUpload.click();
		uploadFile.sendKeys(filePath);
		btSave.click();
		TestUtil.waitUntilVisible(5000, btClose);
		btClose.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public String searchLedger(String searchName) {
		inputSearch.sendKeys(searchName);
		return driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'"+searchName+"')]")).getText();
	}
	
}
