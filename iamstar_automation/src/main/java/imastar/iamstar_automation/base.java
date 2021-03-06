package imastar.iamstar_automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base
{
	public static WebDriver driver=null;
	public static Properties property;
	public static WebDriverWait wait;
	public static WebElement element;
	
	// constructor
	public  base() 
	{
		try
		{
			property= new Properties();
			FileInputStream file=new FileInputStream("C:\\Users\\Thinksysuser\\git\\ImAstar"
					+ "\\iamstar_automation\\src\\main\\java\\imastar_config\\Config.properties");
			property.load(file);
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
	
	@SuppressWarnings("deprecation")
	public static void initialization() throws InterruptedException
	{
     	
		String browsername=property.getProperty("browser");
		
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

		try
		{
			driver.get(property.getProperty("url"));
			Thread.sleep(4000);
			driver.navigate().refresh();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get(property.getProperty("url"));
			Thread.sleep(3000);
			driver.navigate().refresh();
		}
		Thread.sleep(3000);
		
		/* DEPRICIATED
		WebDriverWait wait=new WebDriverWait(driver, 10);
		WebElement profile_icon = driver.findElement(By.xpath("//a[@data-name='Profile']"));
		wait.until(ExpectedConditions.elementToBeClickable(profile_icon));
		profile_icon.click();
		*/
	}
}
