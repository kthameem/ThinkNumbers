package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class UploadsFasTBValidatePage extends BaseTest{


	@FindBy(xpath="//span[text()='Search']")
	WebElement btSearch;
	
	@FindBy(xpath="//tfoot//td[2]")
	WebElement totalTB;
	
	@FindBy(xpath="//tfoot//td[3]")
	WebElement totalFas;
	
	@FindBy(xpath="//tfoot//td[4]")
	WebElement totalVariance;
	
	public UploadsFasTBValidatePage() {
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
	
	public boolean validateVariance() {
		float diff = Float.parseFloat(totalFas.getText()) - Float.parseFloat(totalTB.getText());
		if(diff==Float.parseFloat(totalVariance.getText())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
