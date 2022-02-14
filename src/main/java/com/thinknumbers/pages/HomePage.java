package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class HomePage extends BaseTest{
	
	@FindBy(xpath="//img[@title='Dashboard']")
	WebElement dashboardIcon;
	
	@FindBy(xpath="//img[@title='Reports']")
	WebElement reportsIcon;
	
	@FindBy(xpath="//img[@title='Master']")
	WebElement masterIcon;
		
	@FindBy(xpath="//img[@title='Settings']")
	WebElement settingsIcon;
	
	@FindBy(xpath="//img[@title='Logout']")
	WebElement logoutIcon;
	
	@FindBy(xpath="//button[@class='btn shadow bg-white rounded' and @title='REFRESH']")
	WebElement refreshButton;
	
	@FindBy(xpath="//span[@id='normalimage2' and @title='Expand(F11)']//img")
	WebElement fullScreenButton;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(xpath="//div//mat-dialog-container[@role='dialog']")
	WebElement loadingIcon;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyDashboardIcon() {
		return dashboardIcon.isDisplayed();
	}
	
	public boolean verifyReportsIcon() {
		return reportsIcon.isDisplayed();
	}
	
	public boolean verifyMasterIcon() {
		return masterIcon.isDisplayed();
	}
	
	public boolean verifySettingsIcon() {
		return settingsIcon.isDisplayed();
	}
	
	public boolean verifyLogoutIcon() {
		return logoutIcon.isDisplayed();
	}
	
	public boolean verifyRefreshButton() {
		return refreshButton.isDisplayed();
	}
	
	public void logoutThinkNumbers() {
		logoutIcon.click();
	}
	
	public void pressF11() {
		fullScreenButton.click();
	}
	
	public void refreshDashboard() {
		refreshButton.click();
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectDashboardName(String dashboardName) {
		AppReusableComponents.angularDn("dashboardName", dashboardName);

	}
	
	public MasterPage goToMaster() {
		masterIcon.click();
		return new MasterPage();
	}
	
	public boolean isLoadingIconAvailable() {
		return loadingIcon.isDisplayed();
	}
	

}
