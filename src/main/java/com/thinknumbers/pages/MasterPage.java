package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;

public class MasterPage extends BaseTest{
	
	@FindBy(xpath="//div[@class='page-title' and contains(text(),'Master')]")
	WebElement pageTitle;
	
	@FindBy(xpath="//a[text()='Getting Started']")
	WebElement gettingStartedMenu;
	
	@FindBy(xpath="//a[@href='#/thinknumbers/master/company' and text()='Company']")
	WebElement companyMenu;
	
	@FindBy(xpath="//a[text()='FAS Masters']")
	WebElement fasMastersMenu;
	
	@FindBy(xpath="//a[text()='Operating metrics']")
	WebElement operatingMetricsMenu;
	
	@FindBy(xpath="//a[text()='Uploads']")
	WebElement uploadsMenu;
	
	@FindBy(xpath="//a[text()='Upload list']")
	WebElement uploadListMenu;
	
	@FindBy(xpath="//a[text()='Reporting ledgers']")
	WebElement reportingLedgersMenu;
	
	@FindBy(xpath="//a[text()='Report Design']")
	WebElement reportDesignMenu;
	
	@FindBy(xpath="//a[text()='Dashboard Design']")
	WebElement dashboardDesignMenu;
	
	@FindBy(xpath="//a[text()='Report run']")
	WebElement reportRunMenu;
	
	@FindBy(xpath="//a[text()='User Details']")
	WebElement userDetailsMenu;
	
	@FindBy(xpath="//a[text()='Group Entity Details']")
	WebElement companyGroupEntityDetails;

	@FindBy(xpath="//a[text()='Exchange Rate']")
	WebElement companyExchangeRate;
	
	@FindBy(xpath="//a[text()='Ledger List']")
	WebElement fasMastersLedgerList;
	
	@FindBy(xpath="//a[text()='Map With Ledger']")
	WebElement fasMastersMapWithLedger;
	
	@FindBy(xpath="//a[text()='Cost Center']")
	WebElement fasMastersCostCenter;
	
	@FindBy(xpath="//a[text()='Cost Category']")
	WebElement fasMastersCostCategory;
	
	@FindBy(xpath="//a[text()='Analysis Group']")
	WebElement fasMastersAnalysisGroup;
	
	@FindBy(xpath="//a[text()='Analysis Group Details']")
	WebElement fasMastersAnalysisGroupDetails;
	
	@FindBy(xpath="//a[text()='Employee']")
	WebElement fasMastersEmployee;
	
	@FindBy(xpath="//a[text()='Voucher Type']")
	WebElement fasMastersVoucherType;
	
	@FindBy(xpath="//a[text()='Ledger Type']//following-sibling::a")
	WebElement oMOperatingMetrics;
	
	@FindBy(xpath="//a[text()='Operating metrics Group']")
	WebElement oMOperatingMetricsGroup;
	
	@FindBy(xpath="//a[text()='DayBook Upload']")
	WebElement uploadsDBUpload;
	
	@FindBy(xpath="//a[text()='Trial Computation']")
	WebElement uploadsTrialComputation;

	@FindBy(xpath="//a[text()='FasTB Upload']")
	WebElement uploadsFasTBUpload;
	
	@FindBy(xpath="//a[text()='FasTB Validate']")
	WebElement uploadsFasTBValidate;
	
	@FindBy(xpath="//a[text()='Operating Metrics Data']")
	WebElement uploadsOperatingMetricsData;
	
	@FindBy(xpath="//a[text()='Budget Data']")
	WebElement uploadsBudgetData;
	
	@FindBy(xpath="//a[text()='DayBook List']")
	WebElement uploadListDBList;
	
	@FindBy(xpath="//a[text()='Unfreeze List']")
	WebElement uploadListUnfreezeList;
	
	@FindBy(xpath="//a[text()='Daybook RLMap Lists']")
	WebElement uploadListDBRLMapLists;
	
