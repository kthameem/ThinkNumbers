package com.thinknumbers.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.ThinkiamHomePage;

public class LoginPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	
	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test(priority=1, enabled=true)
	public void loginTest() {
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
