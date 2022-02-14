package com.thinknumbers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinknumbers.base.BaseTest;
import com.thinknumbers.util.AppReusableComponents;

public class FasMastersFasGroupListPage extends BaseTest{
	
	@FindBy(xpath="//a[text()='Map With Ledger']")
	WebElement mapLedger;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement iSearch;
	
	public FasMastersFasGroupListPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String searchFasGroup(String searchString) {
		iSearch.sendKeys(Keys.CLEAR);
		iSearch.clear();
		iSearch.sendKeys(searchString);
		return AppReusableComponents.searchTable(searchString);
	}

	public String validateFasGroup(String searchString) {
		String subType = driver.findElement(By.xpath("//tbody//td[text()='"+searchString+"']//following-sibling::td//following-sibling::td")).getText();
		return subType;
	}
	

}
