package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class DashboardDesignDashboardCreationPage extends BaseTest{

	@FindBy(xpath="//button[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//button[text()='Back']")
	WebElement btBack;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement btUpdate;
	
	@FindBy(xpath="//Span[text()='Save']")
	WebElement btPopSave;
	
	@FindBy(xpath="//Span[text()='Close']")
	WebElement btPopClose;
	
	@FindBy(name="title")
	WebElement iTitle;
	
	@FindBy(name="noOfRow")
	WebElement dnRows;
	
	@FindBy(xpath="//mat-select[@ng-reflect-name='idRow1']")
	WebElement dnRow1;
	
	@FindBy(xpath="//mat-select[@ng-reflect-name='idRow2']")
	WebElement dnRow2;
	
	@FindBy(xpath="//mat-select[@ng-reflect-name='idRow3']")
	WebElement dnRow3;
	
	@FindBy(xpath="//mat-select[@ng-reflect-name='noOfVal1']")
	WebElement dnRowCount1;
	
	@FindBy(xpath="//mat-select[@ng-reflect-name='noOfVal2']")
	WebElement dnRowCount2;
	
	@FindBy(xpath="//mat-select[@ng-reflect-name='noOfVal3']")
	WebElement dnRowCount3;
	
	//@FindBy(xpath="//div[@role='alertdialog']")
	@FindBy(xpath="//div[@id='toast-container']//div//div[1]")
	WebElement alertMessage;
	
	@FindBy(xpath="//button[text()='Key number 1']")
	WebElement btNumber1;
	
	@FindBy(xpath="//button[text()='Key number 2']")
	WebElement btNumber2;

	@FindBy(xpath="//button[text()='Key number 3']")
	WebElement btNumber3;
	
	@FindBy(xpath="//button[text()='Key number 4']")
	WebElement btNumber4;
	
	@FindBy(xpath="//button[text()='Key number 5']")
	WebElement btNumber5;
	
	@FindBy(xpath="//button[text()='Key number 6']")
	WebElement btNumber6;
	
	@FindBy(xpath="//button[text()='Chart 1']")
	WebElement btChart1;
	
	@FindBy(xpath="//button[text()='Chart 2']")
	WebElement btChart2;
	
	@FindBy(xpath="//button[text()='Chart 3']")
	WebElement btChart3;
	
	@FindBy(xpath="//button[text()='Chart 4']")
	WebElement btChart4;
	
	@FindBy(xpath="//button[text()='Table 1']")
	WebElement btTable1;
	
	@FindBy(xpath="//button[text()='Table 2']")
	WebElement btTable2;
	
	@FindBy(xpath="//button[text()='Table 3']")
	WebElement btTable3;
	
	@FindBy(xpath="//button[text()='Table 4']")
	WebElement btTable4;
	
	
	public DashboardDesignDashboardCreationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void selectNoRows(String noRows) {
		AppReusableComponents.angularDn("noOfRow", noRows);
	}
	
	public void selectRow1(String row1) {
		dnRow1.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +row1+"')]")).click();
	}
	
	public void selectRow2(String row2) {
		dnRow2.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +row2+"')]")).click();
		//AppReusableComponents.angularDn("idRow2", row2);
	}
	
	public void selectRow3(String row3) {
		dnRow3.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +row3+"')]")).click();
		//AppReusableComponents.angularDn("idRow3", row3);
	}
	
	public void selectRowCount1(String rowNum11) {
		dnRowCount1.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +rowNum11+"')]")).click();
		//AppReusableComponents.angularDn("noOfVal1", rowNum11);
	}
	
	public void selectRowCount2(String rowNum12) {
		dnRowCount2.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +rowNum12+"')]")).click();
		//AppReusableComponents.angularDn("noOfVal2", rowNum12);
	}
	
	public void selectRowCount3(String rowNum13) {
		dnRowCount3.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" +rowNum13+"')]")).click();
		//AppReusableComponents.angularDn("noOfVal3", rowNum13);
	}
	
	public void selectDropDown(String value) {
		AppReusableComponents.angularDn("idKeyNumbers", value);
		btPopSave.click();
	}
	
	public void createDashboard(String title, String noRows, String row1, String row2, String row3, 
			String rowCount1, String rowCount2, String rowCount3, 
			String keyNumbersNames,
			String chartsNames, 
			String tableNames) {
		
		String number[] = keyNumbersNames.split(",");
		String chart[] = chartsNames.split(",");
		String table[] = tableNames.split(",");
		
		System.out.println(number);
		System.out.println(chart);
		System.out.println(table);
		
		TestUtil.WaitTill_PageLoads(10);
		
		iTitle.sendKeys(title);
		selectNoRows(noRows);
		
		TestUtil.waitUntilClickable(1000, dnRow1);
		
		//Select Row 1 data
		selectRow1(row1);
		selectRowCount1(rowCount1);
		
		//If loop to select Row 2, 3 & 4 data
		if(noRows.equalsIgnoreCase("2")) {
			selectRow2(row2);
			selectRowCount2(rowCount2);
		} else if(noRows.equalsIgnoreCase("3")) {
			selectRow2(row2);
			selectRowCount2(rowCount2);
			selectRow3(row3);
			selectRowCount3(rowCount3);
		} /*else if(noRows.equalsIgnoreCase("4")) {
			selectRow2(row2);
			selectRowNum12(row12);
			selectRow3(row3);
			selectRowNum13(row13);
			selectRow4(row4);
			selectRowNum14(row14);
		}*/
		
		//Select Key Number for Row 1	
		if(rowCount1.equalsIgnoreCase("1")) {
			btNumber1.click();
			selectDropDown(number[0]);
		} else if(rowCount1.equalsIgnoreCase("2")) {
			btNumber1.click();
			selectDropDown(number[0]);
			btNumber2.click();
			selectDropDown(number[1]);
		} else if(rowCount1.equalsIgnoreCase("3")) {
			btNumber1.click();
			selectDropDown(number[0]);
			btNumber2.click();
			selectDropDown(number[1]);
			btNumber3.click();
			selectDropDown(number[2]);
		} else if(rowCount1.equalsIgnoreCase("4")) {
			btNumber1.click();
			selectDropDown(number[0]);
			btNumber2.click();
			selectDropDown(number[1]);
			btNumber3.click();
			selectDropDown(number[2]);
			btNumber4.click();
			selectDropDown(number[3]);
		}
		
		
		//Select Chart for Row 2
			if(rowCount2.equalsIgnoreCase("1")) {
				btChart1.click();
				selectDropDown(chart[0]);
			} else if(rowCount2.equalsIgnoreCase("2")) {
				btChart1.click();
				selectDropDown(chart[0]);
				btChart2.click();
				selectDropDown(chart[1]);
			} else if(rowCount2.equalsIgnoreCase("3")) {
				btChart1.click();
				selectDropDown(chart[0]);
				btChart2.click();
				selectDropDown(chart[1]);
				btChart3.click();
				selectDropDown(chart[2]);
			} else if(rowCount2.equalsIgnoreCase("4")) {
				btChart1.click();
				selectDropDown(chart[0]);
				btChart2.click();
				selectDropDown(chart[1]);
				btChart3.click();
				selectDropDown(chart[2]);
				btChart4.click();
				selectDropDown(chart[3]);
			}
		
		
		//Select Table for Row 3
			if(rowCount3.equalsIgnoreCase("1")) {
				btTable1.click();
				selectDropDown(table[0]);
			} else if(rowCount3.equalsIgnoreCase("2")) {
				btTable1.click();
				selectDropDown(table[0]);
				btTable2.click();
				selectDropDown(table[1]);
			} else if(rowCount3.equalsIgnoreCase("3")) {
				btTable1.click();
				selectDropDown(table[0]);
				btTable2.click();
				selectDropDown(table[1]);
				btTable3.click();
				selectDropDown(table[2]);
			} else if(rowCount3.equalsIgnoreCase("4")) {
				btTable1.click();
				selectDropDown(table[0]);
				btTable2.click();
				selectDropDown(table[1]);
				btTable3.click();
				selectDropDown(table[2]);
				btTable4.click();
				selectDropDown(table[3]);
			}
		
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void updateDashboard() {
		AppReusableComponents.editTable("Active");
		TestUtil.waitUntilClickable(1000, btUpdate);
		btUpdate.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
}
