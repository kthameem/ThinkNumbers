package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadListUnfreezeListPage extends BaseTest{
	
	@FindBy(xpath="//button//span[text()='Un Freeze']")
	WebElement btUnfreeze;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//div[@class='row']/div[1]//input[@name='daysValue']")
	WebElement optJan;
	
	@FindBy(xpath="//div[@class='row']/div[2]//input[@name='daysValue']")
	WebElement optFeb;
	
	@FindBy(xpath="//div[@class='row']/div[3]//input[@name='daysValue']")
	WebElement optMar;
	
	@FindBy(xpath="//div[@class='row']/div[4]//input[@name='daysValue']")
	WebElement optApr;
	
	@FindBy(xpath="//div[@class='row']/div[5]//input[@name='daysValue']")
	WebElement optMay;
	
	@FindBy(xpath="//div[@class='row']/div[6]//input[@name='daysValue']")
	WebElement optJun;
	
	@FindBy(xpath="//div[@class='row']/div[7]//input[@name='daysValue']")
	WebElement optJul;
	
	@FindBy(xpath="//div[@class='row']/div[8]//input[@name='daysValue']")
	WebElement optAug;
	
	@FindBy(xpath="//div[@class='row']/div[9]//input[@name='daysValue']")
	WebElement optSep;
	
	@FindBy(xpath="//div[@class='row']/div[10]//input[@name='daysValue']")
	WebElement optOct;
	
	@FindBy(xpath="//div[@class='row']/div[11]//input[@name='daysValue']")
	WebElement optNov;
	
	@FindBy(xpath="//div[@class='row']/div[12]//input[@name='daysValue']")
	WebElement optDec;
	
	public UploadListUnfreezeListPage() {
		PageFactory.initElements(driver, this);
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
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectEntityName(String entityName) {
		AppReusableComponents.angularDn("entityName", entityName);
	}
	
	public void selectYear(String fromYear) {
		AppReusableComponents.angularDn("year", fromYear);
	}
	
	public void unfreezeDaybook(String entityName, String fromYear, String month) {
		selectEntityName(entityName);
		selectYear(fromYear);
		TestUtil.WaitTill_PageLoads(10);
		selectMonth(month);
		btUnfreeze.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
}
