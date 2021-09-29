package com.thinknumbers.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.thinknumbers.base.TestBase;
import com.thinknumbers.util.TestUtil;

public class ThinkiamHomePage extends TestBase{
	
	@FindBy(xpath="//a[text()='ThinkNumbers']")
	WebElement thinkNumbersTitle;
	
	@FindBy(xpath="//a[@href='#/settings/users/change-password']//parent::li//following-sibling::li//img")
	WebElement thinkiamLogout;
	
	@FindBy(xpath="//button[text()='Yes']")
	WebElement confirmLogout;
	
	public ThinkiamHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyTitle() {
		return thinkNumbersTitle.isDisplayed();
	}
	
	public boolean verifyThinkiamLogout() {
		return thinkiamLogout.isDisplayed();
	}
	
	public HomePage goToThinkNumber() {
		thinkNumbersTitle.click();
		
		return new HomePage();
	}
	
	public void logOut() {
		thinkiamLogout.click();
		TestUtil.WaitTill_PageLoads(10);
		confirmLogout.click();
	}

}
