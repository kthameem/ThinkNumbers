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
import com.thinknumbers.pages.OMLedgerTypePage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class OMLedgerTypePageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	OMLedgerTypePage oMLedgerTypePage;
	
	public OMLedgerTypePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		oMLedgerTypePage = new OMLedgerTypePage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		oMLedgerTypePage = masterPage.goToOMLedgerTypePage();
	}
	
	@Test(priority=1, enabled=false)
	public void addLedgerTypeTest() {
		String ledgerType = TestUtil.getCellData("LedgerType", 1);
		oMLedgerTypePage.addLedgerType();
		oMLedgerTypePage.enterLedgerType(ledgerType);
		oMLedgerTypePage.saveLedgerType();
		Assert.assertEquals(oMLedgerTypePage.readAlertMessage(), "Ledger Type Added Successfully");
	}
	
	@Test(priority=2, enabled=true, retryAnalyzer = IRetryAnalyser.class)
	public void searchLedgerTypeTest() {
		String ledgerType = TestUtil.getCellData("LedgerType", 1);
		oMLedgerTypePage.searchLedgerType(ledgerType);
		Assert.assertEquals(oMLedgerTypePage.searchLedgerType(ledgerType), ledgerType);
	}
	
	@Test(priority=3, enabled=true)
	public void duplicateLedgerTypeTest() throws InterruptedException {
		String ledgerType = TestUtil.getCellData("LedgerType", 1);
		oMLedgerTypePage.addLedgerType();
		oMLedgerTypePage.enterLedgerType(ledgerType);
		oMLedgerTypePage.saveLedgerType();
		Assert.assertEquals(oMLedgerTypePage.readAlertMessage(), "Ledger Type Already Exists");
		oMLedgerTypePage.closeLedgerType();
	}
	
	@Test(priority=4, enabled=true)
	public void editLedgerTypeTest() throws InterruptedException {
		String ledgerType = TestUtil.getCellData("LedgerType", 1);
		AppReusableComponents.editTable(ledgerType);
		oMLedgerTypePage.enterLedgerType("Edited"+ledgerType);
		oMLedgerTypePage.updateLedgerType();
		Assert.assertEquals(oMLedgerTypePage.readAlertMessage(), "Ledger Type Updated Successfully");
	}
	

	@AfterMethod
	public void tearDown() {
		TestUtil.SwitchToMainWindow();
		driver.quit();
	}
}
