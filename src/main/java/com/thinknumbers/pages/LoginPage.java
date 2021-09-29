package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(name="clientname")
	WebElement client;
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public ThinkiamHomePage login(String cl, String un, String pwd) {
		client.sendKeys(cl);
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new ThinkiamHomePage();
	}

}
