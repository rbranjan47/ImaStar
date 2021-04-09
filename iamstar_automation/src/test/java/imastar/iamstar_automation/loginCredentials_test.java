package imastar.iamstar_automation;


import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Methods_automation.imastarmethods;
import pagesdata_automation.login_crendetials;

public class loginCredentials_test extends imastarmethods
{
	 login_crendetials ht;
	 
	
	public loginCredentials_test()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization();
		ht =new login_crendetials();
	}
	
	@Test
	public void home_page() throws InterruptedException 
	{
		String title=ht.page_title();
		String ExpectedTitle="A-Star";
		Assert.assertEquals(title, ExpectedTitle);
		
		//checking the top Get Started button
		boolean getstatedtop_clickable = ht.getstart_topclick();
		Assert.assertEquals(getstatedtop_clickable, true);
		
		//Checking bottom Get Started Button
		boolean getstatednbottom_clickable = ht.getstart_bottomclick();
		Assert.assertEquals(getstatednbottom_clickable, true);
		
		
		//clicking on get started button(top)
		getStrtedtopClickmethod();
		
		//navigating back
		driver.navigate().back();
		
		//clicking on get started button(bottom)
		JavascriptExecutor scrollexe = ((JavascriptExecutor) driver);
		scrollexe.executeScript("window.scrollBy(0, 3500)");
		
		getStrtedbottomClickmethod();  
		Thread.sleep(3000);
		ht.click_login();
		
		//logging In
		String email_value = prop.getProperty("signin_email");
		String pass_value = prop.getProperty("signin_pass");
		
		//checking login button
		boolean login_clickable = ht.login_btn_click();
	    Assert.assertEquals(login_clickable, true); 
		
		ht.login_imastar(email_value, pass_value);
		System.out.println("logged In Successfully!");
		
		
	}
	
	@AfterMethod
	public void exit()
	{
		//driver.close();
	}
}
