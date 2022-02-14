package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class UserDetailsUserPage extends BaseTest{
	
	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement inputSearch;
	
	@FindBy(name="loginname")
	WebElement inputUserName;
	
	@FindBy(name="logpassword")
	WebElement inputUserPwd;
	
	@FindBy(xpath="//app-credential-add//button//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//app-credential-add//button//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//div[contains(text(),'Login Name')]//following-sibling::div")
	WebElement viewUserName;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public UserDetailsUserPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String searchUser(String userName) {
		return AppReusableComponents.searchTable(userName);
	}
	
	public void selectUserType(String userName) {
		AppReusableComponents.angularDn("usertype", userName);
	}
	
	public void addUser(String urName, String urType, String urPwd) {
		btAdd.click();
		inputUserName.sendKeys(urName);
		selectUserType(urType);
		inputUserPwd.sendKeys(urPwd);
		btSave.click();
	}
	
	public String viewUser(String username) {
		AppReusableComponents.viewTable(username);
		String name = viewUserName.getText();
		return name;
		
	}
	
	public void closeUser() {
		btClose.click();
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
}
