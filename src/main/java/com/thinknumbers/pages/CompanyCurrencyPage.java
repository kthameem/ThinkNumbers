package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class CompanyCurrencyPage extends BaseTest{

	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement inputSearch;
	
	@FindBy(xpath="//input[@type='text' and @name='code']")
	WebElement inputCode;
	
	@FindBy(xpath="//input[@type='text' and @name='name']")
	WebElement inputName;
	
	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//button//span[text()='Update']")
	WebElement btUpdate;
	
	@FindBy(xpath="//div[contains(text(),'Code')]//following-sibling::div")
	WebElement viewCode;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//a[contains(text(),'Cost Center')]")
	WebElement costCenter;
	
	@FindBy(xpath="//a[contains(text(),'Cost Category')]")
	WebElement costCategory;
	
	@FindBy(xpath="//a[contains(text(),'Employee')]")
	WebElement employee;
	
	@FindBy(xpath="//a[contains(text(),'Voucher Type')]")
	WebElement voucherType;
	
	@FindBy(xpath="//a[contains(text(),'Ledger List')]")
	WebElement ledgerList;
	
	public CompanyCurrencyPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}

	
	public void addCurrency(String iCode, String iName) {
		btAdd.click();
		inputCode.clear();
		inputCode.sendKeys(iCode);
		inputName.clear();
		inputName.sendKeys(iName);
		btSave.click();
	}
	
	public String searchCurrency(String searchString) {
		return AppReusableComponents.searchTable(searchString);
	}
	
	public String viewCurrency(String iName) {
		AppReusableComponents.viewTable(iName);
		String name = viewCode.getText();
		btClose.click();
		return name;
	}
	
	public void editCurrency(String oldName, String newCode, String newName) {
		AppReusableComponents.editTable(oldName);
		inputCode.clear();
		inputCode.sendKeys(newCode);
		inputName.clear();
		inputName.sendKeys(newName);
		btUpdate.click();
		
	}
	
	public void closeCurrency() {
		btClose.click();
	}
		
}
