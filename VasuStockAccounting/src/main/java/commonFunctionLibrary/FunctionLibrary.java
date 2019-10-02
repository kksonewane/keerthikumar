package commonFunctionLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;

public class FunctionLibrary 
{
	WebDriver driver;
	public static WebDriver startBrowser(WebDriver driver)throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecho.driver","D:\\@KK@\\VasuStockAccounting\\ExecutableFiles\\geckodriver.exe");
			driver=new FirefoxDriver();
		}else 
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver","D:\\@KK@\\VasuStockAccounting\\ExecutableFiles\\chromedriver.exe");
			driver=new ChromeDriver();
		}else 
			if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("ie"))
			{
				System.setProperty("webdriver.ie.driver","D:\\@KK@\\VasuStockAccounting\\ExecutableFiles\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
		return driver;
	}
	
	//open application
	public static void openApplication(WebDriver driver)throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("URL"));
		driver.manage().window().maximize();
	}
	
	public static void typeAction(WebDriver driver,String locatorType,String locatorValue, String Data)throws Throwable
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(Data);	
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(Data);	
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(Data);	
				}
	}
	
	//Click Action
	public static void clickAction(WebDriver driver, String locatorType, String locatorValue)throws Throwable
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).click();
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).click();
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).click();
				}
	}
	
	//Close Browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	//Wait for Element
	public static void waitForElement(WebDriver driver,String locatorType,String locatorValue,String waitTime)
	{
		WebDriverWait myWait= new WebDriverWait(driver, Integer.parseInt(waitTime));
		if(locatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}else
		if(locatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
		}else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			}
		}
	
	        //Scroll down page
			public static void pageDown(WebDriver driver) throws Exception
			{
				Actions action=new Actions(driver);
				action.sendKeys(Keys.PAGE_DOWN).build().perform();
				
				
				//driver.findElement(By.xpath("//*[@id='btnAction']")).click();
				
			}
			
			//capture Supplier Number
			public static void captureData(WebDriver driver, String locatorType, String locatorValue) throws Throwable
			{
				String data="";
				if(locatorType.equalsIgnoreCase("id"))
				{
					data=driver.findElement(By.id(locatorValue)).getAttribute("value");
				}else
					if(locatorType.equalsIgnoreCase("name"))
					{
						data=driver.findElement(By.name(locatorValue)).getAttribute("value");
					}else
						if(locatorType.equalsIgnoreCase("xpath"))
						{
							data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
						}
				
				FileWriter fw=new FileWriter("D:\\@KK@\\VasuStockAccounting\\CaptureData\\Data.txt");
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write(data);	
				bw.close();
			}
			
			//Supplier Creation Table Validation
			public static void tableValidation(WebDriver driver, String colum)throws Throwable
			{
				//reading the supplier number from text file
				FileReader fr=new FileReader("D:\\@KK@\\VasuStockAccounting\\CaptureData\\Data.txt");
				BufferedReader br=new BufferedReader(fr);
				String exp_data=br.readLine();
				
				//Converting String value into integer
				int columNum=Integer.parseInt(colum);
				
				if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.Panel"))).isDisplayed())
				{
					driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.Panel"))).click();
					
					driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.Box"))).clear();
					driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.Box"))).sendKeys();
					driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.Button"))).click();	
				}else
				{
					driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.Box"))).clear();
					driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.Box"))).sendKeys();
					driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.Button"))).click();	
				}
				WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable")));
				List<WebElement> rows=webtable.findElements(By.tagName("tr"));
				for(int i=1;i<=rows.size();i++)
				{
					String act_dada=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+columNum+"]/div/span")).getText();
					System.out.println(act_dada);
					//Validation
		            Assert.assertEquals(act_dada, exp_data);
		            break;
				}
				
			}
			//Generate Date
			public static String generateDate()
			{
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
				return sdf.format(date);
			}

}
