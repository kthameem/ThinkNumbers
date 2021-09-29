package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.pages.CompanyEntityDetailsPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MastersPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class CompanyEntityDetailsPageTest extends TestBase{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MastersPage mastersPage;
	CompanyEntityDetailsPage companyEntityDetailsPage;
	
	String sheetName = "ComGroupEntityDetails";
	String logoPath = "C:/Users/ahmed/OneDrive/Desktop/newLogo.png";
	
	public CompanyEntityDetailsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		mastersPage = new MastersPage();
		companyEntityDetailsPage = new CompanyEntityDetailsPage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		mastersPage = homePage.goToMasters();
		homePage.clearAlertMessage();
		companyEntityDetailsPage = mastersPage.goToCompanyPage();
	}
	
	@Test(priority=1)
	public void updateCompanyDetails() throws InterruptedException {
		companyEntityDetailsPage.addCompanyDetails("Dunzo", "Dunzo", logoPath);
		Thread.sleep(1000);
		softAssert.assertEquals(companyEntityDetailsPage.readAlertMessage(), "Company Details Updated Successfully");
		softAssert.assertAll();
		companyEntityDetailsPage.clearAlertMessage();
	}
	
	@Test(priority=2)
	public void resetCompanyTest() {
		String tempCompanyName = companyEntityDetailsPage.getCompanyName();
		String tempCompanyCode = companyEntityDetailsPage.getCompanyCode();
		companyEntityDetailsPage.resetCompany();
		
		softAssert.assertEquals(companyEntityDetailsPage.getCompanyName(), tempCompanyName);
		softAssert.assertEquals(companyEntityDetailsPage.getCompanyCode(), tempCompanyCode);
		softAssert.assertAll("Company Details Reset Successfully");
	}
	
	@Test(priority=3)
	public void searchEntityName() {
		Assert.assertEquals(companyEntityDetailsPage.searchEntity("Dunzo"), "Dunzo");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getTestData")
	public void addEntityData(String entityname, String acccurrency, String repcurrency, String remarks) throws InterruptedException {
		companyEntityDetailsPage.addEntity(entityname, acccurrency, repcurrency, remarks);
		Thread.sleep(500);
		
		softAssert.assertEquals(companyEntityDetailsPage.readAlertMessage(), "Entity Added Successfully");
		if (companyEntityDetailsPage.readAlertMessage()!= "Entity Added Successfully") {
			companyEntityDetailsPage.closeEntity();
		} 
		softAssert.assertAll();
		companyEntityDetailsPage.clearAlertMessage();
	}
	

	@Test(priority=5)
	public void viewEntityData() {
		Assert.assertEquals(companyEntityDetailsPage.viewEntity("Dunzo"), "Dunzo");
	}
	
	@Test(priority=6)
	public void editEntityData() {
		companyEntityDetailsPage.editEntity("Dunzo 1");
		softAssert.assertEquals(companyEntityDetailsPage.readAlertMessage(), "Entity Updated Successfully");
		softAssert.assertAll();
		companyEntityDetailsPage.clearAlertMessage();
	}
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}

}
