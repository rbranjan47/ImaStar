package pagesdata_automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import imastar.iamstar_automation.base;

public class forgotPassword extends base {
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

	// Intializing the POM
	public forgotPassword() {
		PageFactory.initElements(driver, this);
	}

	// getting title
	public String page_title() {
		return driver.getTitle();
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
	public void  gmai_title()
	{
		//checking the title of the Page
		String title_page = driver.getTitle();
		String original_title = "Gmail";
		Assert.assertEquals(title_page, original_title);

	}

	// opening new window to confirm mail
	@SuppressWarnings("deprecation")
	public void gmail() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//passing mail ID
		WebElement userElement = wait.until(ExpectedConditions.elementToBeClickable(gmail_email));
		userElement.click();
		userElement.clear();
		userElement.sendKeys(prop.getProperty("gmail_email_forgot"));
		//clicking on next button
		WebElement identifierNext = wait.until(ExpectedConditions.elementToBeClickable(gmail_next));
		identifierNext.click();
		//passing password
		WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(password_field));
		passwordElement.click();
		passwordElement.clear();
		passwordElement.sendKeys(prop.getProperty("gmail_password_forgot"));
		//clicking on next button
		WebElement passwordNext = wait.until(ExpectedConditions.elementToBeClickable(login_next));
		passwordNext.click();
		
		//clicking on the Inbox option
		WebElement composeElement = wait
				.until(ExpectedConditions.elementToBeClickable(inbox));
		composeElement.click();
		
		WebElement maximizeEmailElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//img[2]")));
		maximizeEmailElement.click();

		WebElement sendToElement = wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
		sendToElement.click();
		sendToElement.clear();
		sendToElement.sendKeys(String.format("%s@gmail.com", prop.getProperty("username")));

		
		WebElement subjectElement = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name = 'subjectbox']")));
		subjectElement.click();
		subjectElement.clear();
		subjectElement.sendKeys(prop.getProperty("email.subject"));

		WebElement emailBodyElement = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@role = 'textbox']")));
		emailBodyElement.click();
		emailBodyElement.clear();
		emailBodyElement.sendKeys(prop.getProperty("email.body"));

		WebElement sendMailElement = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Send']")));
		sendMailElement.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Message sent')]")));
		List<WebElement> inboxEmails = wait.until(
				ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class='zA zE']"))));

		for (WebElement email : inboxEmails) {
			if (email.isDisplayed() && email.getText().contains("email.subject")) {
				email.click();

				WebElement label = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(@title,'with label Inbox')]")));
				WebElement subject = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Subject of this message')]")));
				WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[contains(text(),'Single line body of this message')]")));

			}

		}
	}
}
