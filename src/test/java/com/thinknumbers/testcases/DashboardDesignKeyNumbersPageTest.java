package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.DashboardDesignKeyNumbersPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignKeyNumbersPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	DashboardDesignKeyNumbersPage dashboardDesignKeyNumbersPage;
	
	String sheetName = "KeyNumbers";
	
	public DashboardDesignKeyNumbersPageTest() {
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
		dashboardDesignKeyNumbersPage = masterPage.goToDashboardDesignKeyNumbersPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addKeyNumberTest(String type, String entityName, String keyNumbers, String dispName, 
			String range, String compVsPrev, String compRange, String compVsBudget) throws InterruptedException {
		
		dashboardDesignKeyNumbersPage.addKeyNumbers(type, entityName, keyNumbers, dispName, range, compVsPrev, compRange, compVsBudget);
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignKeyNumbersPage.readAlertMessage(), "Added Successfully");
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void duplicateKeyNumberTest(String type, String entityName, String keyNumbers, String dispName, 
			String range, String compVsPrev, String compRange, String compVsBudget) throws InterruptedException {
		
		dashboardDesignKeyNumbersPage.addKeyNumbers(type, entityName, keyNumbers, dispName, range, compVsPrev, compRange, compVsBudget);
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignKeyNumbersPage.readAlertMessage(), "Display Name Already Exist");
	}
	
	@Test(priority=3, enabled=true)
	public void editKeyNumbersTest() throws InterruptedException {
		dashboardDesignKeyNumbersPage.editKeyNumbers();
		Thread.sleep(1000);
		Assert.assertEquals(dashboardDesignKeyNumbersPage.readAlertMessage(), "Updated Successfully");
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}
}
