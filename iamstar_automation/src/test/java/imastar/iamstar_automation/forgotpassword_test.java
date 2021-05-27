package imastar.iamstar_automation;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Methods_automation.imastarmethods;
import pagesdata_automation.forgotPassword;

public class forgotpassword_test extends base 
{
	forgotPassword fp;
	imastarmethods imethods;
	imastarmethods starmethods;

	public forgotpassword_test() {
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		fp = new forgotPassword();
	}

	@Test
	public void forgot_password() throws InterruptedException 
	{

		//Extent Report
		ExtentReports extent = new ExtentReports();
     	ExtentSparkReporter spark = new ExtentSparkReporter("index.html");  //project report location, i.e. index.html
     	extent.attachReporter(spark);
     	//setting theme
     	spark.config().setTheme(Theme.DARK);
     	//setting report name
     	spark.config().setReportName("extent_report");
     	//setting document name
     	spark.config().setDocumentTitle("reportraBi");
     	extent.attachReporter(spark);
	
		        	
		
		String title=driver.getTitle();
		String ExpectedTitle="A-Star";
		Assert.assertEquals(title, ExpectedTitle);
		
		//checking the top Get Started button
		boolean getstatedtop_clickable = fp.getstart_topclick();
		Assert.assertEquals(getstatedtop_clickable, true);
		
		//Checking bottom Get Started Button
		boolean getstatednbottom_clickable = fp.getstart_topclick();
		Assert.assertEquals(getstatednbottom_clickable, true);
		
		
		//clicking on get started button(top)
		starmethods.getStrtedtopClickmethod();
		
		//navigating back
		driver.navigate().back();
		
		//clicking on get started button(bottom)
		JavascriptExecutor scrollexe = ((JavascriptExecutor) driver);
		scrollexe.executeScript("window.scrollBy(0, 3500)");
		
		starmethods.getStrtedbottomClickmethod();  


		// checking the forgot button
		ExtentTest expforgot_button = extent.createTest("Forgot button check");
		boolean clickable = fp.forgot_btn_clickable();
		Assert.assertEquals(clickable, true);
		expforgot_button.pass("button is clickable");
		expforgot_button.fail("button is not clickable");
		expforgot_button.fail(MediaEntityBuilder.createScreenCaptureFromPath("button_not_clickable.png").build());

		// forgoting password
		ExtentTest expforgot_pwd = extent.createTest("Forgoting Password");
		
		String forgotMail = property.getProperty("forgot_mail");
		fp.forgot_pass(forgotMail);
		ExtentTest expforgot_node = expforgot_pwd.createNode("Passing Mail ID in Forgot Password");
		expforgot_node.pass("Inserted Mail ID successfully");
		expforgot_node.fail("Email ID not Inserted");
		expforgot_node.fail(MediaEntityBuilder.createScreenCaptureFromPath("not_inserted_mail.png").build());

		fp.forgrotlink();
		ExtentTest expforgotlink_node = expforgot_pwd.createNode("Clicking on Forgot Password button");
		expforgotlink_node.pass("Clicked Successfully!");
		expforgotlink_node.fail("Not abled to click");
		expforgotlink_node.fail(MediaEntityBuilder.createScreenCaptureFromPath("not_abled_to_click.png").build());

		// cheking the Popup message
		/* @SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);

		 Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String alert_expected = "Mail Sent Successfully" ;
		String alert_text = alert.getText();
		System.out.println(alert_text);
		if(alert_text.equalsIgnoreCase(alert_expected))
		{
			System.out.println("Alert message is same! ");
		} */
		// swicthing into new window to make logging in gmail
		
		//calling whole gmail method
		fp.gmail();
		
		//cheking login with new password
		//fp.imastarpasswordcheck();
		
	}
	@AfterMethod
	public void exit()
	{
		driver.close();
		driver = null;
		
	}

}
