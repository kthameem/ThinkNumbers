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
import com.thinknumbers.pages.UploadsOMDataPage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class UploadsOMDataPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadsOMDataPage uploadsOMDataPage;
	
	String filePath = "C:/Users/ahmed/OneDrive/Desktop/OperatingMetrics/OperatingMetricsData.xlsx";
	
	public UploadsOMDataPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadsOMDataPage = new UploadsOMDataPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadsOMDataPage = masterPage.goToUploadsOMDataPage();
	}


	@Test(priority = 1, retryAnalyzer = IRetryAnalyser.class, enabled = false)
	public void uploadOMDataTest() throws InterruptedException {
		uploadsOMDataPage.uploadOMData(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth(), filePath);
		Assert.assertEquals(uploadsOMDataPage.readAlertMessage(), "Metrics Data Uploaded Successfully");
	}
	
	
	@AfterMethod
	public void tearDown() {
		TestUtil.SwitchToMainWindow();
		driver.quit();
	}
}
