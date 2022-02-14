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
import com.thinknumbers.pages.UploadListUnfreezeListPage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class UploadListUnfreezeListPageTest extends BaseTest{
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	UploadListUnfreezeListPage uploadListUnfreezeListPage;
	
	public UploadListUnfreezeListPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		uploadListUnfreezeListPage = new UploadListUnfreezeListPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		uploadListUnfreezeListPage = masterPage.goToUploadListUnfreezeListPage();
	}
	
	@Test(priority=1, enabled=true)
	public void unfreezeDaybookTest() {
		uploadListUnfreezeListPage.unfreezeDaybook(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth());
		Assert.assertEquals(uploadListUnfreezeListPage.readAlertMessage(), "Unfreeze Is Successful");
	}
	
	@Test(priority=2, enabled =true)
	public void alreadyUnfreezedDaybookTest() {
		uploadListUnfreezeListPage.unfreezeDaybook(AppReusableComponents.getEntityName(), AppReusableComponents.getYear(), AppReusableComponents.getMonth());
		Assert.assertEquals(uploadListUnfreezeListPage.readAlertMessage(), "Already Unfreezed");
	}
	
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
