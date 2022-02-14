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
import com.thinknumbers.pages.UploadListTBListPage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class UploadListTBListPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadListTBListPage uploadListTBListPage;
	
	
	public UploadListTBListPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadListTBListPage = new UploadListTBListPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadListTBListPage =  masterPage.goToUploadListTBListPage();
	}
	
	@Test(priority=1, retryAnalyzer = IRetryAnalyser.class)
	public void validateOpeningTrialBalanceTest() throws InterruptedException {
		uploadListTBListPage.searchTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase());
		Assert.assertTrue(uploadListTBListPage.validateDebitCredit());
	}
	
	@Test(priority=2, retryAnalyzer = IRetryAnalyser.class)
	public void searchNoTrialBalanceTest() {
		uploadListTBListPage.searchTrailBalance(AppReusableComponents.getEntityName(), "2010", AppReusableComponents.getMonth().toUpperCase());
		Assert.assertTrue(uploadListTBListPage.noTrialBalanceMsg());
	}
	
	@Test(priority=3)
	public void validateClosingTrialBalanceTest() throws InterruptedException {
		uploadListTBListPage.searchTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase());	
		Assert.assertEquals(uploadListTBListPage.getClosingTrialBalance(), uploadListTBListPage.getCalculatedClosingTrialBalance());
	}

	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}

}
