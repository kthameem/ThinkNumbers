package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.DashboardDesignDashboardCreationPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignDashboardCreationPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	DashboardDesignDashboardCreationPage dashboardDesignDashboardCreationPage;
	
	String sheetName = "Dashboard";
	
	public DashboardDesignDashboardCreationPageTest() {
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
		dashboardDesignDashboardCreationPage = masterPage.goToDashboardDesignDashboardCreationPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, enabled=true, dataProvider="getTestData")
	public void createDashboardTest(String title, String noRows, String row1, String row2, String row3, 
			String rowCount1, String rowCount2, String rowCount3, 
			String keyNumbersNames, String chartsNames, String tableNames) {
		
		dashboardDesignDashboardCreationPage.createDashboard(title, noRows, row1, row2, row3, rowCount1, rowCount2, rowCount3, keyNumbersNames, chartsNames, tableNames);
		Assert.assertEquals(dashboardDesignDashboardCreationPage.readAlertMessage(), "Added Successfully");
	}

	@Test(priority=2, enabled=true, dataProvider="getTestData")
	public void duplicateDashboardTest(String title, String noRows, String row1, String row2, String row3, 
			String rowCount1, String rowCount2, String rowCount3, 
			String keyNumbersNames, String chartsNames, String tableNames) {
		
		dashboardDesignDashboardCreationPage.createDashboard(title, noRows, row1, row2, row3, rowCount1, rowCount2, rowCount3, keyNumbersNames, chartsNames, tableNames);
		Assert.assertEquals(dashboardDesignDashboardCreationPage.readAlertMessage(), "Dashboard Title Already Exist");
	}
	
	@Test(priority=3, enabled=true)
	public void updateDashboardTest() {
		dashboardDesignDashboardCreationPage.updateDashboard();
		Assert.assertEquals(dashboardDesignDashboardCreationPage.readAlertMessage(), "Updated Successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
	
}