	@FindBy(xpath="//a[text()='FasTB List']")
	WebElement uploadListFasTBList;
	
	@FindBy(xpath="//a[text()='Reporting ledgers to Accounting Ledgers mapping']")
	WebElement rLReportingLedgersToAccLedgersMapping;
	
	@FindBy(xpath="//a[text()='report template']")
	WebElement reportDesignReportTemplate;
	
	@FindBy(xpath="//a[text()='Charts']")
	WebElement dashboardDesignCharts;
	
	@FindBy(xpath="//a[text()='Tables']")
	WebElement dashboardDesignTables;
	
	@FindBy(xpath="//a[text()='dashboard creation']")
	WebElement dashboardDesignDashboardCreation;
	
	@FindBy(xpath="//a[text()='Color setup']")
	WebElement dashboardDesignColorSetup;
	
	@FindBy(xpath="//a[text()='Report Calculation']")
	WebElement reportRunReportCalculation;

	@FindBy(xpath="//a[text()='Report Calculated']")
	WebElement reportRunReportCalculated;
	
	@FindBy(xpath="//a[text()='AL Report Calculation ']")
	WebElement reportRunALReportCalculation;
	
	@FindBy(xpath="//a[text()='publish report']")
	WebElement reportRunPublishReport;
	
	@FindBy(xpath="//a[text()='User']")
	WebElement userDetailsUser;
	
	@FindBy(xpath="//a[text()='User Group']")
	WebElement userDetailsUserGroup;
	
	@FindBy(xpath="//a[text()='Voucher Type']//parent::div//parent::div//parent::div//following-sibling::div//div")
	WebElement scrollRight;
	
	public MasterPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle() {
		return pageTitle.getText();
	}
	
	public boolean verifyGettingStartedMenu() {
		return gettingStartedMenu.isDisplayed();
	}
	
	public boolean verifyCompanyMenu() {
		return companyMenu.isDisplayed();
	}
	
	public boolean verifyFasMastersMenu() {
		return fasMastersMenu.isDisplayed();
	}
	
	public boolean verifyOperatingMetricsMenu() {
		return operatingMetricsMenu.isDisplayed();
	}
	
	public boolean verifyUploadsMenu() {
		return uploadsMenu.isDisplayed();
	}
	
	public boolean verifyUploadListMenu() {
		return uploadListMenu.isDisplayed();
	}
	
	public boolean verifyReportingLedgersMenu() {
		return reportingLedgersMenu.isDisplayed();
	}
	
	public boolean verifyReportDesignMenu() {
		return reportDesignMenu.isDisplayed();
	}
	
	public boolean verifyDashboardDesignMenu() {
		return dashboardDesignMenu.isDisplayed();
	}
	
	public boolean verifyReportRunMenu() {
		return reportRunMenu.isDisplayed();
	}
	
	public boolean verifyUserDetailsMenu() {
		return userDetailsMenu.isDisplayed();
	}
	
	public boolean verifyCompanyGroupEntityDetail() {
		return companyGroupEntityDetails.isDisplayed();
	}
	
	public boolean verifyCompanyExchangeRate() {
		return companyExchangeRate.isDisplayed();
	}
	
	public boolean verifyFasMastersLedgerList() {
		return fasMastersLedgerList.isDisplayed();
	}

	public boolean verifyFasMastersMapWithLedger() {
		return fasMastersMapWithLedger.isDisplayed();
	}

	public boolean verifyFasMastersCostCenter() {
		return fasMastersCostCenter.isDisplayed();
	}

	public boolean verifyFasMastersCostCategory() {
		return fasMastersCostCategory.isDisplayed();
	}

	public boolean verifyFasMastersAnalysisGroup() {
		return fasMastersAnalysisGroup.isDisplayed();
	}

	public boolean verifyFasMastersAnalysisGroupDetails() {
		return fasMastersAnalysisGroupDetails.isDisplayed();
	}

	public boolean verifyFasMastersEmployee() {
		return fasMastersEmployee.isDisplayed();
	}

