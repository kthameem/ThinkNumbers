package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignChartsPage extends BaseTest{

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
	
	@FindBy(xpath="//input[@type='search']")
	WebElement iSearch;
	
	@FindBy(xpath="//input[@name='title']")
	WebElement iTitle;
	
	@FindBy(name="idEntity")
	WebElement dnEntityName;
	
	@FindBy(name="idLedgers")
	WebElement dnLedger;
	
	public DashboardDesignChartsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void selectChartType(String chartType) {
		AppReusableComponents.angularDn("idType", chartType);
	}
	
	public void selectEntity(String entityName) {
		String entity[] = entityName.split(",");
		TestUtil.javaScriptClickEvent(dnEntityName);
		//dnEntityName.click();
		for(String name: entity) {
			driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +name+"')]")).click();
		}
	}
	
	public void selectXAxis(String xAxis) {
		AppReusableComponents.angularDn("idXaxis", xAxis);
	}
	
	public void selectPeriod(String period) {
		AppReusableComponents.angularDn("idPeriod", period);
	}
	
	public void selectReportTemplate(String reportTemplate) {
		AppReusableComponents.angularDn("idTemplate", reportTemplate);
	}
	
	public void selectReportLedger(String reportLedger) {
		String ledger[]=reportLedger.split(",");
		dnLedger.click();
		for(String name: ledger) {
			driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +name+"')]")).click();
		}
	}
	
	public void selectBudget(String budget) {
		AppReusableComponents.angularDn("budget", budget);
	}
	
	public void addCharts(String chartType, String chartTitle, String entityName, String xAxis, 
			String period, String reportTemplate, String reportLedger, String budget) {
		
		btAdd.click();
		selectChartType(chartType);
		iTitle.sendKeys(chartTitle);
		
		selectXAxis(xAxis);
		selectPeriod(period);
		selectReportTemplate(reportTemplate);
		
		selectBudget(budget);
		
		selectReportLedger(reportLedger);
		selectEntity(entityName);
		
		TestUtil.javaScriptClickEvent(btSave);
	}
	
	public void editCharts() {
		AppReusableComponents.editTable("Active");
		btUpdate.click();
	}
	
	
}
