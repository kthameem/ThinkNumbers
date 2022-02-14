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
import com.thinknumbers.pages.UploadsTBPage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadsTBPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadsTBPage uploadsTBPage;
	
	String filePathInvalid = "C:/Users/ahmed/OneDrive/Desktop/TrialBalance/trialbalance_invalid.xls";
	String filePathValid = "C:/Users/ahmed/OneDrive/Desktop/TrialBalance/trialbalance_Valid.xls";
	
	
	public UploadsTBPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadsTBPage = new UploadsTBPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(100);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadsTBPage = masterPage.goToUploadsTBPage();
	}
	
	@Test(priority=1, enabled=true)
	public void trailBalanceDebitCreditNotMatchingTest() {
		uploadsTBPage.validateTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase(), filePathInvalid);
		String msgValidity = uploadsTBPage.getmsgValidateUpload();
		System.out.println(msgValidity);
		softAssert.assertEquals(msgValidity, "Not allowed to upload.");
		String msgDebitCredit = uploadsTBPage.getmsgDebitCreditValidate();
		System.out.println(msgDebitCredit);
		softAssert.assertEquals(msgDebitCredit, "Debit and Credit is not matching.");
		softAssert.assertAll();
	}
	
	@Test(priority=2, enabled=true)
	public void trialBalanceUploadAllowedTest() throws InterruptedException {
		uploadsTBPage.validateTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase(), filePathValid);
		
		uploadsTBPage.waitTillTBCompleted();
		uploadsTBPage.clearAlertMessage();
		uploadsTBPage.uploadTrialBalance();
		
		Assert.assertEquals(uploadsTBPage.readAlertMessage(), "Trial Balance Uploaded Successfully");
		uploadsTBPage.clearAlertMessage();
	}
	
	@Test(priority=3, enabled=true)
	public void trialBalanceNewLedgerCodeNameTest() {
		uploadsTBPage.validateTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase(), filePathInvalid);
		String msg = uploadsTBPage.newLedgerCodeName();
		System.out.println(msg);
		Assert.assertTrue(msg.contains("is not present in masters."));
	}
	
	@Test(priority=4, enabled=true)
	public void trialBalanceStringDebitCreditTest() {
		uploadsTBPage.validateTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase(), filePathInvalid);
		String msg = uploadsTBPage.stringDebitCredit();
		System.out.println(msg);
		Assert.assertTrue(msg.contains("is in text format, not allowed."));
	}
	
	@Test(priority=5, enabled=false)
	public void trialBalanceEmptyRowTest() {
		uploadsTBPage.validateTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase(), filePathInvalid);
		String msg = uploadsTBPage.emptyLedgerRow();
		System.out.println(msg);
		Assert.assertTrue(msg.contains("is Empty."));
	}
	 	
	@Test(priority=6, enabled=true)
	public void overrideTrialBalanceConfirmTest() {
		uploadsTBPage.validateTrailBalance(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth().toUpperCase(), filePathValid);
		
		uploadsTBPage.waitTillTBCompleted();
		uploadsTBPage.clearAlertMessage();
		uploadsTBPage.uploadTrialBalance();
		
		Assert.assertEquals(uploadsTBPage.readAlertMessage(), "Trial Balance Uploaded Successfully");
		uploadsTBPage.clearAlertMessage();
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
