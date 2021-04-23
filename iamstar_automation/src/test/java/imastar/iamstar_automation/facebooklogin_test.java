package imastar.iamstar_automation;

import java.util.Iterator;
import java.util.Set;

//import java.util.Iterator;
//import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pagesdata_automation.facebook_login;


public class facebooklogin_test extends base
{
	facebook_login ln;
	
	
	public facebooklogin_test()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		initialization();
		
		ln=new facebook_login();
	}
	
	@Test
	public void login_facebook() throws InterruptedException
	{
		
		String title=ln.pagetitle();
		String ExpectedTitle="I'm A Star";
		Assert.assertEquals(title, ExpectedTitle);
		
		
		//checking the facebook button
		boolean clickable = ln.facebook_btn_click();
		Assert.assertEquals(clickable, true);
		
		String parent_window = driver.getWindowHandle();
		
		Set<String> windows = driver.getWindowHandles();
		
		Iterator<String> itr = windows.iterator();
		
		while(itr.hasNext())
		{
			String child_window = itr.next();
			
			if(!parent_window.equals(child_window))
			{
				driver.switchTo().window(child_window);
				
				//getting value of email and password
				String email = prop.getProperty("facebook_email_default");
				String pass = prop.getProperty("facebook_pass_default");
				
				ln.facebook(email, pass);	
			}  
		}	
		
		String email = prop.getProperty("facebook_email_default");
		String pass = prop.getProperty("facebook_pass_default");
		
		ln.facebook(email, pass);
	}
	
	@AfterTest
	public void endup() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
	}
}
