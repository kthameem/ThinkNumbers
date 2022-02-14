package com.thinknumbers.testcases;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.FasMastersFasGroupListPage;
import com.thinknumbers.pages.FasMastersMapWithLedgerPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class FasMastersMapWithLedgerPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	FasMastersMapWithLedgerPage fasMastersMapWithLedgerPage;
	FasMastersFasGroupListPage fasMastersFasGroupListPage;
	
	
	public FasMastersMapWithLedgerPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		fasMastersFasGroupListPage = new FasMastersFasGroupListPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		fasMastersMapWithLedgerPage = masterPage.goToFasMastersMapWithLedgerPage();
	}
	
	@Test(priority=1, enabled=true)
	public void mapLedgerTest() {
		fasMastersMapWithLedgerPage.mapLedger("Balance Sheet", "Asset", "Fixed Assets", "test1");
		fasMastersMapWithLedgerPage.saveLedger();
		Assert.assertEquals(fasMastersMapWithLedgerPage.readAlertMessage(), "Ledger Map Added Successfully");
	}
	
	@Test(priority=2, enabled=true)
	public void resetLedgerTest() {
		fasMastersMapWithLedgerPage.resetLedger();
		Assert.assertEquals(fasMastersMapWithLedgerPage.readAlertMessage(), "Please Select Accounting Group Type");
	}
	
	@Test(priority=3, enabled=true)
	public void validateFasGroupRevenueTest() throws InterruptedException {
		String[] fasGroupList = fasMastersMapWithLedgerPage.getPLRevenue();
		System.out.println(Arrays.toString(fasGroupList));
		for(String fasGroup:fasGroupList) {
			masterPage.goToFasMastersFasGroupListPage();
			Thread.sleep(2000);
			TestUtil.WaitTill_PageLoads(10);
			softAssert.assertEquals(fasMastersFasGroupListPage.searchFasGroup(fasGroup), fasGroup);
			softAssert.assertEquals(fasMastersFasGroupListPage.validateFasGroup(fasGroup), "Revenue");
		}
		softAssert.assertAll();
	}
	
	@Test(priority=4, enabled=true)
	public void validateFasGroupExpenseTest() throws InterruptedException {
		String[] fasGroupList = fasMastersMapWithLedgerPage.getPLExpense();
		System.out.println(Arrays.toString(fasGroupList));
		for(String fasGroup:fasGroupList) {
			masterPage.goToFasMastersFasGroupListPage();
			Thread.sleep(2000);
			TestUtil.WaitTill_PageLoads(10);
			softAssert.assertEquals(fasMastersFasGroupListPage.searchFasGroup(fasGroup), fasGroup);
			softAssert.assertEquals(fasMastersFasGroupListPage.validateFasGroup(fasGroup), "Expense");
		}
		softAssert.assertAll();
	}
	
	@Test(priority=5, enabled=true)
	public void validateFasGroupLiabilityTest() throws InterruptedException {
		String[] fasGroupList = fasMastersMapWithLedgerPage.getBSLiability();
		System.out.println(Arrays.toString(fasGroupList));
		for(String fasGroup:fasGroupList) {
			masterPage.goToFasMastersFasGroupListPage();
			Thread.sleep(2000);
			TestUtil.WaitTill_PageLoads(10);
			softAssert.assertEquals(fasMastersFasGroupListPage.searchFasGroup(fasGroup), fasGroup);
			softAssert.assertEquals(fasMastersFasGroupListPage.validateFasGroup(fasGroup), "Liability");
		}
		softAssert.assertAll();
	}
	
	@Test(priority=6, enabled=true)
	public void validateFasGroupAssetTest() throws InterruptedException {
		String[] fasGroupList = fasMastersMapWithLedgerPage.getBSAsset();
		System.out.println(Arrays.toString(fasGroupList));
		for(String fasGroup:fasGroupList) {
			masterPage.goToFasMastersFasGroupListPage();
			Thread.sleep(2000);
			TestUtil.WaitTill_PageLoads(10);
			softAssert.assertEquals(fasMastersFasGroupListPage.searchFasGroup(fasGroup), fasGroup);
			softAssert.assertEquals(fasMastersFasGroupListPage.validateFasGroup(fasGroup), "Asset");
		}
		softAssert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
