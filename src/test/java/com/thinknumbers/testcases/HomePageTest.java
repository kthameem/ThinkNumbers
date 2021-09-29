package com.thinknumbers.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MastersPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MastersPage mastersPage;
	
	public HomePageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
	}
	
	@Test(priority=1)
	public void verifyHomePageIconsTest() {
		softAssert.assertEquals(homePage.readAlertMessage(), "Logged In Successfully");
		homePage.clearAlertMessage();
		softAssert.assertEquals(homePage.verifyPageTitle(), "Thinknumbers");
		softAssert.assertTrue(homePage.verifyDashboardIcon());
		softAssert.assertTrue(homePage.verifyReportsIcon());
		softAssert.assertTrue(homePage.verifyMasterIcon());
		softAssert.assertTrue(homePage.verifyMastersIcon());
		softAssert.assertTrue(homePage.verifySettingsIcon());
		softAssert.assertTrue(homePage.verifyLogoutIcon());
		softAssert.assertTrue(homePage.verifyThemeSettingsButton());
		softAssert.assertTrue(homePage.verifyRefreshButton());
		softAssert.assertAll();
	}
	
	@Test(priority=2)
	public void selectDashboard() {
		homePage.selectDashboardName("Dashboard 1");
	}
	
	@Test(priority=3)
	public void goToMastersTest() {
		mastersPage = homePage.goToMasters();
	}
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
	

}
