package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.DashboardDesignTablesPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignTablesPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	DashboardDesignTablesPage dashboardDesignTablesPage;
	
	String sheetName = "Tables";
	
	public DashboardDesignTablesPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		dashboardDesignTablesPage = masterPage.goToDashboardDesignTablesPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addTablesTest(String title, String entityName, String reportSection, String budget, 
			String variance, String range, String reportType, String ytd, String ytdFrom, String iYTDLabel) throws InterruptedException {
		
		dashboardDesignTablesPage.addTables(title, entityName, reportSection, budget, variance, range, reportType, ytd, ytdFrom, iYTDLabel);
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignTablesPage.readAlertMessage(), "Added Successfully");	
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void duplicateTablesTest(String title, String entityName, String reportSection, String budget, 
			String variance, String range, String reportType, String ytd, String ytdFrom, String iYTDLabel) throws InterruptedException {
		
		dashboardDesignTablesPage.addTables(title, entityName, reportSection, budget, variance, range, reportType, ytd, ytdFrom, iYTDLabel);
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignTablesPage.readAlertMessage(), "Title Already Exist");	
	}
	
	@Test(priority=3, enabled=true)
	public void editTablesTest() throws InterruptedException {
		dashboardDesignTablesPage.editTables();
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignTablesPage.readAlertMessage(), "Updated Successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
