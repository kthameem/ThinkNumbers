package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.pages.CompanyEntityDetailsPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MasterPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class CompanyEntityDetailsPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	CompanyEntityDetailsPage companyEntityDetailsPage;
	
	String sheetName = "EntityDetails";
	String logoPath = "C:/Users/ahmed/OneDrive/Desktop/newLogo.png";
	
	public CompanyEntityDetailsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		companyEntityDetailsPage = new CompanyEntityDetailsPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		companyEntityDetailsPage = masterPage.goToCompanyEntityDetailsPage();
	}
	
	@Test(priority=1, enabled=true)
	public void updateCompanyDetailsTest() throws InterruptedException {
		companyEntityDetailsPage.addCompanyDetails("Dunzo", "Dunzo", logoPath);
		Thread.sleep(1000);
		softAssert.assertEquals(companyEntityDetailsPage.readAlertMessage(), "Company Details Updated Successfully");
		softAssert.assertAll();
		companyEntityDetailsPage.clearAlertMessage();
	}
	
	@Test(priority=2, enabled=true)
	public void resetCompanyTest() {
		String tempCompanyName = companyEntityDetailsPage.getCompanyName();
		String tempCompanyCode = companyEntityDetailsPage.getCompanyCode();
		companyEntityDetailsPage.resetCompany();
		
		softAssert.assertEquals(companyEntityDetailsPage.getCompanyName(), tempCompanyName);
		softAssert.assertEquals(companyEntityDetailsPage.getCompanyCode(), tempCompanyCode);
		softAssert.assertAll("Company Details Reset Successfully");
	}

	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=3, dataProvider="getTestData", enabled=true)
	public void addEntityDataTest(String entityname, String acccurrency, String repcurrency, String remarks) {
		companyEntityDetailsPage.addEntity(entityname, acccurrency, repcurrency, remarks);
		Assert.assertEquals(companyEntityDetailsPage.readAlertMessage(), "Entity Added Successfully");
	}
	

	@Test(priority=4, enabled= true)
	public void viewEntityDataTest() throws InterruptedException {
		Thread.sleep(1000);
		String entityName = TestUtil.getCellData("EntityName", 1);
		Assert.assertEquals(companyEntityDetailsPage.viewEntity(entityName), entityName);
	}
	
	@Test(priority=5, enabled=true)
	public void searchEntityNameTest() {
		Assert.assertEquals(companyEntityDetailsPage.searchEntity(TestUtil.getCellData("EntityName", 1)), TestUtil.getCellData("EntityName", 1));
	}
	
	@Test(priority=6, enabled=true)
	public void editEntityDataTest() {
		companyEntityDetailsPage.editEntity(TestUtil.getCellData("EntityName", 1));
		Assert.assertEquals(companyEntityDetailsPage.readAlertMessage(), "Entity Updated Successfully");
		companyEntityDetailsPage.clearAlertMessage();
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}

}
