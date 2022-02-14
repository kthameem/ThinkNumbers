package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class ReportDesignReportTemplatePage extends BaseTest{
	
	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//span[text()='Update']")
	WebElement btUpdate;
	
	@FindBy(xpath="//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//span[text()='preview']")
	WebElement btPreview;
	
	@FindBy(xpath="//span[text()='Back']")
	WebElement btBack;
	
	@FindBy(name="templateName")
	WebElement iName;
	
	@FindBy(name="Search")
	WebElement iSearch;
	
	@FindBy(xpath="//mat-form-field//mat-select//following-sibling::span//label//mat-label[text()='Select']")
	WebElement dnSelect;

	@FindBy(xpath="//div[@role='alertdialog']")
	//@FindBy(xpath="//div[@id='toast-container']//div//div[1]")
	WebElement alertMessage;
	
	@FindBy(xpath="//mat-select[@role='listbox' and @name='endityGroup']")
	WebElement entityGroup;
	
	@FindBy(xpath="//mat-checkbox//input")
	WebElement ledgerCheckbox;
	
	public ReportDesignReportTemplatePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void goBack() {
		TestUtil.javaScriptClickEvent(btBack);
	}
	
	public void updateTemplate() {
		AppReusableComponents.editTable("Active");
		btUpdate.click();
	}
	
	public void selectEntity(String entityName) {
		entityGroup.click();
		String entity[] = entityName.split(",");
		for(String i:entity) {
			driver.findElement(By.xpath("//mat-option[@role='option']//span[contains(text(),'"+i+"')]")).click();
		}
	}
	
	public void selectBudget(String budget) {
		AppReusableComponents.angularDn("budgetOption", budget);
	}
	
	public void selectVariance(String variance) {
		AppReusableComponents.angularDn("varianceOption", variance);
	}
	
	public void selectReportGroup(String reportGroup) {
		AppReusableComponents.angularDn("alGroupOption", reportGroup);
	}
	
	public void addReportTemplate(String templateName, String entityName, String budget, 
			String variance, String reportGroup, String ledgerList) throws InterruptedException {
		
		btAdd.click();
		
		iName.sendKeys(Keys.CLEAR);
		iName.clear();
		iName.sendKeys(templateName);
		
		selectBudget(budget);
		
		if(budget.equalsIgnoreCase("Yes")) {
			selectVariance(variance);
		}
		selectReportGroup(reportGroup);
		
		String ledger[]=ledgerList.split(",");
		for(String ledgerName:ledger) {
			Thread.sleep(1000);
			iSearch.sendKeys(Keys.CLEAR);
			iSearch.clear();
			iSearch.sendKeys(ledgerName);
			TestUtil.javaScriptClickEvent(ledgerCheckbox);
		}
		
		selectEntity(entityName);
		
		TestUtil.javaScriptClickEvent(btSave);
	}
}
