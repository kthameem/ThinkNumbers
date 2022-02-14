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
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class UploadsDBUploadPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadsDBUploadPage uploadsDBUploadPage;
	
	String filePathInvalid = "C:/Users/ahmed/OneDrive/Desktop/DayBook/Daybook_invalid.xls";
	String filePathValid = "C:/Users/ahmed/OneDrive/Desktop/DayBook/Daybook_valid.xlsx";
	
	public UploadsDBUploadPageTest() {
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
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadsDBUploadPage = masterPage.goToUploadsDBUploadPage();	
	}
	
	
	@Test(priority=1, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void uploadNewLedgerInDaybookTest() {
		uploadsDBUploadPage.validateDaybook(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1), filePathInvalid);
		Assert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Not Allowed To Upload");
	}
	
	@Test(priority=2, enabled=true)
	public void uploadDaybookTest() {
		uploadsDBUploadPage.validateDaybook(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1), filePathValid);
		uploadsDBUploadPage.waitTillDabookCompleted();
		softAssert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Allowed To Upload");
		uploadsDBUploadPage.clearAlertMessage();
		uploadsDBUploadPage.uploadDaybok();
		uploadsDBUploadPage.waitTillDabookCompleted();
		softAssert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Daybook Uploaded Successfully");
		softAssert.assertAll();
		
	}
	
	@Test(priority=3, enabled=false, retryAnalyzer=IRetryAnalyser.class)
	public void uploadDaybookWithoutTrialBalanceTest() {
		uploadsDBUploadPage.validateDaybook(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1), filePathValid);
		softAssert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Not Allowed To Upload");
		uploadsDBUploadPage.uploadDaybok();
		softAssert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Not Allowed To Upload");
		softAssert.assertAll();
	}
	
	
	//Bug for this test is kept on hold
	@Test(priority=4, enabled=false, retryAnalyzer=IRetryAnalyser.class)
	public void uploadDaybookCostCentreInMultipleCostCategoryTest() {
		uploadsDBUploadPage.validateDaybook(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1), filePathInvalid);
		softAssert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Not Allowed To Upload");
		uploadsDBUploadPage.uploadDaybok();
		softAssert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Not Allowed To Upload");
		softAssert.assertAll();
	}
	
	@Test(priority=5, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void freezeDaybookTest() {
		uploadsDBUploadPage.freezeDaybook(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1));
		Assert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Daybook For The Selected Month Frozen Successfully!");
	}
	
	@Test(priority=6, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void alreadyFrozenDaybookTest() {
		uploadsDBUploadPage.freezeDaybook(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1));
		Assert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Month Already Freezed!");
		uploadsDBUploadPage.readAlertMessage();
	}

	@Test(priority=7, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void uploadDaybookFrozenMonthTest() {
		uploadsDBUploadPage.validateDaybook(TestUtil.getCellData("EntityName", 1), TestUtil.getCellData("Year", 1), TestUtil.getCellData("Month", 1), filePathValid);
		uploadsDBUploadPage.waitTillDabookCompleted();
		softAssert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Allowed To Upload");
		uploadsDBUploadPage.clearAlertMessage();
		uploadsDBUploadPage.uploadDaybok();
		softAssert.assertTrue(uploadsDBUploadPage.readAlertMessage().contains("Freezed"));
		softAssert.assertAll();
	}
	
	@Test(priority=8, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void FreezeWithNoDaybookTest() {
		uploadsDBUploadPage.freezeDaybook(AppReusableComponents.getEntityName(), "2010", AppReusableComponents.getMonth());
		Assert.assertEquals(uploadsDBUploadPage.readAlertMessage(), "Unable To Freeze As Day Book Is Not Uploaded Yet For The Month.");
		uploadsDBUploadPage.readAlertMessage();
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
}
