package pagesdata_automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import imastar.iamstar_automation.base;

public class facebook_login extends base
{

	//POM
	@FindBy(id = "facebook-connect")
	WebElement Facebook;
	
	@FindBy(id = "email")
	WebElement email;
	
	@FindBy(id = "pass")
	WebElement password;
	
	@FindBy(id = "loginbutton")
	WebElement login_btn;
	
	//Intializing the POM
	public facebook_login()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Clicking on Facebook
	public homepage facebook(String fbemail, String fbpass )
	{
		Facebook.click();
		email.sendKeys(fbemail);
		password.sendKeys("fbpass");
		
		login_btn.click();
		
		return new homepage();
	}
	
}

