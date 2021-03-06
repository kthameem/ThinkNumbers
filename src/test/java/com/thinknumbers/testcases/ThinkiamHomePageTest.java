package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class ThinkiamHomePageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	
	public ThinkiamHomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1, enabled=true)
	public void verifyHomeTitleTest() {
		Assert.assertTrue(thinkiamHomePage.verifyTitle());
	}
	
	@Test(priority=2, enabled=false)
	public void verifyThinkiamLogoutTest() {
		TestUtil.WaitTill_PageLoads(1000);
		Assert.assertTrue(thinkiamHomePage.verifyThinkiamLogout());
	}
	
	@Test(priority=3, enabled=true)
	public void goToDashboardTest() {
		homePage = thinkiamHomePage.goToThinkNumber();	
	}
	
	@AfterMethod
	public void tearDown() {
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
	
}
