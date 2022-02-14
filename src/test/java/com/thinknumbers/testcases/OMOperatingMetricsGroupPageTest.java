package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.OMOperatingMetricsGroupPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class OMOperatingMetricsGroupPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	OMOperatingMetricsGroupPage oMOperatingMetricsGroupPage;
	
	String sheetName = "OMGroup";
	
	public OMOperatingMetricsGroupPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		oMOperatingMetricsGroupPage = new OMOperatingMetricsGroupPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		oMOperatingMetricsGroupPage = masterPage.goToOMOperatingMetricsGroupPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addOMGroupTest(String name, String ledgerType) {
		oMOperatingMetricsGroupPage.addOMGroup(name, ledgerType);
		Assert.assertEquals(oMOperatingMetricsGroupPage.readAlertMessage(), "Success");
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void duplicateOMGroupTest(String name, String ledgerType) {
		oMOperatingMetricsGroupPage.addOMGroup(name, ledgerType);
		Assert.assertEquals(oMOperatingMetricsGroupPage.readAlertMessage(), "Failed");
	}
	
	@AfterMethod
	public void tearDown() {
		TestUtil.SwitchToMainWindow();
		driver.quit();
	}

}
