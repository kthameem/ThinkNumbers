package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.TestUtil;

public class OMOperatingMetricsGroupPage extends BaseTest{

	@FindBy(xpath="//button//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(name="name")
	WebElement iName;
	
	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//div[text()=' Ledger type']//following-sibling::div//mat-select[@role='listbox']")
	WebElement dnLedgerType;

	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public OMOperatingMetricsGroupPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectLedgerType(String ledgerType) {
		dnLedgerType.click();
		String ledger[] = ledgerType.split(",");
		for(String i:ledger) {
			driver.findElement(By.xpath("//mat-option[@role='option']//span[contains(text(),'"+i+"')]")).click();
		}
	}
	
	public void addOMGroup(String name, String ledgerType) {
		btAdd.click();
		iName.sendKeys(Keys.CLEAR);
		iName.clear();
		iName.sendKeys(name);
		selectLedgerType(ledgerType);
		TestUtil.javaScriptClickEvent(btSave);
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
}
