package Methods_automation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import freemarker.core.JavaScriptOutputFormat;
import imastar.iamstar_automation.base;

public class imastarmethods extends base
{
	public static WebElement element;
	//JS click methods
	public void js_click()
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	//scrolling
	public void scrollto()
	{
		JavascriptExecutor scrollexe = (JavascriptExecutor) driver;
		scrollexe.executeScript("window.scrollTo(0, 2500)", element);
	}
	
	
	//child window handle
	public List<String> window_Handle() 
	{
		
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		
		List<String> list=new ArrayList<String>();
		System.out.println(windowHandles.size());
		for(int i=0; i<windowHandles.size() ; i++)
		{
		list.add(iterator.next());
		}
		return list;
	}
	
}