	public boolean verifyFasMastersVoucherType() {
		return fasMastersVoucherType.isDisplayed();
	}

	public boolean verifyOMOperatingMetrics() {
		return oMOperatingMetrics.isDisplayed();
	}

	public boolean verifyUploadsDBUpload() {
		return uploadsDBUpload.isDisplayed();
	}

	public boolean verifyUploadsTrialComputation() {
		return uploadsTrialComputation.isDisplayed();
	}

	public boolean verifyUploadsFasTBUpload() {
		return uploadsFasTBUpload.isDisplayed();
	}

	public boolean verifyUploadsFasTBValidate() {
		return uploadsFasTBValidate.isDisplayed();
	}

	public boolean verifyUploadsOperatingMetricsData() {
		return uploadsOperatingMetricsData.isDisplayed();
	}

	public boolean verifyUploadsBudgetData() {
		return uploadsBudgetData.isDisplayed();
	}

	public boolean verifyUploadListDBList() {
		return uploadListDBList.isDisplayed();
	}

	public boolean verifyUploadListUnfreezeList() {
		return uploadListUnfreezeList.isDisplayed();
	}
	
	public boolean verifyUploadListDBRLMapLists() {
		return uploadListDBRLMapLists.isDisplayed();
	}

	public boolean verifyUploadListFasTBList() {
		return uploadListFasTBList.isDisplayed();
	}

	public boolean verifyRLReportingLedgersToAccLedgersMapping() {
		return rLReportingLedgersToAccLedgersMapping.isDisplayed();
	}

	public boolean verifyReportDesignReportTemplate() {
		return reportDesignReportTemplate.isDisplayed();
	}

	public boolean verifyDashboardDesignCharts() {
		return dashboardDesignCharts.isDisplayed();
	}
	
	public boolean verifyDashboardDesignTables() {
		return dashboardDesignTables.isDisplayed();
	}

	public boolean verifyDashboardDesignDashboardCreation() {
		return dashboardDesignDashboardCreation.isDisplayed();
	}

	public boolean verifyDashboardDesignColorSetup() {
		return dashboardDesignColorSetup.isDisplayed();
	}

	public boolean verifyReportRunReportCalculation() {
		return reportRunReportCalculation.isDisplayed();
	}

	public boolean verifyReportRunReportCalculated() {
		return reportRunReportCalculated.isDisplayed();
	}

	public boolean verifyReportRunALReportCalculation() {
		return reportRunALReportCalculation.isDisplayed();
	}

	public boolean verifyReportRunPublishReport() {
		return reportRunPublishReport.isDisplayed();
	}

	public boolean verifyUserDetailsUser() {
		return userDetailsUser.isDisplayed();
	}

	public boolean verifyUserDetailsUserGroup() {
		return userDetailsUserGroup.isDisplayed();
	}
	
	public CompanyCurrencyPage goToCompanyCurrencyPage() {
		companyMenu.click();
		return new CompanyCurrencyPage();
	}
	
	public FasMastersFasGroupListPage goToFasMastersFasGroupListPage() {
		fasMastersMenu.click();
		return new FasMastersFasGroupListPage();
	}
	
	public OMLedgerTypePage goToOMLedgerTypePage() {
		operatingMetricsMenu.click();
		return new OMLedgerTypePage();
	}
	
	public UploadsTBPage goToUploadsTBPage() {
		uploadsMenu.click();
		return new UploadsTBPage();
	}
	
	public UploadListTBListPage goToUploadListTBListPage() {
		uploadListMenu.click();
		return new UploadListTBListPage();
	}
	
	public RLReportingLedgerListsPage goToRLReportingLedgerListsPage() {
		reportingLedgersMenu.click();
		return new RLReportingLedgerListsPage();
	}
	
	public ReportDesignReportSectionPage goToReportDesignReportSectionPage() {
		reportDesignMenu.click();
		return new ReportDesignReportSectionPage();
	}
	
