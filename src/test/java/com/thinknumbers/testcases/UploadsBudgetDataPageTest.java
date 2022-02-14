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
import com.thinknumbers.pages.UploadsBudgetDataPage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class UploadsBudgetDataPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadsBudgetDataPage uploadsBudgetDataPage;
	
	String filePath = "C:/Users/ahmed/OneDrive/Desktop/Budget/BudgetData.xlsx";
	
	public UploadsBudgetDataPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadsBudgetDataPage = new UploadsBudgetDataPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadsBudgetDataPage = masterPage.goToUploadsBudgetDataPage();
	}


	@Test(priority = 1, retryAnalyzer = IRetryAnalyser.class, enabled = true)
	public void uploadBudgetDataTest() throws InterruptedException {
		uploadsBudgetDataPage.uploadBudgetData(AppReusableComponents.getEntityName(), filePath);
		Assert.assertEquals(uploadsBudgetDataPage.readAlertMessage(), "Budget Data Uploaded Successfully");
	}
	
	
	@AfterMethod
	public void tearDown() {
		TestUtil.SwitchToMainWindow();
		driver.quit();
	}
}
