package com.thinknumbers.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class FasMastersMapWithLedgerPage extends BaseTest{

	@FindBy(xpath="//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[text()='Reset']")
	WebElement btReset;
	
	@FindBy(xpath="//span[text()='Ledgers']")
	WebElement dnLedger;
	
	@FindBy(xpath="//input[@type='text']")
	WebElement inputSearch;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(name="parent_group")
	WebElement dnParentGroup;
		
	public FasMastersMapWithLedgerPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectFasType(String fasType) {
		AppReusableComponents.angularDn("fas_type", fasType);
	}
	
	public void selectFasSubType(String fasSubType) {
		AppReusableComponents.angularDn("fas_subType", fasSubType);
	}
	
	public void selectParentGroup(String parentGroup) {
		AppReusableComponents.angularDn("parent_group", parentGroup);
	}
	
	public void selectLedgers(String accLedger) {
		AppReusableComponents.angularDn("selectedledgers", accLedger);
	}
	
	public void mapLedger(String fasType, String fasSubType, String parentGroup, String accLedger) {
		selectFasType(fasType);
		selectFasSubType(fasSubType);
		selectParentGroup(parentGroup);
		dnLedger.click();
		if(accLedger.contains("Select All")) {
			driver.findElement(By.xpath("//mat-option[1]")).click();
		} else {
			inputSearch.sendKeys(accLedger);
			driver.findElement(By.xpath("//span[contains(text(),'"+accLedger+"')]")).click();
		}		
	}
	
	public void saveLedger() {
		TestUtil.javaScriptClickEvent(btSave);
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void resetLedger() {
		selectFasType("P&L");
		selectFasSubType("Revenue");
		btReset.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public String[] getPLRevenue(){
		selectFasType("P&L");
		selectFasSubType("Revenue");
		TestUtil.WaitTill_PageLoads(10);
		dnParentGroup.click();
		List<WebElement> parentAccGroup = driver.findElements(By.xpath("//mat-option"));
		String[] fasGroupList = new String[parentAccGroup.size()];
		for(int i=0; i<parentAccGroup.size(); i++) {
			fasGroupList[i] = parentAccGroup.get(i).getText();
		}
		parentAccGroup.get(0).click();
		return fasGroupList;
	}
	
	public String[] getPLExpense(){
		selectFasType("P&L");
		selectFasSubType("Expense");
		TestUtil.WaitTill_PageLoads(10);
		dnParentGroup.click();
		List<WebElement> parentAccGroup = driver.findElements(By.xpath("//mat-option"));
		String[] fasGroupList = new String[parentAccGroup.size()];
		for(int i=0; i<parentAccGroup.size(); i++) {
			fasGroupList[i] = parentAccGroup.get(i).getText();
		}
		parentAccGroup.get(0).click();
		return fasGroupList;
	}

	public String[] getBSLiability(){
		selectFasType("Balance Sheet");
		selectFasSubType("Liability");
		TestUtil.WaitTill_PageLoads(10);
		dnParentGroup.click();
		List<WebElement> parentAccGroup = driver.findElements(By.xpath("//mat-option"));
		String[] fasGroupList = new String[parentAccGroup.size()];
		for(int i=0; i<parentAccGroup.size(); i++) {
			fasGroupList[i] = parentAccGroup.get(i).getText();
		}
		parentAccGroup.get(0).click();
		return fasGroupList;
	}
	
	public String[] getBSAsset(){
		selectFasType("Balance Sheet");
		selectFasSubType("Asset");
		TestUtil.WaitTill_PageLoads(10);
		dnParentGroup.click();
		List<WebElement> parentAccGroup = driver.findElements(By.xpath("//mat-option"));
		String[] fasGroupList = new String[parentAccGroup.size()];
		for(int i=0; i<parentAccGroup.size(); i++) {
			fasGroupList[i] = parentAccGroup.get(i).getText();
		}
		parentAccGroup.get(0).click();
		return fasGroupList;
	}
	
}
