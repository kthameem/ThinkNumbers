package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;
import com.thinknumbers.util.TestUtil;

public class CompanyExchangeRatePage extends BaseTest{

	@FindBy(xpath="//span[text()='Add']")
	WebElement btAdd;
	
	@FindBy(xpath="//span[text()='Save']")
	WebElement btSave;
	
	@FindBy(xpath="//span[text()='Close']")
	WebElement btClose;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	@FindBy(name="monthValue")
	WebElement month;
	
	
	public CompanyExchangeRatePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void selectMonth(String year, String month) {
		this.month.click();
		driver.findElement(By.xpath("//div[text()='"+year+"']")).click();
		driver.findElement(By.xpath("//div[text()='"+month+"']")).click();
	}
	
	public void selectCurrency(String currency) {
		AppReusableComponents.angularDn("currency_type", currency);
	}
	
	public void selectExchangeRate(String toCurrency, String value) {
		
		String toCurrList[] = toCurrency.split(",");
		String valueList[] = value.split(",");
		
		
		for(int i=0;i<toCurrList.length;i++) {
			driver.findElement(By.xpath("//div[contains(text(),'"+toCurrList[i]+"')]//parent::div//input")).clear();
			driver.findElement(By.xpath("//div[contains(text(),'"+toCurrList[i]+"')]//parent::div//input")).sendKeys(valueList[i]);
		}

		//driver.findElement(By.xpath("//div[contains(text(),'"+toCurrency+"')]//parent::div//input")).clear();
		//driver.findElement(By.xpath("//div[contains(text(),'"+toCurrency+"')]//parent::div//input")).sendKeys(value);
	}

	public void addExchangeRate() {
		btAdd.click();
	}
	
	public void enterExchangeRate(String year, String month, String fromCurrency, String toCurrency, String value) {

		selectMonth(year, month);
		selectCurrency(fromCurrency);
		selectExchangeRate(toCurrency, value);
	}
	
	public void saveExchangeRate() {
		TestUtil.waitUntilClickable(1000, btSave);
		btSave.click();
		TestUtil.waitUntilVisible(1000, alertMessage);
	}
	
	public void closeExchangeRate() {
		btClose.click();
	}
	
}
