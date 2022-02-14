package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignKeyNumbersPage extends BaseTest{

	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button[text()='Back']")
	WebElement btBack;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement btUpdate;
	
	//@FindBy(xpath="//div[@role='alertdialog']")
	@FindBy(xpath="//div[@id='toast-container']//div//div[1]")
	WebElement alertMessage;
	
	@FindBy(name="displayName")
	WebElement iDisplayName;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement iSearch;
	
	@FindBy(xpath="//input[@name='compprevperiod' and @value='1']")
	WebElement compVsPrevPeriodYes;
	
	@FindBy(xpath="//input[@name='compprevperiod' and @value='2']")
	WebElement compVsPrevPeriodNo;
	
	@FindBy(xpath="//input[@name='compbudget' and @value='1']")
	WebElement compVsBudgetYes;
	
	@FindBy(xpath="//input[@name='compbudget' and @value='2']")
	WebElement compVsBudgetNo;
	
	@FindBy(name="idEntity")
	WebElement dnEntityName;
	
	public DashboardDesignKeyNumbersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectType(String type) {
		AppReusableComponents.angularDn("idType", type);
	}
	
	public void selectEntity(String entityName) {
		String entity[] = entityName.split(",");
		dnEntityName.click();
		for(String name: entity) {
			driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +name+"')]")).click();
		}
	}
	
	public void selectKeyNumbers(String keyNumbers) {
		AppReusableComponents.angularDn("reportSection", keyNumbers);
	}
	
	public void selectRange(String range) {
		AppReusableComponents.angularDn("range", range);
	}
	
	public void selectCompRange(String compRange) {
		AppReusableComponents.angularDn("comparisonrange", compRange);
	}
	
	public void addKeyNumbers(String type, String entityName, String keyNumbers, String dispName, 
			String range, String compVsPrev, String compRange, String compVsBudget) {
		
		btAdd.click();
		selectType(type);
			
		selectKeyNumbers(keyNumbers);
		iDisplayName.sendKeys(dispName);
		selectRange(range);
		
		if(compVsPrev.equalsIgnoreCase("Yes")) {
			TestUtil.javaScriptClickEvent(compVsPrevPeriodYes);
			//compVsPrevPeriodYes.click();
			selectCompRange(compRange);
		} else {
			TestUtil.javaScriptClickEvent(compVsPrevPeriodNo);
			//compVsPrevPeriodNo.click();
		}
		
		if(compVsBudget.equalsIgnoreCase("Yes")) {
			TestUtil.javaScriptClickEvent(compVsBudgetYes);
			//compVsBudgetYes.click();
		} else {
			TestUtil.javaScriptClickEvent(compVsBudgetYes);
			//compVsBudgetNo.click();
		}
		
		selectEntity(entityName);	
		
		TestUtil.javaScriptClickEvent(btSave);
	}
	
	public void editKeyNumbers() {
		AppReusableComponents.editTable("Active");
		btUpdate.click();
	}
	
}
