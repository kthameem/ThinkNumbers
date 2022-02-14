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
import com.thinknumbers.pages.UserDetailsUserPage;
import com.thinknumbers.util.TestUtil;

public class UserDetailsUserPageTest extends BaseTest{
	
	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	CompanyEntityDetailsPage companyEntityDetailsPage;
	UserDetailsUserPage userDetailsUserPage;
	
	String sheetName = "ComCredentials";
	
	public UserDetailsUserPageTest() {
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
		userDetailsUserPage = masterPage.goToUserDetailsUserPage();
	}
	
	@Test(priority=1)
	public void searchUserNameTest() {
		Assert.assertEquals(userDetailsUserPage.searchUser("admin"), "admin");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=2, dataProvider="getTestData")
	public void addUsersDataTest(String loginname, String usertype, String logingpwd) throws InterruptedException {
		userDetailsUserPage.addUser(loginname, usertype, logingpwd);
		Thread.sleep(500);
		softAssert.assertEquals(userDetailsUserPage.readAlertMessage(), "User Added Successfully");
		if(userDetailsUserPage.readAlertMessage()!= "User Added Successfully") {
			userDetailsUserPage.closeUser();
		} else {
			softAssert.assertEquals(userDetailsUserPage.searchUser(loginname), loginname);
		}
		userDetailsUserPage.clearAlertMessage();
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
