package imastar.iamstar_automation;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pagesdata_automation.login_crendetials;

public class loginCredentials_test extends base
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
		
		ht=new login_crendetials();
	}
	
	@Test
	public void home_page() throws InterruptedException 
	{
		
		String title=ht.page_title();
		String ExpectedTitle="A";
		Assert.assertEquals(title, ExpectedTitle);
		
		
		//checking the facebook button
		boolean clickable = ht.login_btn_click();
		Assert.assertEquals(clickable, true);
		
		//logging In
		String email_value = prop.getProperty("signin_email");
		String pass_value = prop.getProperty("signin_pass");
		
		ht.login_imastar(email_value, pass_value);
		System.out.println("logged In Successfully!");
	}
	
	@AfterMethod
	public void exit()
	{
		driver.close();
	}
}
