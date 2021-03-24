package imastar.iamstar_automation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pagesdata_automation.forgotPassword;

public class forgotpassword_test extends base
{
		forgotPassword  fp;
		
		public forgotpassword_test()
		{
			super();
		}
		
		@BeforeMethod
		public void setup() throws InterruptedException
		{
			initialization();
			fp=new forgotPassword();
		}
		
		@Test
		public void home_page() throws InterruptedException 
		{
			
			String title=fp.page_title();
			String ExpectedTitle="I'm A Star";
			Assert.assertEquals(title, ExpectedTitle);
			
			
			//checking the facebook button
			boolean clickable = fp.forgot_btn_clickable();
			Assert.assertEquals(clickable, true);
			
			//forgoting password
			String forgotMail = prop.getProperty("forgot_mail");
			
			fp.forgot_pass(forgotMail);
			System.out.println("Successfully Passed Mail ID for Forgoting Password!");
			
			fp.forgrotlink();
			System.out.println("Clicked on 'SEND RESET LINK'");
			
			// cheking the Popup message
			@SuppressWarnings("deprecation")
			WebDriverWait wait =new WebDriverWait(driver, 10);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			
			String alert_text = alert.getText();
			System.out.println(alert_text);
			
			//swicthing into new window to make logging in gmail
			driver.switchTo().newWindow(WindowType.TAB);
			
			
		}
		
		@AfterMethod
		public void exit()
		{
			driver.close();
		}

}
