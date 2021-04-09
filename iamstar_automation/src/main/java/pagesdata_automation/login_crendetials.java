package pagesdata_automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import imastar.iamstar_automation.base;

public class login_crendetials extends base {
	// email
	@FindBy(id = "email")
	WebElement email;
	// passsword
	@FindBy(id = "password")
	WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement login;
	// click_here_to_login button
	@FindBy(css = "a[class='gold']")
	WebElement clickloginhere;
	// setting button
	@FindBy(xpath = "//div[@class='asideMenuWrap']/ul/li[6]")
	WebElement setting_button;
	// logout button
	@FindBy(xpath = "//div[@class='right-sidebar-menu-item']/a[8]")
	WebElement logout_button;

	// top get started button
	@FindBy(xpath = "//a[@class='btn-start mt-sm-0']")
	WebElement getstarted_bottom;

	// get started bottom button
	@FindBy(xpath = "//a[@class='btn-start']")
	WebElement getstarted_top;

	// Intializing the POM
	public login_crendetials() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this);
	}

	// getting title
	public String page_title() {
		return driver.getTitle();
	}

	public boolean getstart_topclick() {
		return getstarted_top.isEnabled();
	}

	public boolean getstart_bottomclick() {
		return getstarted_bottom.isEnabled();
	}

	public boolean login_btn_click() {
		return login.isEnabled();
	}

	// clicking on Click here to login
	public void click_login() {
		clickloginhere.click();
	}

	// clicking on get started bottom button
	// performing login operation
	public void login_imastar(String email_address, String fbpasswordpass) throws InterruptedException {
		email.sendKeys(email_address);
		password.sendKeys(fbpasswordpass);
		Thread.sleep(3000);
		login.click();
	}

	// clicking on setting button
	public void logout() {
		setting_button.click();
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(logout_button));
		logout_button.click();
	}

}
