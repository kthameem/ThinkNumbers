package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class ReportRunReportCalculatedPage extends BaseTest{

	@FindBy(xpath="//mat-select[@name='repledger']")
	WebElement dnRepLedger;
	
	public ReportRunReportCalculatedPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void viewReportCalculated(String entityName, String year, String month, String[] repLedgerName) {
		
		AppReusableComponents.angularDn("entityName", entityName);
		AppReusableComponents.angularDn("year", year);
		AppReusableComponents.angularDn("monthvalue", month.toUpperCase());
	
		for(String name: repLedgerName) {
			AppReusableComponents.angularDn("repledger", name);
		}
	}
}
