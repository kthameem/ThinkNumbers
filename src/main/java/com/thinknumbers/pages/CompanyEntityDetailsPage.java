package com.thinknumbers.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class CompanyEntityDetailsPage extends BaseTest{
	
	@FindBy(xpath="//h4[text()='Group Company Details']")
	WebElement pageTitle;
	
	@FindBy(name="cmpy_name")
	WebElement inputCompanyName;
	
	@FindBy(name="cmpy_code")
	WebElement inputCompanyCode;
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadLogo;
	
	@FindBy(xpath="//span[text()='Update']")
	WebElement btUpdate;
	
	@FindBy(xpath="//span[text()='Reset']")
	WebElement btReset;
	
	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement inputSearch;
	
	@FindBy(xpath="//a[text()='Credential']")
	WebElement credentialIcon;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(name="entity_name")
	WebElement entityName;
	
	@FindBy(xpath="//textarea[@placeholder='Remarks']")
	WebElement remarksTxt;
	
	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Close']")
	WebElement btClose;

	@FindBy(xpath="//div[@class='btn-bottom']//button//span[text()='Update']")
	WebElement btUpdateEntity;
	
	@FindBy(xpath="//div[contains(text(),'Entity Name')]//following-sibling::div")
	WebElement viewEntityName;

	public CompanyEntityDetailsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addCompanyDetails(String companyName, String companyCode, String logoPath) {
		inputCompanyName.clear();
		inputCompanyCode.clear();
		inputCompanyName.sendKeys(companyName);
		inputCompanyCode.sendKeys(companyCode);
		uploadLogo.sendKeys(logoPath);;
		btUpdate.click();	
	}
	
	public String getCompanyName() {
		return inputCompanyName.getText();
	}
	
	public String getCompanyCode() {
		return inputCompanyCode.getText();
	}
	
	public void resetCompany() {
		inputCompanyName.clear();
		inputCompanyCode.clear();
		inputCompanyName.sendKeys("Test");
		inputCompanyCode.sendKeys("Test");
		btReset.click();
	}
	
	public String searchEntity(String entityName) {
		return AppReusableComponents.searchTable(entityName);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectAccCurrency(String accCurrency) {
		AppReusableComponents.angularDn("account_currency", accCurrency);
	}
	
	public void selectReportCurrency(String accCurrency) {
		AppReusableComponents.angularDn("report_currency", accCurrency);
	}
	
	public void addEntity(String eName, String aCurrency, String rCurrency, String rTxt) {
		TestUtil.javaScriptClickEvent(btAdd);
		entityName.sendKeys(eName);
		selectAccCurrency(aCurrency);
		selectReportCurrency(rCurrency);
		remarksTxt.sendKeys(rTxt);
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public String viewEntity(String eName) {
		AppReusableComponents.viewTable(eName);
		String name = viewEntityName.getText();
		btClose.click();
		return name;
	}
	
	public void editEntity(String eName) {
		AppReusableComponents.editTable(eName);
		remarksTxt.sendKeys(Keys.CLEAR);
		remarksTxt.clear();
		remarksTxt.sendKeys(eName+"test");
		btUpdateEntity.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void closeEntity() {
		btClose.click();
	}

}
