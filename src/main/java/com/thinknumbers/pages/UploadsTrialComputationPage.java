package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsTrialComputationPage extends BaseTest{

	@FindBy(xpath="//span[text()='View']")
	WebElement btView;
	
	@FindBy(xpath="//div[text()='25%']")
	WebElement closingTB25;
	
	@FindBy(xpath="//div[text()='50%']")
	WebElement closingTB50;
	
	@FindBy(xpath="//div[text()='75%']")
	WebElement closingTB75;
	
	@FindBy(xpath="//div[text()='100%']")
	WebElement isClosingTBComputed;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	//Trial Balance Calculated Successfully
	
	public UploadsTrialComputationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void selectEntityName(String entityName) {
		AppReusableComponents.angularDn("entityName", entityName);
	}
	
	public void selectYear(String fromYear) {
		AppReusableComponents.angularDn("year", fromYear);
	}
	
	public void viewTrialComputationStatus(String entityName, String fromYear) {
		selectEntityName(entityName);
		selectYear(fromYear);
		//btView.click();
	}
	
	public boolean isTrialBalanceUploaded(String month) {
		WebElement trialBalance = driver.findElement(By.xpath("//tbody//tr/td[text()='" + month + "']//following-sibling::td[1]//mat-icon"));
		TestUtil.waitUntilVisible(5000, trialBalance);
		return driver.findElement(By.xpath("//tbody//tr/td[text()='" + month + "']//following-sibling::td[1]//mat-icon[text()='done']")).isDisplayed();
	}
	
	public boolean isDaybookUploaded(String month) {
		WebElement daybook = driver.findElement(By.xpath("//tbody//tr/td[text()='" + month + "']//following-sibling::td[2]//mat-icon"));
		TestUtil.waitUntilVisible(5000, daybook);
		return driver.findElement(By.xpath("//tbody//tr/td[text()='" + month + "']//following-sibling::td[2]//mat-icon[text()='done']")).isDisplayed();
	}
	
	public void trialComputation(String month) {
		if (isDaybookUploaded(month) || isTrialBalanceUploaded(month)) {
			WebElement trialComputation = driver.findElement(By.xpath("//tbody//tr/td[text()='" + month + "']//following-sibling::td[3]//mat-icon[text()='search']"));
			trialComputation.click();
			TestUtil.waitUntilVisible(10000, closingTB25);
			TestUtil.waitUntilVisible(10000, closingTB50);
			TestUtil.waitUntilVisible(10000, closingTB75);
			TestUtil.waitUntilVisible(20000, isClosingTBComputed);
			TestUtil.waitUntilVisible(20000, alertMessage);
		}
		
	}
	
}
