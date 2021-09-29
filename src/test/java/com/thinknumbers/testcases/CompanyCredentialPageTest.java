package com.thinknumbers.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.pages.CompanyCredentialPage;
import com.thinknumbers.pages.CompanyEntityDetailsPage;
import com.thinknumbers.pages.HomePage;
import com.thinknumbers.pages.LoginPage;
import com.thinknumbers.pages.MastersPage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class CompanyCredentialPageTest extends TestBase {
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MastersPage mastersPage;
	CompanyEntityDetailsPage companyEntityDetailsPage;
	CompanyCredentialPage companyCredentialPage;
	
	String sheetName = "ComCredentials";
	
	public CompanyCredentialPageTest() {
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
		companyCredentialPage = companyEntityDetailsPage.goToCredentialPage();
	}
	
	@Test(priority=1)
	public void searchUserName() {
		Assert.assertEquals(companyCredentialPage.searchUser("admin"), "admin");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=2, dataProvider="getTestData")
	public void addUsersData(String loginname, String usertype, String logingpwd) throws InterruptedException {
		companyCredentialPage.addUser(loginname, usertype, logingpwd);
		Thread.sleep(500);
		softAssert.assertEquals(companyCredentialPage.readAlertMessage(), "User Added Successfully");
		if(companyCredentialPage.readAlertMessage()!= "User Added Successfully") {
			companyCredentialPage.closeUser();
		} else {
			softAssert.assertEquals(companyCredentialPage.searchUser(loginname), loginname);
		}
		companyCredentialPage.clearAlertMessage();
		softAssert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		thinkiamHomePage.logOut();
		driver.quit();
	}

}
