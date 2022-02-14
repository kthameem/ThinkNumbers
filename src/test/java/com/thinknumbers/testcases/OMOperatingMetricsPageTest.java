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
import com.thinknumbers.pages.OMOperatingMetricsPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class OMOperatingMetricsPageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	OMOperatingMetricsPage oMOperatingMetricsPage;
	
	String filePath = "C:/Users/ahmed/OneDrive/Desktop/OperatingMetrics/OperatingMetrics.xlsx";
	
	String sheetName = "OperatingMetrics";
	
	public OMOperatingMetricsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		oMOperatingMetricsPage = new OMOperatingMetricsPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		oMOperatingMetricsPage = masterPage.goToOMOperatingMetricsPage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addOperatingMetricsTest(String Name, String DisName, String DashDisName, String Category, String Type, String FigConversion, String ExchangeRate, String YTDCalculation) throws InterruptedException {
		oMOperatingMetricsPage.addOM();
		oMOperatingMetricsPage.enterOM(Name, DisName, DashDisName, Category, Type, FigConversion, ExchangeRate, YTDCalculation);
		oMOperatingMetricsPage.saveOM();
		Assert.assertEquals(oMOperatingMetricsPage.readAlertMessage(), "Operating Metrics Added Successfully");
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void duplicateOperatingMetricsTest(String Name, String DisName, String DashDisName, String Category, String Type, String FigConversion, String ExchangeRate, String YTDCalculation) throws InterruptedException {
		oMOperatingMetricsPage.addOM();
		oMOperatingMetricsPage.enterOM(Name, DisName, DashDisName, Category, Type, FigConversion, ExchangeRate, YTDCalculation);
		oMOperatingMetricsPage.saveOM();
		Assert.assertEquals(oMOperatingMetricsPage.readAlertMessage(), "Operating Metrics Already Exists");
	}
	
	@Test(priority=3, enabled=true)
	public void uploadOperatingMetricsTest() {
		oMOperatingMetricsPage.uploadOM(filePath);
		//oMOperatingMetricsPage.closeOM();
		Assert.assertEquals(oMOperatingMetricsPage.readAlertMessage(), "Operating Metrics Uploaded Successfully");
	}
	
	@Test(priority=4, enabled=true)
	public void editOperatingMetricsTest() {
		oMOperatingMetricsPage.editOM();
		Assert.assertEquals(oMOperatingMetricsPage.readAlertMessage(), "Operating Metrics Updated Successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		TestUtil.SwitchToMainWindow();
		driver.quit();
	}
}
