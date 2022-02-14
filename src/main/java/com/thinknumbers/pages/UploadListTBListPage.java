package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class UploadListTBListPage extends BaseTest{

	@FindBy(xpath="//span[text()='Search']")
	WebElement btSearch;
	
	@FindBy(xpath="//div[@class='dataTables_scrollFootInner']//tfoot//td[2]")
	WebElement debitTotal;
	
	@FindBy(xpath="//div[@class='dataTables_scrollFootInner']//tfoot//td[3]")
	WebElement creditTotal;

	@FindBy(xpath="//div[@class='dataTables_scrollFootInner']//tfoot//td[4]")
	WebElement openingBalanceTotal;
	
	@FindBy(xpath="//div[@class='dataTables_scrollFootInner']//tfoot//td[5]")
	WebElement daybookDebitTotal;
	
	@FindBy(xpath="//div[@class='dataTables_scrollFootInner']//tfoot//td[6]")
	WebElement daybookCreditTotal;
	
	@FindBy(xpath="//div[@class='dataTables_scrollFootInner']//tfoot//td[7]")
	WebElement closingBalanceTotal;
	
	@FindBy(xpath="//tr//td[text()='No data available in table']")
	WebElement noTrialBalance;
	
	public UploadListTBListPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void selectEntityName(String entityName) {
		AppReusableComponents.angularDn("entityName", entityName);
	}
	
	public void selectYear(String fromYear) {
		AppReusableComponents.angularDn("year", fromYear);
	}
	
	public void selectMonth(String monthValue) {
		AppReusableComponents.angularDn("monthvalue", monthValue);
	}
	
	public void searchTrailBalance(String entityName, String fromYear, String monthValue) {
		selectEntityName(entityName);
		selectYear(fromYear);
		selectMonth(monthValue);
		btSearch.click();
	}
	
	public boolean validateDebitCredit() {
		System.out.println(debitTotal.getText());
		System.out.println(creditTotal.getText());
		return debitTotal.getText().contentEquals(creditTotal.getText());
	}
	
	public boolean noTrialBalanceMsg() {
		return noTrialBalance.isDisplayed();
	}
	
	public float getCalculatedClosingTrialBalance() {
		String dayBookDebit = daybookDebitTotal.getText().replaceAll(",", "");
		String dayBookCredit = daybookCreditTotal.getText().replaceAll(",", "");
		String openBalance = openingBalanceTotal.getText().replaceAll(",", "");
		System.out.println("Dabybook Debit is "+dayBookDebit);
		return Float.parseFloat(openBalance) + Float.parseFloat(dayBookDebit) - Float.parseFloat(dayBookCredit);
	}
	
	public float getClosingTrialBalance() {
		String closeBalance = closingBalanceTotal.getText().replaceAll(",", "");
		return Float.parseFloat(closeBalance);
	}
	
}
