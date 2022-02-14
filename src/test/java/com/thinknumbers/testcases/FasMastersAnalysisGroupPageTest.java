package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.FasMastersAnalysisGroupPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class FasMastersAnalysisGroupPageTest extends BaseTest{	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	FasMastersAnalysisGroupPage fasMastersAnalysisGroupPage;
	
	
	public FasMastersAnalysisGroupPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		fasMastersAnalysisGroupPage = new FasMastersAnalysisGroupPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
	
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		fasMastersAnalysisGroupPage = masterPage.goToFasMastersAnalysisGroupPage();
	}
	
	@Test(priority=1)
	public void deleteAnalysisGroupTest() {
		TestUtil.WaitTill_PageLoads(10);
		fasMastersAnalysisGroupPage.deleteGroup();
	}
	
	@Test(priority=2)
	public void addAnalysisGroupTest() {
		TestUtil.WaitTill_PageLoads(10);
		fasMastersAnalysisGroupPage.addGroup();
		Assert.assertEquals(fasMastersAnalysisGroupPage.readAlertMessage(), "Group Added Successfully");
	}
	
	@Test(priority=3)
	public void updateAnalysisGroupTest() {
		TestUtil.WaitTill_PageLoads(10);
		fasMastersAnalysisGroupPage.updateGroup();
		TestUtil.WaitTill_PageLoads(10);
		Assert.assertEquals(fasMastersAnalysisGroupPage.readAlertMessage(), "Group Updated Successfully");
	}
	
	@Test
	public void resetAnalysisGroupTest() {
		TestUtil.WaitTill_PageLoads(10);
		fasMastersAnalysisGroupPage.resetGroup();
		TestUtil.WaitTill_PageLoads(10);
		Assert.assertEquals(fasMastersAnalysisGroupPage.readAlertMessage(), "Group Reset Successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
