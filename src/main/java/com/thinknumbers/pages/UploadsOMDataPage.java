package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsOMDataPage extends BaseTest{

	@FindBy(xpath="//span[text()='Upload']")
	WebElement btUpload;
	
	@FindBy(xpath="//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//input[@name='Document' and @type='file']")
	WebElement uploadFile;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//tbody//tr[2]//td[1]")
	WebElement omGroup1;
	
	@FindBy(xpath="//div[@class='row'][1]/div[1]//input[@name='selectedMonth']")
	WebElement optJan;
	
	@FindBy(xpath="//div[@class='row'][1]/div[2]//input[@name='selectedMonth']")
	WebElement optFeb;
	
	@FindBy(xpath="//div[@class='row'][1]/div[3]//input[@name='selectedMonth']")
	WebElement optMar;
	
	@FindBy(xpath="//div[@class='row'][2]/div[1]//input[@name='selectedMonth']")
	WebElement optApr;
	
	@FindBy(xpath="//div[@class='row'][2]/div[2]//input[@name='selectedMonth']")
	WebElement optMay;
	
	@FindBy(xpath="//div[@class='row'][2]/div[3]//input[@name='selectedMonth']")
	WebElement optJun;
	
	@FindBy(xpath="//div[@class='row'][3]/div[1]//input[@name='selectedMonth']")
	WebElement optJul;
	
	@FindBy(xpath="//div[@class='row'][3]/div[2]//input[@name='selectedMonth']")
	WebElement optAug;
	
	@FindBy(xpath="//div[@class='row'][3]/div[3]//input[@name='selectedMonth']")
	WebElement optSep;
	
	@FindBy(xpath="//div[@class='row'][4]/div[1]//input[@name='selectedMonth']")
	WebElement optOct;
	
	@FindBy(xpath="//div[@class='row'][4]/div[2]//input[@name='selectedMonth']")
	WebElement optNov;
	
	@FindBy(xpath="//div[@class='row'][4]/div[3]//input[@name='selectedMonth']")
	WebElement optDec;
	
	public UploadsOMDataPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectEntity(String entityName) {
		AppReusableComponents.angularDn("endityGroup", entityName);
	}
	
	public void selectOMGroup(String omGroup) {
		AppReusableComponents.angularDn("idOMGroup", omGroup);
	}
	
	public void selectYear(String year) {
		AppReusableComponents.angularDn("selectedYear", year);
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
	
	public void uploadOMData(String entityName, String year, String month, String filePath) {
		btUpload.click();
		selectEntity(entityName);
		selectOMGroup(omGroup1.getText());
		selectYear(year);
		selectMonth(month);
		uploadFile.sendKeys(filePath);
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
}