	public DashboardDesignKeyNumbersPage goToDashboardDesignKeyNumbersPage() {
		dashboardDesignMenu.click();
		return new DashboardDesignKeyNumbersPage();
	}
	
	public ReportRunDBRLMapPage goToReportRunDBRLMapPage() {
		reportRunMenu.click();
		return new ReportRunDBRLMapPage();
	}
	
	public UserDetailsUserRolePage goToUserDetailsUserRolePage() {
		userDetailsMenu.click();
		return new UserDetailsUserRolePage();
	}
	
	public GettingStartedPage goToGettingStartedPage() {
		gettingStartedMenu.click();
		return new GettingStartedPage();
	}
	
	public CompanyEntityDetailsPage goToCompanyEntityDetailsPage() {
		goToCompanyCurrencyPage();
		companyGroupEntityDetails.click();
		return new CompanyEntityDetailsPage();
	}
	
	public CompanyExchangeRatePage goToCompanyExchangeRatePage() {
		goToCompanyCurrencyPage();
		companyExchangeRate.click();
		return new CompanyExchangeRatePage();
	}
	
	public FasMastersLedgerListPage goToFasMastersLedgerListPage() {
		goToFasMastersFasGroupListPage();
		fasMastersLedgerList.click();
		return new FasMastersLedgerListPage();
	}

	public FasMastersMapWithLedgerPage goToFasMastersMapWithLedgerPage() {
		goToFasMastersFasGroupListPage();
		fasMastersMapWithLedger.click();
		return new FasMastersMapWithLedgerPage();
	}

	public FasMastersCostCenterPage goToFasMastersCostCenterPage() {
		goToFasMastersFasGroupListPage();
		fasMastersCostCenter.click();
		return new FasMastersCostCenterPage();
	}

	public FasMastersCostCategoryPage goToFasMastersCostCategoryPage() {
		goToFasMastersFasGroupListPage();
		fasMastersCostCategory.click();
		return new FasMastersCostCategoryPage();
	}

	public FasMastersAnalysisGroupPage goToFasMastersAnalysisGroupPage() {
		goToFasMastersFasGroupListPage();
		fasMastersAnalysisGroup.click();
		return new FasMastersAnalysisGroupPage();
	}

	public FasMastersAnalysisGroupDetailsPage goToFasMastersAnalysisGroupDetailsPage() {
		goToFasMastersFasGroupListPage();
		fasMastersAnalysisGroupDetails.click();
		return new FasMastersAnalysisGroupDetailsPage();
	}

	public FasMastersEmployeePage goToFasMastersEmployeePage() {
		goToFasMastersFasGroupListPage();
		fasMastersEmployee.click();
		return new FasMastersEmployeePage();
	}

	public FasMastersVoucherTypePage goToFasMastersVoucherTypePage() {
		goToFasMastersFasGroupListPage();
		fasMastersVoucherType.click();
		return new FasMastersVoucherTypePage();
	}

	public OMOperatingMetricsPage goToOMOperatingMetricsPage() {
		goToOMLedgerTypePage();
		oMOperatingMetrics.click();
		return new OMOperatingMetricsPage();
	}
	
	public OMOperatingMetricsGroupPage goToOMOperatingMetricsGroupPage() {
		goToOMLedgerTypePage();
		oMOperatingMetricsGroup.click();
		return new OMOperatingMetricsGroupPage();
	}

	public UploadsDBUploadPage goToUploadsDBUploadPage() {
		goToUploadsTBPage();
		uploadsDBUpload.click();
		return new UploadsDBUploadPage();
	}

	public UploadsTrialComputationPage goToUploadsTrialComputationPage() {
		goToUploadsTBPage();
		uploadsTrialComputation.click();
		return new UploadsTrialComputationPage();
	}

	public UploadsFasTBUploadPage goToUploadsFasTBUploadPage() {
		goToUploadsTBPage();
		uploadsFasTBUpload.click();
		return new UploadsFasTBUploadPage();
	}

