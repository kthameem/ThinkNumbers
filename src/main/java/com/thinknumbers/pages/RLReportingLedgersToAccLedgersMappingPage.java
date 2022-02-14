package com.thinknumbers.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.TestUtil;

public class RLReportingLedgersToAccLedgersMappingPage extends BaseTest{
	
	@FindBy(xpath="//input[@type='search']")
	WebElement iSearch;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//div[contains(text(),'Showing')]")
	WebElement noOfEntries;
	
	@FindBy(xpath="//mat-option[1]")
	WebElement reportingLedger;
	
	@FindBy(xpath="//div[@role='tab']//div[text()='Revenue']")
	WebElement revenueTab;
	
	@FindBy(xpath="//div[@role='tab']//div[text()='Expense']")
	WebElement expenseTab;
	
	@FindBy(xpath="//div[@role='tab']//div[text()='Liability']")
	WebElement liabilityTab;
	
	@FindBy(xpath="//div[@role='tab']//div[text()='Asset']")
	WebElement assetTab;
	
	@FindBy(xpath="//a[text()='Next']//parent::li//preceding-sibling::li[1]")
	WebElement lastPage;
	
	@FindBy(xpath="//a[text()='1']")
	WebElement firstPage;
	
	public RLReportingLedgersToAccLedgersMappingPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	
	public void goToExpenseTab() {
		expenseTab.click();
	}
	
	public void goToLiabilityTab() {
		liabilityTab.click();
	}
	
	public void goToAssetTab() {
		assetTab.click();
	}
	
	public void mapRL() throws InterruptedException {
		
		TestUtil.javaScriptClickEvent(lastPage);
	
		String e = noOfEntries.getText();
		System.out.println(e);
		final Pattern p = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");
	    Matcher m = p.matcher(e);

	    // if an occurrence if a pattern was found in a given string...
	    if (m.find()) {
	    	System.out.println(m.group(1)); // second matched digits
	    }
	    
	    int entries = Integer.parseInt(m.group(1));
	    
	    System.out.println(entries);
	    
	    TestUtil.javaScriptClickEvent(firstPage);
	    
	    for(int i=14; i<=30; i++) {
	    //for(int i=1; i<=entries; i++) {	
	    	iSearch.sendKeys(Keys.CLEAR);
			iSearch.clear();
			String sNo = Integer.toString(i);
			iSearch.sendKeys(sNo);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tbody//tr//td[1][text()='"+sNo+"']//following-sibling::td//i")).click();
			reportingLedger.click();
			Thread.sleep(1000);
	    }
		
	}
	
	

}
