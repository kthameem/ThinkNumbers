package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MastersPage;
import com.thinknumbers.pages.SubMasterCurrencyPage;
import com.thinknumbers.pages.SubMasterLedgerPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class SubMasterLedgerPageTest extends TestBase {
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MastersPage mastersPage;
	SubMasterCurrencyPage subMasterCurrencyPage;
	SubMasterLedgerPage subMasterLedgerPage;
	
	String sheetName = "LedgerList";
	String filePath = "C:/Users/ahmed/OneDrive/Desktop/ledger.xls";
	
	public SubMasterLedgerPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		mastersPage = new MastersPage();
		subMasterCurrencyPage = new SubMasterCurrencyPage();
		subMasterLedgerPage = new SubMasterLedgerPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		mastersPage = homePage.goToMasters();
		homePage.clearAlertMessage();
		subMasterCurrencyPage = mastersPage.goToSubMasterPage();
		subMasterLedgerPage = subMasterCurrencyPage.goToLedgerList();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData")
	public void addLedgerListTest(String ledgerCode, String ledgerName, String ledgerType, String ledgerSubType, String ledgerGroup, String status) {
		subMasterLedgerPage.addLedger(ledgerCode, ledgerName, ledgerType, ledgerSubType, ledgerGroup, status);
		if(subMasterLedgerPage.readAlertMessage() != "Ledger Added Successfully") {
			try {
				subMasterLedgerPage.closeLedger();
			} finally {
				System.out.println(subMasterLedgerPage.readAlertMessage());
			}
		}
		softAssert.assertEquals(subMasterLedgerPage.readAlertMessage(), "Ledger Added Successfully");
		softAssert.assertAll();
		subMasterLedgerPage.clearAlertMessage();
	}
	
	@Test(priority=2)
	public void uploadLedgerTest() {
		subMasterLedgerPage.uploadLedger(filePath);
		if(subMasterLedgerPage.readAlertMessage() != "Ledger And Group Uploaded Successfully") {
			subMasterLedgerPage.closeLedger();
		}
		softAssert.assertEquals(subMasterLedgerPage.readAlertMessage(), "Ledger And Group Uploaded Successfully");
		softAssert.assertAll();
		subMasterLedgerPage.clearAlertMessage();
	}
	
	@Test(priority=2)
	public void editLedgerTest() {
		subMasterLedgerPage.editLedger("test");
		if(subMasterLedgerPage.readAlertMessage() != "Ledger Updated Successfully") {
			subMasterLedgerPage.closeLedger();
		}
		softAssert.assertEquals(subMasterLedgerPage.readAlertMessage(), "Ledger Updated Successfully");
		softAssert.assertAll();
		subMasterLedgerPage.clearAlertMessage();
		
	}
	
	@Test(priority=3)
	public void incorrectLedgerComboTest() throws InterruptedException {
		Thread.sleep(2000);
		subMasterLedgerPage.searchLedger("test");
		
		subMasterLedgerPage.incorrectLedgerTypeCombo("test", "P&L", "Liability");
		System.out.println(subMasterLedgerPage.readAlertMessage());
		
		//Add If once defect is fixed
		
		//if(subMasterLedgerPage.readAlertMessage() != "Ledger Updated Successfully") {
			//subMasterLedgerPage.closeLedger();
			
		//} else {
			softAssert.assertTrue(false, "Incorrect combo saved");
		//}
		softAssert.assertAll();
		subMasterLedgerPage.clearAlertMessage();
	}
	
	@Test
	public void searchLedgerTest() throws InterruptedException {
		Thread.sleep(2000);
		String searchResult = subMasterLedgerPage.searchLedger("test");
		Thread.sleep(1000);
		Assert.assertEquals(searchResult, "test");
	}
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}

}
