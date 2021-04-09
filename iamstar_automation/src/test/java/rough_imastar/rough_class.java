package rough_imastar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Methods_automation.imastarmethods;
import imastar.iamstar_automation.Util;

public class rough_class  
{
	public static WebDriver driver=null;
	public static Properties prop;
	public static WebDriverWait wait;
	public static WebElement element;
	
	imastarmethods imethos;
	
	// constructor
	public  rough_class() 
	{
		try
		{
			prop= new Properties();
			FileInputStream file=new FileInputStream("C:\\Users\\Thinksysuser\\git\\ImAstar\\iamstar_automation"
					+ "\\src\\main\\java\\imastar_config\\Config.properties");
			prop.load(file);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		 catch(IOException e)
		{
			 e.printStackTrace();
		}	
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public static void initialization() throws InterruptedException
	{
     	
		String browsername=prop.getProperty("browser");
		
		if (browsername.equals("chrome"))
		{
			//setting the path of chrome driver
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver =new ChromeDriver();
		}
		else if(browsername.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "‪./driver/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		
		else if(browsername.equals("internetexplorer"))
		{
			System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Util.timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Util.pageout, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get(prop.getProperty("prod_url"));
		Thread.sleep(4000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		/* DEPRICIATED
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement profile_icon = driver.findElement(By.xpath("//a[@data-name='Profile']"));
		wait.until(ExpectedConditions.elementToBeClickable(profile_icon));
		profile_icon.click();
		*/
		
		System.out.println(driver.getTitle());
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0, 3500)");
		
		driver.navigate().refresh();
		
		
	}
}
