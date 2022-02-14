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
import com.thinknumbers.pages.UploadsDBUploadPage;
import com.thinknumbers.pages.UploadsTBPage;
import com.thinknumbers.pages.UploadsTrialComputationPage;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class UploadsTrialComputationPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadsTBPage uploadsTBPage;
	UploadsTrialComputationPage uploadsTrialComputationPage;
	UploadsDBUploadPage uploadsDBUploadPage;
	
	String filePathValid = "C:/Users/ahmed/OneDrive/Desktop/TrialBalance/trialbalance_Valid.xls";
	
	
	public UploadsTrialComputationPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadsDBUploadPage = new UploadsDBUploadPage();
		uploadsTBPage = new UploadsTBPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		//homePage.clearAlertMessage();
		uploadsTrialComputationPage = masterPage.goToUploadsTrialComputationPage();
	}
	
	
	@Test(priority=1, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void viewTrialBalanceStatusTest() {
		uploadsTrialComputationPage.viewTrialComputationStatus(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1));
		uploadsTrialComputationPage.isTrialBalanceUploaded(TestUtil.getCellData("Month", 1).toLowerCase());	
		Assert.assertTrue(uploadsTrialComputationPage.isTrialBalanceUploaded(TestUtil.getCellData("Month", 1).toLowerCase()));

	}

	@Test(priority=2, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void viewDaybookUploadStatusTest() {
		uploadsTrialComputationPage.viewTrialComputationStatus(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1));
		uploadsTrialComputationPage.isDaybookUploaded(TestUtil.getCellData("Month", 1).toLowerCase());	
		Assert.assertTrue(uploadsTrialComputationPage.isDaybookUploaded(TestUtil.getCellData("Month", 1).toLowerCase()));
	}
	
	@Test(priority=3, enabled=true)
	public void runClosingTrialBalanceTest() {
		uploadsTrialComputationPage.viewTrialComputationStatus(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1));
		uploadsTrialComputationPage.trialComputation(TestUtil.getCellData("Month", 1).toLowerCase());
		Assert.assertEquals(uploadsTrialComputationPage.readAlertMessage(), "Trial Balance Calculated Successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}

}
