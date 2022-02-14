package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.DashboardDesignChartsPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignChartsPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	DashboardDesignChartsPage dashboardDesignChartsPage;
	
	String sheetName = "Charts";
	
	public DashboardDesignChartsPageTest() {
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
		dashboardDesignChartsPage = masterPage.goToDashboardDesignChartsPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addChartsTest(String chartType, String chartTitle, String entityName, String xAxis, 
			String period, String reportTemplate, String reportLedger, String budget) throws InterruptedException {
		
		dashboardDesignChartsPage.addCharts(chartType, chartTitle, entityName, xAxis, period, reportTemplate, reportLedger, budget);
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignChartsPage.readAlertMessage(), "Added Successfully");	
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void duplicateChartsTest(String chartType, String chartTitle, String entityName, String xAxis, 
			String period, String reportTemplate, String reportLedger, String budget) throws InterruptedException {
		
		dashboardDesignChartsPage.addCharts(chartType, chartTitle, entityName, xAxis, period, reportTemplate, reportLedger, budget);
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignChartsPage.readAlertMessage(), "Title Already Exist");	
	}
	
	@Test(priority=3, enabled=true)
	public void editChartsTest() throws InterruptedException {
		dashboardDesignChartsPage.editCharts();
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignChartsPage.readAlertMessage(), "Updated Successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
}
