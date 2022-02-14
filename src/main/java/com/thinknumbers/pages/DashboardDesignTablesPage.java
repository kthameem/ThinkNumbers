package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignTablesPage extends BaseTest{

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
	
	@FindBy(name="totalColumnLabel")
	WebElement iYTDColLabel;
	
	public DashboardDesignTablesPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void selectEntity(String entityName) {
		String entity[] = entityName.split(",");
		TestUtil.javaScriptClickEvent(dnEntityName);
		//dnEntityName.click();
		for(String name: entity) {
			driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +name+"')]")).click();
		}
	}
	
	public void selectReportSection(String reportSection) {
		AppReusableComponents.angularDn("reportSection", reportSection);
	}
	
	public void selectBudget(String budget) {
		AppReusableComponents.angularDn("budget", budget);
	}
	
	public void selectVariance(String variance) {
		AppReusableComponents.angularDn("variance", variance);
	}
	
	public void selectRange(String range) {
		AppReusableComponents.angularDn("range", range);
	}
	
	public void selectReportType(String reportType) {
		AppReusableComponents.angularDn("reportType", reportType);
	}
	
	public void selectYTD(String ytd) {
		AppReusableComponents.angularDn("YTD", ytd);
	}
	
	public void selectYTDFrom(String ytdFrom) {
		AppReusableComponents.angularDn("YTDfrom", ytdFrom);
	}
	
	public void addTables(String title, String entityName, String reportSection, String budget, 
			String variance, String range, String reportType, String ytd, String ytdFrom, String iYTDLabel) {
		
		TestUtil.WaitTill_PageLoads(10);
		
		iTitle.sendKeys(title);
		selectReportSection(reportSection);
		selectBudget(budget);
		selectVariance(variance);
		
		if(range.equalsIgnoreCase("Previous month")) {
			selectRange(range);
		} else if(range.equalsIgnoreCase("Current month")) {
			selectRange(range);
		} else {
			selectRange(range);
			selectReportType(reportType);
		}
		
		if(ytd.equalsIgnoreCase("Yes")){
			selectYTD(ytd);
			selectYTDFrom(ytdFrom);
			iYTDColLabel.sendKeys(iYTDLabel);
		} else {
			selectYTD(ytd);
		}
		
		selectEntity(entityName);
		
		TestUtil.javaScriptClickEvent(btSave);		
	}
	
	public void editTables() {
		AppReusableComponents.editTable("Active");
		btUpdate.click();
	}
	
	
}
