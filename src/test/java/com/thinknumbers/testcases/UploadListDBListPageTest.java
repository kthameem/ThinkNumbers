package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.pages.UploadListDBListPage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class UploadListDBListPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadListDBListPage uploadListDBListPage;
	
	public UploadListDBListPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadListDBListPage = new UploadListDBListPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadListDBListPage = masterPage.goToUploadListDBListPage();
	}
	

	@Test(priority=1, retryAnalyzer = IRetryAnalyser.class, enabled = true)
	public void searchResultsDaybookListTest() {
		uploadListDBListPage.searchDaybookList(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase());
		TestUtil.WaitTill_PageLoads(10);
		Assert.assertEquals(uploadListDBListPage.tableDataAvailable(), "1");
	}
	
	@Test(priority=2, retryAnalyzer = IRetryAnalyser.class, enabled = true)
	public void searchDaybookTest() throws InterruptedException {
		uploadListDBListPage.searchDaybookList(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase());
		Thread.sleep(2000);
		Assert.assertEquals(uploadListDBListPage.searchDaybook("CGST (INPUT) - 9%"), "CGST (INPUT) - 9%");
	}
	
	
	@Test(priority=3, enabled=true)
	public void daybookNoDataTest() {
		uploadListDBListPage.searchDaybookList(AppReusableComponents.getEntityName(), "2010", AppReusableComponents.getMonth().toUpperCase());
		TestUtil.WaitTill_PageLoads(20);
		Assert.assertTrue(uploadListDBListPage.isDaybookDataAvailable());
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
