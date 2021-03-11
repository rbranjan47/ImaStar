package imastar.iamstar_automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base
{
	public static WebDriver driver=null;
	public static Properties prop;
	public static WebDriverWait wait;
	
	// constructor
	public  base() 
	{
		try
		{
			prop= new Properties();
			FileInputStream file=new FileInputStream("C:\\Users\\Thinksysuser\\git\\ImAstar\\"
					+ "iamstar_automation\\src\\main\\java\\pagesdata_automation\\Config.properties");
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Util.timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Util.pageout, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	
		driver.get(prop.getProperty("url"));
		Thread.sleep(5000);
		WebElement click_btn=driver.findElement(By.xpath("//div[@class='text-center agree-btn col']"));
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(click_btn));
		click_btn.click();
		driver.findElement(By.xpath("//a[@data-name='Profile']")).click();
	}
}
