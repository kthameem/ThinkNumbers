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
import com.thinknumbers.pages.ReportDesignReportSectionPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.IRetryAnalyser;
import com.thinknumbers.util.TestUtil;

public class ReportDesignReportSectionPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	ReportDesignReportSectionPage reportDesignReportSectionPage;
	
	String sheetName = "ReportSection";
	
	public ReportDesignReportSectionPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		reportDesignReportSectionPage = new ReportDesignReportSectionPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		reportDesignReportSectionPage = masterPage.goToReportDesignReportSectionPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addALReport(String name, String dispName, String groupName, String type, String isCalc, String figConv, 
			String exchRate, String aggregate, String dashDispName, String isRlOm, String formula, String ledgerList, String uomType) {
	
		String ledger[] =ledgerList.split(",");
		
		reportDesignReportSectionPage.alReport(name, dispName, groupName, type, isCalc, figConv, 
				exchRate, aggregate, dashDispName, isRlOm, formula, ledger, uomType);
		
		Assert.assertEquals(reportDesignReportSectionPage.readAlertMessage(), "Arithmetic Ledger Added Successfully");
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void duplicateALReport(String name, String dispName, String groupName, String type, String isCalc, String figConv, 
			String exchRate, String aggregate, String dashDispName, String isRlOm, String formula, String ledgerList, String uomType) {
	
		String ledger[] =ledgerList.split(",");
		
		reportDesignReportSectionPage.alReport(name, dispName, groupName, type, isCalc, figConv, 
				exchRate, aggregate, dashDispName, isRlOm, formula, ledger, uomType);
		
		Assert.assertEquals(reportDesignReportSectionPage.readAlertMessage(), "Arithmetic Ledger Name Already Exists");
	}
	
	@Test(priority=3, enabled=true, retryAnalyzer=IRetryAnalyser.class)
	public void updateALReport() throws InterruptedException {
		reportDesignReportSectionPage.updateReportSection();
		
		Thread.sleep(3000);
		
		Assert.assertEquals(reportDesignReportSectionPage.readAlertMessage(), "Arithmetic Ledger Updated Successfully");
	}
	
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
