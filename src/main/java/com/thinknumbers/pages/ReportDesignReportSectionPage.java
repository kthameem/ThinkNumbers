package com.thinknumbers.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class ReportDesignReportSectionPage extends BaseTest{

	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//span[text()='SAVE']")
	WebElement btSave;
	
	@FindBy(xpath="//span[text()='Update']")
	WebElement btUpdate;
	
	@FindBy(xpath="//span[text()='RESET']")
	WebElement btReset;
	
	@FindBy(xpath="//span[text()='BACK']")
	WebElement btBack;
	
	@FindBy(name="Search")
	WebElement iSearch;
	
	@FindBy(name="arithmeticLedgerName")
	WebElement iName;
	
	@FindBy(name="displayName")
	WebElement iDispName;
	
	@FindBy(name="groupName")
	WebElement iGroupName;
	
	@FindBy(name="dashboarddisplayName")
	WebElement iDashDispName;
	
	@FindBy(xpath="//input[@name='isCalculate' and @value='1']")
	WebElement isCalcAvailableYes;
	
	@FindBy(xpath="//input[@name='isCalculate' and @value='2']")
	WebElement isCalcAvailableNo;
	
	@FindBy(xpath="//input[@name='totalAvg' and @value='1']")
	WebElement isAggregateSum;
	
	@FindBy(xpath="//input[@name='totalAvg' and @value='2']")
	WebElement isAggregateAvg;

	@FindBy(xpath="//input[@name='isRLOMonly' and @value='1']")
	WebElement isRlOmOnlyYes;
	
	@FindBy(xpath="//input[@name='isRLOMonly' and @value='2']")
	WebElement isRlOmOnlyNo;
	
	@FindBy(name="uomName")
	WebElement iUOMName;
	
	@FindBy(xpath="//input[@name='isOperation' and @value='1']")
	WebElement formulaSum;
	
	@FindBy(xpath="//input[@name='isOperation' and @value='2']")
	WebElement formulaAvg;
	
	@FindBy(xpath="//input[@name='isOperation' and @value='3']")
	WebElement formulaHundred;
	
	//@FindBy(xpath="//div[@role='alertdialog']")
	@FindBy(xpath="//div[@id='toast-container']//div//div[1]")
	WebElement alertMessage;
	
	@FindBy(xpath="//div[@class='ng-star-inserted']/div[1]//mat-checkbox//input")
	WebElement ledger;
	
	public ReportDesignReportSectionPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void updateReportSection() {
		AppReusableComponents.editTable("AL1");
		TestUtil.javaScriptClickEvent(btUpdate);
		//btUpdate.click();
	}
	
	public void selectType(String type) {
		AppReusableComponents.angularDn("LedgerType", type);
	}
	
	public void selectFigConversion(String figConv) {
		AppReusableComponents.angularDn("FigureConversion", figConv);
	}
	
	public void selectExchRate(String exchRate) {
		AppReusableComponents.angularDn("exchangeRate", exchRate);
	}
	
	public void selectUOMType(String uomType) {
		AppReusableComponents.angularDn("uomType", uomType);
		if(uomType.equalsIgnoreCase("Custom")) {
			iUOMName.sendKeys("UOM");
		}
	}
	
	public void selectStatus(String status) {
		AppReusableComponents.angularDn("status", status);
	}
	
	public void selectLedger(String ledgers) {
		iSearch.sendKeys(Keys.CLEAR);
		iSearch.clear();
		iSearch.sendKeys(ledgers);
		ledger.click();
	}
	
	public void alReport(String name, String dispName, String groupName, String type, String isCalc, String figConv, 
			String exchRate, String aggregate, String dashDispName, String isRlOm, String formula, String[] ledgerList, String uomType) {
		
		btAdd.click();
		iName.sendKeys(name);
		iDispName.sendKeys(dispName);
		iGroupName.sendKeys(groupName);
		selectType(type);
		
		if(isCalc.equalsIgnoreCase("Yes")) {
			TestUtil.javaScriptClickEvent(isCalcAvailableYes);
			if(formula.equalsIgnoreCase("Sum")) {
				TestUtil.javaScriptClickEvent(formulaSum);
			} else if(formula.equalsIgnoreCase("Avg")) {
				TestUtil.javaScriptClickEvent(formulaAvg);
			} else {
				TestUtil.javaScriptClickEvent(formulaHundred);
			}
		} else {
			TestUtil.javaScriptClickEvent(isCalcAvailableNo);
		}
		
		selectFigConversion(figConv);
		selectExchRate(exchRate);
		selectUOMType(uomType);
		
		if(aggregate.equalsIgnoreCase("Sum")) {
			TestUtil.javaScriptClickEvent(isAggregateSum);
		} else {
			TestUtil.javaScriptClickEvent(isAggregateAvg);
		}
		
		iDashDispName.sendKeys(dashDispName);
		
		if(isRlOm.equalsIgnoreCase("Yes")) {
			TestUtil.javaScriptClickEvent(isRlOmOnlyYes);
		} else {
			TestUtil.javaScriptClickEvent(isRlOmOnlyNo);
		}
		
		for(String ledger : ledgerList) {
			iSearch.sendKeys(Keys.CLEAR);
			iSearch.clear();
			iSearch.sendKeys(ledger);
			TestUtil.javaScriptClickEvent(this.ledger);
		}
		
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
}