	public UploadsFasTBValidatePage goToUploadsFasTBValidatePage() {
		goToUploadsTBPage();
		uploadsFasTBValidate.click();
		return new UploadsFasTBValidatePage();
	}

	public UploadsOMDataPage goToUploadsOMDataPage() {
		goToUploadsTBPage();
		uploadsOperatingMetricsData.click();
		return new UploadsOMDataPage();
	}

	public UploadsBudgetDataPage goToUploadsBudgetDataPage() {
		goToUploadsTBPage();
		uploadsBudgetData.click();
		return new UploadsBudgetDataPage();
	}

	public UploadListDBListPage goToUploadListDBListPage() {
		goToUploadListTBListPage();
		uploadListDBList.click();
		return new UploadListDBListPage();
	}

	public UploadListUnfreezeListPage goToUploadListUnfreezeListPage() {
		goToUploadListTBListPage();
		uploadListUnfreezeList.click();
		return new UploadListUnfreezeListPage();
	}

	public UploadListFasTBListPage goToUploadListFasTBListPage() {
		goToUploadListTBListPage();
		uploadListFasTBList.click();
		return new UploadListFasTBListPage();
	}

	public RLReportingLedgersToAccLedgersMappingPage goToRLReportingLedgersToAccLedgersMappingPage() {
		goToRLReportingLedgerListsPage();
		rLReportingLedgersToAccLedgersMapping.click();
		return new RLReportingLedgersToAccLedgersMappingPage();
	}

	public ReportDesignReportTemplatePage goToReportDesignReportTemplatePage() {
		goToReportDesignReportSectionPage();
		reportDesignReportTemplate.click();
		return new ReportDesignReportTemplatePage();
	}

	public DashboardDesignChartsPage goToDashboardDesignChartsPage() {
		goToDashboardDesignKeyNumbersPage();
		dashboardDesignCharts.click();
		return new DashboardDesignChartsPage();
	}
	
	public DashboardDesignTablesPage goToDashboardDesignTablesPage() {
		goToDashboardDesignKeyNumbersPage();
		dashboardDesignTables.click();
		return new DashboardDesignTablesPage();
	}

	public DashboardDesignDashboardCreationPage goToDashboardDesignDashboardCreationPage() {
		goToDashboardDesignKeyNumbersPage();
		dashboardDesignDashboardCreation.click();
		return new DashboardDesignDashboardCreationPage();
	}

	public DashboardDesignColorSetupPage goToDashboardDesignColorSetupPage() {
		goToDashboardDesignKeyNumbersPage();
		dashboardDesignColorSetup.click();
		return new DashboardDesignColorSetupPage();
	}

	public ReportRunReportCalculationPage goToReportRunReportCalculationPage() {
		goToReportRunDBRLMapPage();
		reportRunReportCalculation.click();
		return new ReportRunReportCalculationPage();
	}

	public ReportRunReportCalculatedPage goToReportRunReportCalculatedPage() {
		goToReportRunDBRLMapPage();
		reportRunReportCalculated.click();
		return new ReportRunReportCalculatedPage();
	}

	public ReportRunALReportCalculationPage goToReportRunALReportCalculationPage() {
		goToReportRunDBRLMapPage();
		reportRunALReportCalculation.click();
		return new ReportRunALReportCalculationPage();
	}

	public ReportRunPublishReportPage goToReportRunPublishReportPage() {
		goToReportRunDBRLMapPage();
		reportRunPublishReport.click();
		return new ReportRunPublishReportPage();
	}

	public UserDetailsUserPage goToUserDetailsUserPage() {
		goToUserDetailsUserRolePage();
		userDetailsUser.click();
		return new UserDetailsUserPage();
	}

	public UserDetailsUserGroupPage goToUserDetailsUserGroupPage() {
		goToUserDetailsUserRolePage();
		userDetailsUserGroup.click();
		return new UserDetailsUserGroupPage();
	}
	
	public void scrollRight() {
		scrollRight.click();
		scrollRight.click();
	}

}
