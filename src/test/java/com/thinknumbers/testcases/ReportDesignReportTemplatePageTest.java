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
import com.thinknumbers.pages.ReportDesignReportTemplatePage;
import com.thinknumbers.pages.ThinkiamHomePage;
import com.thinknumbers.util.TestUtil;

public class ReportDesignReportTemplatePageTest extends BaseTest{

	LoginPage loginPage;
	ThinkiamHomePage thinkiamHomePage;
	HomePage homePage;
	MasterPage masterPage;
	ReportDesignReportTemplatePage reportDesignReportTemplatePage;
	
	String sheetName = "ReportTemplate";
	
	public ReportDesignReportTemplatePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		softAssert = new SoftAssert();
		loginPage = new LoginPage();
		homePage = new HomePage();
		masterPage = new MasterPage();
		reportDesignReportTemplatePage = new ReportDesignReportTemplatePage();
		thinkiamHomePage = loginPage.login(prop.getProperty("client"), prop.getProperty("username"), prop.getProperty("password"));
		homePage = thinkiamHomePage.goToThinkNumber();	
		TestUtil.SwitchToChildWindow();
		TestUtil.WaitTill_PageLoads(10);
		
		masterPage = homePage.goToMaster();
		homePage.clearAlertMessage();
		reportDesignReportTemplatePage = masterPage.goToReportDesignReportTemplatePage();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getTestData", enabled=true)
	public void addReportTemplateTest(String templateName, String entityName, String budget, 
			String variance, String reportGroup, String ledgerList) throws InterruptedException {
		
		reportDesignReportTemplatePage.addReportTemplate(templateName, entityName, budget, variance, reportGroup, ledgerList);
		Thread.sleep(1000);
		Assert.assertEquals(reportDesignReportTemplatePage.readAlertMessage(), "Template Created Successfully");
	}
	
	@Test(priority=2, dataProvider="getTestData", enabled=true)
	public void duplicateReportTemplateTest(String templateName, String entityName, String budget, String variance, String reportGroup, String ledgerList) throws InterruptedException {
		reportDesignReportTemplatePage.addReportTemplate(templateName, entityName, budget, variance, reportGroup, ledgerList);
		reportDesignReportTemplatePage.goBack();
		Thread.sleep(1000);
		Assert.assertEquals(reportDesignReportTemplatePage.readAlertMessage(), "Template Already Exists.");
	}
	
	@Test(priority=3, enabled=true)
	public void updateReportTemplateTest() throws InterruptedException {
		reportDesignReportTemplatePage.updateTemplate();
		Thread.sleep(1000);
		Assert.assertEquals(reportDesignReportTemplatePage.readAlertMessage(), "Template Updated Successfully");
	}
	
	@AfterMethod
	public void tearDown() {
		//homePage.logoutThinkNumbers();
		TestUtil.SwitchToMainWindow();
		//thinkiamHomePage.logOut();
		driver.quit();
	}
}
