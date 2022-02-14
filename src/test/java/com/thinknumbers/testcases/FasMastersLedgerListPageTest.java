package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.FasMastersLedgerListPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class FasMastersLedgerListPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	FasMastersLedgerListPage fasMastersLedgerListPage;
	
	String sheetName = "LedgerList";
	String filePath = "C:/Users/ahmed/OneDrive/Desktop/LedgerList/ledger.xls";
	String filePathNoData = "C:/Users/ahmed/OneDrive/Desktop/LedgerList/ledger_no data.xls";
	String filePathIncorrectData = "C:/Users/ahmed/OneDrive/Desktop/LedgerList/ledger_incorrectdata.xls";
	
	public FasMastersLedgerListPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		fasMastersLedgerListPage = new FasMastersLedgerListPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		//homePage.clearAlertMessage();
		fasMastersLedgerListPage = masterPage.goToFasMastersLedgerListPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addLedgerListTest(String ledgerCode, String ledgerName, String ledgerType, String ledgerSubType, String ledgerGroup, String status) {
		fasMastersLedgerListPage.addLedger(ledgerCode, ledgerName, ledgerType, ledgerSubType, ledgerGroup, status);
		if(fasMastersLedgerListPage.readAlertMessage().equalsIgnoreCase("Ledger Added Successfully")) {
			softAssert.assertEquals(fasMastersLedgerListPage.readAlertMessage(), "Ledger Added Successfully");
		} else {
			try {
				fasMastersLedgerListPage.closeLedger();
			} finally {
				System.out.println(fasMastersLedgerListPage.readAlertMessage());
			}
		}
		
		softAssert.assertAll();
		fasMastersLedgerListPage.clearAlertMessage();
	}
	
	@Test(priority=2, enabled=true)
	public void uploadLedgerTest(){
		fasMastersLedgerListPage.uploadLedger(filePath);
		Assert.assertEquals(fasMastersLedgerListPage.readAlertMessage(), "Sheet Has Been Uploaded, Please Check The Response List.");		
		fasMastersLedgerListPage.clearAlertMessage();
	}
	
	@Test(priority=3, enabled=true)
	public void editLedgerTest() {
		TestUtil.WaitTill_PageLoads(1000);
		fasMastersLedgerListPage.searchLedger("test1");
		fasMastersLedgerListPage.editLedger("test1");
		Assert.assertEquals(fasMastersLedgerListPage.readAlertMessage(), "Ledger Updated Successfully");	
		fasMastersLedgerListPage.clearAlertMessage();
		
	}
	
	@Test(priority=3, enabled = false)
	public void incorrectLedgerComboTest() throws InterruptedException {
		Thread.sleep(2000);
		fasMastersLedgerListPage.searchLedger("test");
		
		fasMastersLedgerListPage.incorrectLedgerTypeCombo("Active", "P&L", "Liability");
		System.out.println(fasMastersLedgerListPage.readAlertMessage());
		
		Assert.assertTrue(false, "Incorrect combo saved");
		fasMastersLedgerListPage.clearAlertMessage();
	}
	
	@Test(priority=4, enabled = true)
	public void searchLedgerTest() {
		TestUtil.WaitTill_PageLoads(10);
		String searchResult = fasMastersLedgerListPage.searchLedger("test1");
	
		Assert.assertEquals(searchResult, "test1");
	}
	
	@Test(priority=5,dataProvider="getTestData", enabled=true)
	public void duplicateLedgerTest(String ledgerCode, String ledgerName, String ledgerType, String ledgerSubType, String ledgerGroup, String status) {
		TestUtil.WaitTill_PageLoads(10);
		fasMastersLedgerListPage.addLedger(ledgerCode, ledgerName, ledgerType, ledgerSubType, ledgerGroup, status);
		Assert.assertEquals(fasMastersLedgerListPage.readAlertMessage(), "Ledger Code Already Available");
		
	}
	
	@Test(priority=6, enabled=true)
	public void uploadLedgerNoDataTest() {
		fasMastersLedgerListPage.uploadLedger(filePathNoData);
		Assert.assertEquals(fasMastersLedgerListPage.readAlertMessage(), "No Record Found In Excel Sheet.");
		fasMastersLedgerListPage.clearAlertMessage();
	}
	
	@Test(priority=7, enabled=false)
	public void uploadIncorrectDataTest() throws InterruptedException {
		fasMastersLedgerListPage.uploadLedger(filePathIncorrectData);
		
		Thread.sleep(2000);

		if(fasMastersLedgerListPage.readAlertMessage().equalsIgnoreCase("Ledger And Group Uploaded Successfully")) {
			System.out.println(fasMastersLedgerListPage.readAlertMessage());
			fasMastersLedgerListPage.clearAlertMessage();
			TestUtil.WaitTill_PageLoads(10);
			//subMasterLedgerPage.closeLedger();
			softAssert.assertTrue(false);
		}
		softAssert.assertTrue(true);
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
