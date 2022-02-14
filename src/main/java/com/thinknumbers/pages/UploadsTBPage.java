package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsTBPage extends BaseTest{
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//button//span[text()='Validate']")
	WebElement btValidate;
	
	@FindBy(xpath="//button//span[text()='Upload']")
	WebElement btUpload;
	
	@FindBy(xpath="//div[contains(text(),'upload')]")
	WebElement msgValidateUpload;
	
	@FindBy(xpath="//div[contains(text(),'matching')]")
	WebElement msgDebitCreditValidate;
	
	@FindBy(xpath="//tr//td//span[contains(text(),'is not present in masters.')]")
	WebElement newLedgerCodeName;
	
	@FindBy(xpath="//tr//td//span[contains(text(),'is Empty')]")
	WebElement emptyLedgerRow;
	
	@FindBy(xpath="//tr//td//span[contains(text(),'is in text format, not allowed.')]")
	WebElement stringDebitCredit;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//div[@role='alertdialog' and contains(text(),'Trial Balance Upload Successfully')]")
	WebElement uploadAlert;
	
	@FindBy(xpath="//a[text()='Trial Computation']")
	WebElement trialComputation;
	
	@FindBy(xpath="//a[text()='Trial Balance List']")
	WebElement trialBalanceList;
	
	@FindBy(xpath="//button//span[text()='OK']")
	WebElement btOk;
	
	@FindBy(xpath="//button//span[text()='CANCEL']")
	WebElement btCancel;
	
	@FindBy(xpath="//div[text()='100%']")
	WebElement isTBDone;

	public UploadsTBPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public String readUploadAlert() {
		return uploadAlert.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectEntityName(String entityName) {
		AppReusableComponents.angularDn("entityName", entityName);
	}
	
	public void selectYear(String fromYear) {
		AppReusableComponents.angularDn("fromYear", fromYear);
	}
	
	public void selectMonth(String monthValue) {
		AppReusableComponents.angularDn("monthvalue", monthValue);
	}
	
	public void validateTrailBalance(String entityName, String fromYear, String monthValue, String filePath) {
		selectEntityName(entityName);
		selectYear(fromYear);
		selectMonth(monthValue);
		uploadFile.sendKeys(filePath);
		btValidate.click();
	}
	
	public void uploadTrialBalance() {
		TestUtil.waitUntilVisible(10000, btUpload);
		btUpload.click();
		TestUtil.WaitTill_PageLoads(10);
		confirmOverride();
	}
	
	public String getmsgValidateUpload() {
		return msgValidateUpload.getText();
	}
	
	public String getmsgDebitCreditValidate() {
		return msgDebitCreditValidate.getText();
	}
	
	public String newLedgerCodeName() {
		return newLedgerCodeName.getText();
	}
	
	public String emptyLedgerRow() {
		return emptyLedgerRow.getText();
	}
	
	public String stringDebitCredit() {
		return stringDebitCredit.getText();
	}
	
	public void confirmOverride() {
		if(btOk.isDisplayed()) {
			btOk.click();
		}
		TestUtil.waitUntilVisible(10000, alertMessage);
	}
	
	public void cancelOverride() {
		if(btCancel.isDisplayed()) {
			btCancel.click();
		}
	}
	
	public void waitTillTBCompleted() {
		TestUtil.waitUntilVisible(10000, isTBDone);
		TestUtil.waitUntilVisible(10000, alertMessage);
	}
}
