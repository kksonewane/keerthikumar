package commonFunctionLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StockAccounting 
{
	WebDriver driver;
	String Result;
	//app launch
	public String applaunch(String URL)
	{
		
		System.setProperty("webdriver.chrome.driver", "D:\\@KK@\\VasuStockAccounting\\ExecutableFiles\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		//validate
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			Result="Pass";
		}else
		{
			Result="Fail";
		}
		return Result;
	}
	public String appLogin(String Username,String Password)throws Throwable
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(Username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(Password);
		Thread.sleep(3000);
		driver.findElement(By.id("btnsubmit")).click();
		Thread.sleep(3000);
		//validate
		if(driver.findElement(By.id("logout")).isDisplayed())
		{
			Result="Pass";
		}else
		{
			Result="Fail";
		}
		return Result;
	}
	
	public String supplierCreation(String sName,String add,String city,String country,String cPerson,String pNumber,String email,String mNumber,String notes)throws Throwable
	{
		driver.findElement(By.id("mi_a_suppliers")).click();
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		//String value;
		String exp_data=driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
		System.out.println(exp_data);
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(sName);
		driver.findElement(By.id("x_Address")).sendKeys(add);
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).sendKeys(country);
		driver.findElement(By.id("x_Contact_Person")).sendKeys(cPerson);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(pNumber);
		driver.findElement(By.id("x__Email")).sendKeys(email);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mNumber);
		driver.findElement(By.id("x_Notes")).sendKeys(notes);
		//Scroll down the page
		Actions action=new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.id("btnAction")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		//Validation
		if(driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed())
		{
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_data);
			driver.findElement(By.id("btnsubmit")).click();
		}else
		{
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.id("btnsubmit")).click();
		}
		Thread.sleep(2000);
	String act_data=driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
	if(exp_data.equals(act_data))
	{
		Result="Pass";
	}else
	{
		Result="Fail";
	}
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
		return Result;
	}
	public String applogout()throws Throwable
	{
		Thread.sleep(2000);
		driver.findElement(By.id("logout")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
		//validate
		Thread.sleep(2000);
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			Result="Pass";
		}else
		{
			Result="Fail";
		}
		
		return Result;
	}
	public void appClose()
	{
		driver.close();
	}
	
	public static void main(String[]args)throws Throwable	
	{
		StockAccounting app=new StockAccounting();
		//SupplierCreation
		System.out.println(app.applaunch("http://webapp.qedge.com"));
		System.out.println(app.appLogin("admin", "master"));
		System.out.println(app.supplierCreation("Kirtykumar","Ameerpeth, Hyderabad", "Hyderabad", "India", "Kirtykumar", "07183-232843", "kksonewane@gmail.com", "9175818091", "All are OK"));
		System.out.println(app.applogout());
		app.appClose();
	}

}
