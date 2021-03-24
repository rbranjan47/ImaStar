package pagesdata_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import imastar.iamstar_automation.base;

public class facebook_login extends base
{
	
	@FindBy(id = "facebook-connect")
	WebElement facebook_btn;
	
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
	//getting title
	public  String pagetitle()
	{
		return driver.getTitle();
	}
	public boolean facebook_btn_click()
	{
		return facebook_btn.isEnabled();
	}
	//Clicking on Facebook
	public void facebook(String fbemail, String fbpass ) throws InterruptedException
	{
		WebElement faceBook_button = driver.findElement(By.xpath("//span[@class = 'btn-label']/../../button[1]"));
		Actions act = new Actions(driver);
		act.moveToElement(faceBook_button).click().build().perform();
		
		Thread.sleep(10000);
		email.sendKeys(fbemail);
		password.sendKeys(fbpass);
		
		login_btn.click();
		
	}
	
}

