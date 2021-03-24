package pagesdata_automation;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import imastar.iamstar_automation.base;

public class login_crendetials extends base
{
	//email
	@FindBy(id = "email")
	WebElement email;
	//passsword
	@FindBy(id = "password")
	WebElement password;
	//login-button
	@FindBy(xpath = "//button[normalize-space()='Log In']")
	WebElement login;
	
	//Intializing the POM
		public login_crendetials()
		{
			PageFactory.initElements(driver, this);
		}
		//getting title
		public  String page_title()
		{
			return driver.getTitle();
		}
		public boolean login_btn_click()
		{
			return login.isEnabled();
		}
		
		//performing login operation
		public void login_imastar(String email_address, String fbpasswordpass) throws InterruptedException
		{
			email.sendKeys(email_address);
			password.sendKeys(fbpasswordpass);
			Thread.sleep(5000);
			login.click();
		}
}
