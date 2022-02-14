package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class ReportRunPublishReportPage extends BaseTest{

	@FindBy(xpath="//span[text()='PUBLISH']")
	WebElement btPublishOut;
	
	@FindBy(xpath="//span[text()='Back']")
	WebElement btBack;
	
	@FindBy(xpath="//button[text()='SUBMIT']")
	WebElement btSubmit;
	
	@FindBy(xpath="//button[text()='PUBLISH']")
	WebElement btPublishIn;
	
	@FindBy(xpath="//button[contains(text(),'UPDATE')]")
	WebElement btUpdate;

	//@FindBy(xpath="//input[@name='fromDate']")
	@FindBy(xpath="//tbody//tr[2]//td[4]//input")
	WebElement iFromDate;
	
	//@FindBy(xpath="//input[@name='toDate']")
	@FindBy(xpath="//tbody//tr[2]//td[6]//input")
	WebElement iToDate;
	
	@FindBy(xpath="//div[@class='mat-calendar-controls']/button[@type='button'][1]")
	WebElement btGoToYear;
	
	@FindBy(xpath="//input[@name='reportName']")
	WebElement iReportName;
	
	@FindBy(name="tempName")
	WebElement dnTemplateName;
	
	@FindBy(xpath="//table[2]//tbody//td[4]//mat-select")
	WebElement dnUserGroups;
	
	@FindBy(xpath="//table[2]//tbody//td[6]//mat-select")
	WebElement dnUsers;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public ReportRunPublishReportPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void selectTemplate(String inputTemplate) {
		String templateName[] = inputTemplate.split(",");
		dnTemplateName.click();
		for(String name: templateName) {
			driver.findElement(By.xpath("//mat-option//span[contains(text(), '"+name+"')]")).click();
		}
	}
	
	public void selectColumn(String column) {
		AppReusableComponents.angularDn("columnType", column);
	}
	
	public void selectColTotal(String colTotal) {
		AppReusableComponents.angularDn("totalOption", colTotal);
	}
	
	public void selectDecOption(String decimalOption) {
		AppReusableComponents.angularDn("decimalType", decimalOption);
	}
	
	public void selectFigConversion(String figConversion) {
		AppReusableComponents.angularDn("figConverstion", figConversion);
	}
	
	public void selectUserGroups(String userGroup) {
		String userGroupList[] = userGroup.split(",");
		TestUtil.javaScriptClickEvent(dnUserGroups);
		for(String name: userGroupList) {
			driver.findElement(By.xpath("//mat-option//span[contains(text(), '"+name+"')]")).click();
		}
	}
	
	public void selectUsers(String users) {
		String usersList[] = users.split(",");
		TestUtil.javaScriptClickEvent(dnUsers);
		for(String name: usersList) {
			driver.findElement(By.xpath("//mat-option//span[contains(text(), '"+name+"')]")).click();
		}
	}
	
	public void selectDate(String column, String date, WebElement e) {
		
		String seperatedDate[] = date.split("/");
		
		e.click();
		
		if(column.equalsIgnoreCase("Daily")) {
			btGoToYear.click();
			driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[2]+"']")).click();
			driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[1].toUpperCase()+"']")).click();
			driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[0]+"']")).click();
		} else if (column.equalsIgnoreCase("Monthly")) {
			btGoToYear.click();
			btGoToYear.click();
			driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[2]+"']")).click();
			driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[1].toUpperCase()+"']")).click();
			//driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[0]+"']")).click();
		} else if (column.equalsIgnoreCase("Quarterly")) {
			btGoToYear.click();
			btGoToYear.click();
			driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[2]+"']")).click();
			driver.findElement(By.xpath("//tbody//tr//td//div[text()='"+seperatedDate[1].toUpperCase()+"']")).click();
		}
		
	
	}
	
	public void publishReport(String inputTemplate, String column, String fromDate, String toDate, String colTotal, String decimalOption, 
			String figConversion, String reportName, String userGroups, String users) {
		
		btPublishOut.click();
		
		selectColumn(column);
		selectColTotal(colTotal);
		selectDecOption(decimalOption);
		selectFigConversion(figConversion);	
		selectDate(column, fromDate, iFromDate);
		selectDate(column, toDate, iToDate);		
		iReportName.sendKeys(reportName);
		selectTemplate(inputTemplate);
		selectUserGroups(userGroups);
		selectUsers(users);
		
		TestUtil.javaScriptClickEvent(btSubmit);
		
		TestUtil.waitUntilClickable(20000, btPublishIn);
		//Thread.sleep(15000);
		
		TestUtil.javaScriptClickEvent(btPublishIn);
		
		TestUtil.waitUntilVisible(2000, alertMessage);
	}
	
	public void updatePublishReport() {
		AppReusableComponents.editTable("Active");
		TestUtil.waitUntilClickable(2000, btUpdate);
		btUpdate.click();
		TestUtil.waitUntilVisible(2000, alertMessage);
	}
	
	
}
