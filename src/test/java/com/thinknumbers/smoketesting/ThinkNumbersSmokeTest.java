package com.thinknumbers.smoketesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thinknumbers.base.TestBase;
import com.thinknumbers.util.TestUtil;

public class ThinkNumbersSmokeTest extends TestBase{
	WebDriverWait wait;
	SoftAssert softAssert; 

	@BeforeMethod
	public void setUp() {
		initialization();
		
		driver.findElement(By.name("clientname")).sendKeys(prop.getProperty("client"));
		driver.findElement(By.name("username")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'ThinkNumbers')]"))); 
	
		driver.findElement(By.xpath("//a[contains(text(),'ThinkNumbers')]")).click();
		
		TestUtil.SwitchToChildWindow();
		
		TestUtil.WaitTill_PageLoads(10);
		
		driver.findElement(By.xpath("//img[@title='Masters']")).click();

		TestUtil.WaitTill_PageLoads(10);
		
		softAssert = new SoftAssert();
		
	}

	@Test(priority=1)
	public void companyTest() throws InterruptedException {
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#/thinknumbers/masters/company' and text()='Company']"))); 
		
		WebElement companyLink = driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/company' and text()='Company']"));
		WebElement groupEntityDetails = driver.findElement(By.xpath("//a[text()='Group Entity Details']"));
		WebElement credential = driver.findElement(By.xpath("//a[text()='Credential']"));
		
		Assert.assertEquals(true, companyLink.isDisplayed(), "Company Link not found in Masters");
		softAssert.assertEquals(true, groupEntityDetails.isDisplayed(), "Group Entity Details Link not found in Company Tab");
		softAssert.assertEquals(true, credential.isDisplayed(), "Credential Link not found in Company Tab");
		
		credential.click();
		
		softAssert.assertAll();
	}

	@Test(priority=2)
	public void subMasterTest() throws InterruptedException {
	
		
		//Thread.sleep(10000);
		WebElement subMaster = driver.findElement(By.xpath("//a[text()='Sub Master']"));
		
		Assert.assertEquals(true, subMaster.isDisplayed());
		
		subMaster.click();
		
		driver.findElement(By.xpath("//a[text()='Currency Type']")).click();
		driver.findElement(By.xpath("//a[text()='Cost Center']")).click();
		driver.findElement(By.xpath("//a[text()='Cost Category']")).click();
		driver.findElement(By.xpath("//a[text()='Employee']")).click();
		driver.findElement(By.xpath("//a[text()='Voucher Type']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Ledger List']"))); 
		
		driver.findElement(By.xpath("//a[text()='Ledger List']")).click();
	}
	
	@Test(priority=3)
	public void analysisGroupTest() throws InterruptedException {
		
		//Thread.sleep(10000);
		WebElement analysisGroup = driver.findElement(By.xpath("//a[text()='Analysis group']"));
		
		Assert.assertEquals(true, analysisGroup.isDisplayed());
		
		analysisGroup.click();
		
		driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/analysis-groups/analysis-group' and text()='Analysis Group']"));
		driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/analysis-groups/analysis-group-details' and text()='Analysis Group Details']"));
	}

	@Test(priority=4)
	public void accountingGroupTest() throws InterruptedException {
		
		//Thread.sleep(10000);
		WebElement accountingGroup = driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/fas-groups' and text()='Accounting group']"));
		
		Assert.assertEquals(true, accountingGroup.isDisplayed());
		
		accountingGroup.click();
		
		driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/fas-groups/fasgroup-list' and text()='FasGroup List']")).click();	
		driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/fas-groups/map-with-ledger' and text()='Map With Ledger']")).click();
	}

	@Test(priority=5)
	public void dayBookTest() throws InterruptedException {
		
		//Thread.sleep(10000);
		WebElement dayBook = driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/day-books' and text()='Day Book']"));
		dayBook.click();
		
		//WebElement dayBookUpload = driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/day-books/day-book-add' and text()='DayBook Upload']"));
		WebElement dayBookUpload = driver.findElement(By.xpath("//a[contains(text(), 'DayBook Upload')]"));
		WebElement dayBookList = driver.findElement(By.xpath("//a[text()='DayBook List']"));
		WebElement UnfreezeList = driver.findElement(By.xpath("//a[text()='Unfreeze List']"));
		WebElement dayBookRLMap = driver.findElement(By.xpath("//a[text()='Daybook RLMap']"));
		WebElement dayBookRLMapLists = driver.findElement(By.xpath("//a[text()='Daybook RLMap Lists']"));
		
		softAssert.assertEquals(true, dayBook.isDisplayed());
		softAssert.assertEquals(true, dayBookUpload.isDisplayed());
		softAssert.assertEquals(true, ExpectedConditions.elementToBeClickable(dayBookList));
		softAssert.assertEquals(true, ExpectedConditions.elementToBeClickable(UnfreezeList));
		softAssert.assertEquals(true, ExpectedConditions.elementToBeClickable(dayBookRLMap));
		softAssert.assertEquals(true, ExpectedConditions.elementToBeClickable(dayBookRLMapLists));
		

		dayBookUpload.click();
		dayBookList.click();
		UnfreezeList.click();
		dayBookRLMap.click();
		dayBookRLMapLists.click();
	
		softAssert.assertAll();
	}

	@Test(priority=6)
	public void trailBalanceTest() throws InterruptedException {
	
		//Thread.sleep(10000);
		WebElement trailBalance = driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/trail-balances' and text()='Trial Balance']"));
		
		Assert.assertEquals(true, trailBalance.isDisplayed());
		
		trailBalance.click();
		
		driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/trail-balances/trial-balance' and text()='Trial Balance']")).click();
		driver.findElement(By.xpath("//a[text()='Trial Computation']")).click();
		driver.findElement(By.xpath("//a[text()='Trial Balance List']")).click();
	
	}

	@Test(priority=7)
	public void fasTrailBalanceTest() throws InterruptedException {
	
		//Thread.sleep(10000);
		WebElement fasTrailBalance = driver.findElement(By.xpath("//a[text()='Fas Trial Balance']"));
		Assert.assertEquals(true, fasTrailBalance.isDisplayed());
		fasTrailBalance.click();
		
		driver.findElement(By.xpath("//a[text()='FasTB Upload']")).click();
		driver.findElement(By.xpath("//a[text()='FasTB List']")).click();
		driver.findElement(By.xpath("//a[text()='FasTB Validate']")).click();
	}

	@Test(priority=8)
	public void reportingLedgerTest() throws InterruptedException {
	
		//Thread.sleep(10000);
		WebElement reportingLedger = driver.findElement(By.xpath("//a[text()='Reporting Ledger']"));
		Assert.assertEquals(true, reportingLedger.isDisplayed());
		reportingLedger.click();
		
		driver.findElement(By.xpath("//a[text()='Reporting Ledger Lists']")).click();
		driver.findElement(By.xpath("//a[text()='Report Calculation']")).click();
		driver.findElement(By.xpath("//a[text()='Report Calculated']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Reporting ledgers to Accounting Ledgers mapping')]")).click();
	}

	//@Test(priority=9)
	public void gettingStartedTest() throws InterruptedException {
		
		//Thread.sleep(10000);
		WebElement gettingStarted = driver.findElement(By.xpath("//a[text()='Getting Started']"));
		Assert.assertEquals(true, gettingStarted.isDisplayed());
		gettingStarted.click();
		
		driver.findElement(By.xpath("//a[@href='#/thinknumbers/masters/getting-starteds/getting-started' and text()='Getting Started']")).click();
	}

	@AfterMethod
	public void logOutChildWindow() {
		//driver.findElement(By.xpath("//img[@title='Logout']")).click();
		driver.close();
		TestUtil.SwitchToMainWindow();
		driver.quit();
	}


	
}
