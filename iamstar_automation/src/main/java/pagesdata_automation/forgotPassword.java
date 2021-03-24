package pagesdata_automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import imastar.iamstar_automation.base;

public class forgotPassword extends base
{
	@FindBy(xpath = "//span[contains(text(),'Forgot Password')]")
	WebElement forgot;
	
	@FindBy(id = "forgotemail")
	WebElement email_field;
	
	@FindBy(xpath = "//button[contains(text(),'Send Reset Link')]")
	WebElement click_link;
	
	@FindBy(id = "identifierId")
	WebElement gmail_email;
	
	@FindBy(id = "identifierNext")
	WebElement gmail_next;
	
	@FindBy(xpath = "//input[@type='password']")
	WebElement password_field;
	
	@FindBy(id = "passwordNext")
	WebElement login_next;
	
	
	//Intializing the POM
			public forgotPassword()
			{
				PageFactory.initElements(driver, this);
			}
			//getting title
			public  String page_title()
			{
				return driver.getTitle();
			}
			//checking fogot button
			public boolean forgot_btn_clickable()
			{
				return forgot.isEnabled();
			}
			//checking sendlink button
			public boolean send_link_clickable()
			{
				return click_link.isEnabled();
			}
			
			//forgot mails
			public void forgot_pass(String email)
			{
				forgot.click();
				email_field.sendKeys(email);
			}
			
			//clicking on the link
			public void forgrotlink()
			{
				click_link.click();
			}
			
			//opening new window to confirm mail
			
			
			
}
