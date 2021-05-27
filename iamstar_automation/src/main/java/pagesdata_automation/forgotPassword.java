package pagesdata_automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Methods_automation.imastarmethods;
import imastar.iamstar_automation.base;

public class forgotPassword extends base 
{
	imastarmethods imethods;
	
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

	@FindBy(xpath = "//div[@data-tooltip='Inbox']")
	WebElement inbox;

	@FindBy(xpath = "//a[contains(text(),'Reset Password')]")
	WebElement reset_link;
	
	@FindBy(id = "email")
	WebElement email_imastar;
	
	@FindBy(id = "password")
	WebElement password_imastar;
	
	@FindBy(xpath = "//button[contains(text(),'Log In')]")
	WebElement login_imastar;
	
	//top get started button
	@FindBy(xpath = "//a[@class='btn-start mt-sm-0']")
	WebElement getstarted_bottom;
		
	//get started bottom button
	@FindBy(xpath = "//a[@class='btn-start']")
	WebElement getstarted_top;
		
	
	// Intializing the POM
	public forgotPassword() {
		PageFactory.initElements(driver, this);
	}

	// getting title
	public String page_title() {
		return driver.getTitle();
	}
	
	public boolean getstart_topclick()
	{
			return getstarted_top.isEnabled();
	}

	// checking fogot button
	public boolean forgot_btn_clickable() {
		return forgot.isEnabled();
	}

	// checking sendlink button
	public boolean send_link_clickable() {
		return click_link.isEnabled();
	}

	// forgot mails
	public void forgot_pass(String email) {
		forgot.click();
		email_field.sendKeys(email);
	}

	// clicking on the link
	public void forgrotlink() {
		click_link.click();
	}
	
	// opening new window to confirm mail
	@SuppressWarnings("deprecation")
	public void gmail() throws InterruptedException 
	{
		//driver.switchTo().newWindow(WindowType.TAB);
		
		//storing in the LIST
	    List<String> windows =  imethods.window_Handle();
	    driver.switchTo().window(windows.get(1));
	    Thread.sleep(3000);
		
		driver.get(property.getProperty("gmail_link_address"));
	 
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// passing mail ID
		WebElement userElement = wait.until(ExpectedConditions.elementToBeClickable(gmail_email));
		userElement.click();
		userElement.clear();
		userElement.sendKeys(property.getProperty("gmail_email_forgot"));
		// clicking on next button
		WebElement identifierNext = wait.until(ExpectedConditions.elementToBeClickable(gmail_next));
		identifierNext.click();
		// passing password
		WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(password_field));
		passwordElement.click();
		passwordElement.clear();
		passwordElement.sendKeys(property.getProperty("gmail_password_forgot"));
		// clicking on next button
		WebElement passwordNext = wait.until(ExpectedConditions.elementToBeClickable(login_next));
		passwordNext.click();

		// clicking on the Inbox option
		WebElement composeElement = wait.until(ExpectedConditions.elementToBeClickable(inbox));
		composeElement.click();

		// getting all text present on the message
		List<WebElement> subjects = driver.findElements(By.xpath("//div[@class='aeF']"));
		int subject_count = subjects.size();

		for (int i = 0; i < subject_count; i++) {
			String match_text = subjects.get(i).getText();
			String required_subject = "Reset Password - I'm A Star";
			if (match_text.equalsIgnoreCase(required_subject))
			{
				subjects.get(i).click();
				break;
			}
		}
		// clicking on reset button
		WebElement reset_button = wait.until(ExpectedConditions.elementToBeClickable(reset_link));
		reset_button.click();

		// switching into child window
		List<String> window =  imethods.window_Handle();
		driver.switchTo().window(window.get(2));
		
		String new_password = property.getProperty("imastar_new_password");
		System.out.println("new password is :" + new_password);
		driver.findElement(By.id("restpassword")).sendKeys(new_password);
		driver.findElement(By.id("restconfirmpassword")).sendKeys(new_password);
		
		try 
		{
			driver.findElement(By.xpath("//button[@type='submit']")).click();
		} catch (Exception e) 
		{
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		//switching back to parent window i.e. gmail
		driver.switchTo().window(window.get(1));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//img[@class='gb_Da gbii']")).click();
		driver.findElement(By.id("gb_71")).click();	
	}
	
	public void imastarpasswordcheck()
	{
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(property.getProperty("login_url"));
		
		//sending mail
		email_imastar.sendKeys(property.getProperty("functiontest871@gmail.com"));
		password_imastar.sendKeys(property.getProperty("signin_pass"));
		
		
		if(login_imastar.isEnabled())
		{
			login_imastar.click();
			System.out.println("logged In successfully with new Password");
		}
		else
		{
			
		}
	}
}
