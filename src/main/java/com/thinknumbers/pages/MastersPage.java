package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.TestBase;

public class MastersPage extends TestBase {
	
	@FindBy(xpath="//div[@class='page-title' and contains(text(),'Masters')]")
	WebElement pageTitle;
	
	@FindBy(xpath="//a[@href='#/thinknumbers/masters/company' and text()='Company']")
	WebElement companyMenu;
	
	@FindBy(xpath="//a[text()='Sub Master']")
	WebElement subMasterMenu;
	
	@FindBy(xpath="//a[text()='Analysis group']")
	WebElement analysisGroupMenu;
	
	@FindBy(xpath="//a[@href='#/thinknumbers/masters/fas-groups' and text()='Accounting group']")
	WebElement accountingGroupMenu;
	
	@FindBy(xpath="//a[@href='#/thinknumbers/masters/day-books' and text()='Day Book']")
	WebElement dayBookMenu;
	
	@FindBy(xpath="//a[@href='#/thinknumbers/masters/trail-balances' and text()='Trial Balance']")
	WebElement trailBalanceMenu;
	
	@FindBy(xpath="//a[text()='Fas Trial Balance']")
	WebElement fasTrailBalanceMenu;
	
	@FindBy(xpath="//a[text()='Reporting Ledger']")
	WebElement reportingLedgerMenu;
	
	@FindBy(xpath="//a[text()='Getting Started']")
	WebElement gettingStartedMenu;
	
	public MastersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle() {
		return pageTitle.getText();
	}
	
	public boolean verifyCompanyMenu() {
		return companyMenu.isDisplayed();
	}
	
	public boolean verifySubMasterMenu() {
		return subMasterMenu.isDisplayed();
	}
	
	public boolean verifyAnalysisGroupMenu() {
		return analysisGroupMenu.isDisplayed();
	}
	
	public boolean verifyAccountingGroupMenu() {
		return accountingGroupMenu.isDisplayed();
	}
	
	public boolean verifyDayBookMenu() {
		return dayBookMenu.isDisplayed();
	}
	
	public boolean verifyTrailBalancemenu() {
		return trailBalanceMenu.isDisplayed();
	}
	
	public boolean verifyFasTrailBalanceMenu() {
		return fasTrailBalanceMenu.isDisplayed();
	}
	
	public boolean verifyReportingLedgerMenu() {
		return reportingLedgerMenu.isDisplayed();
	}
	
	public boolean verifyGettingStartedMenu() {
		return gettingStartedMenu.isDisplayed();
	}

	public CompanyEntityDetailsPage goToCompanyPage() {
		companyMenu.click();
		return new CompanyEntityDetailsPage();
	}
	
	public SubMasterCurrencyPage goToSubMasterPage() {
		subMasterMenu.click();
		return new SubMasterCurrencyPage();
	}
	
	public AnalysisGroupAddPage goToAnalysisGroupPage() {
		analysisGroupMenu.click();
		return new AnalysisGroupAddPage();
	}
	
	public AccGroupFasGroupListPage goToAccountingGroupPage() {
		accountingGroupMenu.click();
		return new AccGroupFasGroupListPage();
	}
	
	public DayBookUploadPage goToDayBookPage() {
		dayBookMenu.click();
		return new DayBookUploadPage();
	}
	
	public TrailBalanceUploadPage goToTrailBalancePage() {
		trailBalanceMenu.click();
		return new TrailBalanceUploadPage();
	}
	
	public FasTrailBalanceUploadPage goToFasTrailBalancePage() {
		fasTrailBalanceMenu.click();
		return new FasTrailBalanceUploadPage();
	}
	
	public ReportingLedgerAddPage goToReportingLedgerPage() {
		reportingLedgerMenu.click();
		return new ReportingLedgerAddPage();
	}

	
}
