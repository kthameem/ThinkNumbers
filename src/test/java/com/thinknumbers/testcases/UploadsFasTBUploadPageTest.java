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
import com.thinknumbers.pages.UploadsFasTBUploadPage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsFasTBUploadPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadsFasTBUploadPage uploadsFasTBUploadPage;
	
	//String filePathInvalid = "C:/Users/ahmed/OneDrive/Desktop/FasTrialBalance/trialbalance_invalid.xls";
	String filePathValid = "C:/Users/ahmed/OneDrive/Desktop/FasTrialBalance/fasTB_valid.xls";
	
	public UploadsFasTBUploadPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadsFasTBUploadPage = new UploadsFasTBUploadPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadsFasTBUploadPage = masterPage.goToUploadsFasTBUploadPage();
	}
	
	@Test(priority=1, enabled=true)
	public void fasTBUpload() {
		uploadsFasTBUploadPage.validateFasTB(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase(), filePathValid);
		softAssert.assertEquals(uploadsFasTBUploadPage.readAlertMessage(), "FAS Trial Balance Validation Success");
		uploadsFasTBUploadPage.clearAlertMessage();
		uploadsFasTBUploadPage.uploadfasTB();
		Assert.assertEquals(uploadsFasTBUploadPage.readAlertMessage(), "FAS Trial Balance Uploaded Successfully");
	}
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
}
