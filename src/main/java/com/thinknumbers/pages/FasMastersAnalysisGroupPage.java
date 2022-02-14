package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;

public class FasMastersAnalysisGroupPage extends BaseTest{

	@FindBy(xpath="//button//span[contains(text(),'Add')]")
	WebElement btAdd;
	
	@FindBy(xpath="//button//span[contains(text(),'Save')]")
	WebElement btSave;
	
	@FindBy(xpath="//button//span[contains(text(),'Reset')]")
	WebElement btReset;

	@FindBy(xpath="//button//span[contains(text(),'Delete')]")
	WebElement btDelete;
	
	@FindBy(xpath="//div[contains(text(),'GROUP 1')]//following-sibling::div//input")
	WebElement inputGroup1;

	@FindBy(xpath="//div[contains(text(),'GROUP 2')]//following-sibling::div//input")
	WebElement inputGroup2;

	@FindBy(xpath="//div[contains(text(),'GROUP 3')]//following-sibling::div//input")
	WebElement inputGroup3;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	WebElement alertMessage;
	
	public FasMastersAnalysisGroupPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String readAlertMessage() {
		return alertMessage.getText();
	}
	
	public void clearAlertMessage() {
		alertMessage.click();
	}
	
	public void addGroup() {
		btAdd.click();
		inputGroup1.sendKeys("New Group Field");
		btSave.click();
	}
	
	public int inputGroupCount() {
		return driver.findElements(By.xpath("//div[@class='row-view ng-star-inserted']//input")).size();
	}
	
	public void deleteGroup() {
		btAdd.click();
		int size = inputGroupCount();
		System.out.println(size);
		driver.findElements(By.xpath("//div[@class='row-view ng-star-inserted']["+size+"]//input")).size();
		System.out.println(driver.findElements(By.xpath("//div[@class='row-view ng-star-inserted']["+size+"]//input")).size());
		btDelete.click();
	}
	
	public void resetGroup() {
		btAdd.click();
		inputGroup3.sendKeys("New Group Field");
		btReset.click();
	}
	
	public void updateGroup() {
		inputGroup1.clear();
		inputGroup1.sendKeys("Test");
		btSave.click();
	}
	
}
