package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsDBUploadPage extends BaseTest{
	
	@FindBy(xpath="//div//mat-radio-group[@name='bookType']//mat-radio-button[1]//div")
	WebElement typeDaybook;
	
	@FindBy(xpath="//div//mat-radio-group[@name='bookType']//mat-radio-button[2]//div")
	WebElement typeOutsideBookEntries;
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//button//span[text()='Validate']")
	WebElement btValidate;
	
	@FindBy(xpath="//button//span[text()='Upload']")
	WebElement btUpload;
	
	@FindBy(xpath="//button//span[text()='Freeze']")
	WebElement btFreeze;
	
	@FindBy(xpath="//button//span[text()='SELECTED MONTH & SUBSEQUENCT']")
	WebElement btReplaceDaybook;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//div[@class='row'][1]/div[1]//input[@name='monthValue']")
	WebElement optJan;
	
	@FindBy(xpath="//div[@class='row'][1]/div[2]//input[@name='monthValue']")
	WebElement optFeb;
	
	@FindBy(xpath="//div[@class='row'][1]/div[3]//input[@name='monthValue']")
	WebElement optMar;
	
	@FindBy(xpath="//div[@class='row'][2]/div[1]//input[@name='monthValue']")
	WebElement optApr;
	
	@FindBy(xpath="//div[@class='row'][2]/div[2]//input[@name='monthValue']")
	WebElement optMay;
	
	@FindBy(xpath="//div[@class='row'][2]/div[3]//input[@name='monthValue']")
	WebElement optJun;
	
	@FindBy(xpath="//div[@class='row'][3]/div[1]//input[@name='monthValue']")
	WebElement optJul;
	
	@FindBy(xpath="//div[@class='row'][3]/div[2]//input[@name='monthValue']")
	WebElement optAug;
	
	@FindBy(xpath="//div[@class='row'][3]/div[3]//input[@name='monthValue']")
	WebElement optSep;
	
	@FindBy(xpath="//div[@class='row'][4]/div[1]//input[@name='monthValue']")
	WebElement optOct;
	
	@FindBy(xpath="//div[@class='row'][4]/div[2]//input[@name='monthValue']")
	WebElement optNov;
	
	@FindBy(xpath="//div[@class='row'][4]/div[3]//input[@name='monthValue']")
	WebElement optDec;
	
	@FindBy(xpath="//a[text()='DayBook List']")
	WebElement dayBookList;
	
	@FindBy(xpath="//a[text()='Unfreeze List']")
	WebElement unfreezeList;
	
	@FindBy(xpath="//a[text()='Daybook RLMap']")
	WebElement daybookRLMap;	
	
	@FindBy(xpath="//a[text()='Daybook RLMap Lists']")
	WebElement daybookRLMapLists;
	
	@FindBy(xpath="//div[text()='100%']")
	WebElement isDaybookDone;
	
	public UploadsDBUploadPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		TestUtil.waitUntilVisible(1000, alertMessage);
		return alertMessage.getText();
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
	
	public void selectMonth(String month) {
		switch(month) 
		{
			case "Jan":
				optJan.click();
				break;
			case "Feb":
				optFeb.click();
				break;
			case "Mar":
				optMar.click();
				break;
			case "Apr":
				optApr.click();
				break;
			case "May":
				optMay.click();
				break;
			case "Jun":
				optJun.click();
				break;
			case "Jul":
				optJul.click();
				break;
			case "Aug":
				optAug.click();
				break;
			case "Sep":
				optSep.click();
				break;
			case "Oct":
				optOct.click();
				break;
			case "Nov":
				optNov.click();
				break;
			case "Dec":
				optDec.click();
				break;
		}
	}
	
	public void waitTillDabookCompleted() {
		TestUtil.waitUntilVisible(10000, isDaybookDone);
		TestUtil.waitUntilVisible(10000, alertMessage);
	}
	
	public void validateDaybook(String entityName, String fromYear, String month, String filePath) {
		typeDaybook.click();
		selectEntityName(entityName);
		selectYear(fromYear);
		selectMonth(month);
		uploadFile.sendKeys(filePath);
		btValidate.click();
	}
	
	public void uploadDaybok() {
		btUpload.click();
		//if(btReplaceDaybook.isDisplayed()) {
			//btReplaceDaybook.click();
		//}
	}
	
	public void freezeDaybook(String entityName, String fromYear, String month) {
		selectEntityName(entityName);
		selectYear(fromYear);
		selectMonth(month);
		TestUtil.waitUntilClickable(1000, btFreeze);
		btFreeze.click();
	}
	

}
