package imastar.iamstar_automation;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pagesdata_automation.facebook_login;
import pagesdata_automation.homepage;

public class facebooklogin_test extends base
{
	facebook_login ln;
	homepage hm;
	
	
	public facebooklogin_test()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		
		ln=new facebook_login();
	}
	
	@Test(priority = 1)
	public void verifytitile()
	{
		String title=ln.pagetitle();
		String ExpectedTitle="I'm A Star";
		Assert.assertEquals(title, ExpectedTitle);
	}
	
	@Test(priority = 2)
	public void facebook_btn_check()
	{
		boolean clickable = ln.facebook_btn_click();
		Assert.assertEquals(clickable, true);
	}
	
	@Test(priority = 3)
	public void login_check()
	{
		hm=ln.facebook(prop.getProperty("facebook_email_default"), prop.getProperty("facebook_pass_default"));
	}
	@AfterTest
	public void endup() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
	}
}
