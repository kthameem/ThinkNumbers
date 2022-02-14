package com.thinknumbers.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class HomePageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	
	public HomePageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
	}
	
	@Test(priority=1, enabled=true)
	public void verifyHomePageIconsTest() {
		softAssert.assertEquals(homePage.readAlertMessage(), "Logged In Successfully");
		homePage.clearAlertMessage();
		softAssert.assertEquals(homePage.verifyPageTitle(), "Thinknumbers");
		softAssert.assertTrue(homePage.verifyDashboardIcon());
		softAssert.assertTrue(homePage.verifyReportsIcon());
		softAssert.assertTrue(homePage.verifyMasterIcon());
		softAssert.assertTrue(homePage.verifySettingsIcon());
		softAssert.assertTrue(homePage.verifyLogoutIcon());
		softAssert.assertTrue(homePage.verifyRefreshButton());
		softAssert.assertAll();
	}
	
	@Test(priority=2, enabled=true)
	public void selectDashboardTest() throws InterruptedException {
		homePage.selectDashboardName("Dashboard1");
			Thread.sleep(5000);	
	}

	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
	

}
