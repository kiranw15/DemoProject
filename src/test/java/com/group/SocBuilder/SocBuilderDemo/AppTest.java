package com.group.SocBuilder.SocBuilderDemo;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Login test cases
 */

public class AppTest 
{
	WebDriver driver;
	String downloadFilepath=System.getProperty("user.dir")+"\\Downloads";
	
	//Browser configuraztion setup method
	@BeforeClass
	public void setUp()
	{	
		Configurations.setUp();
		System.setProperty("webdriver.chrome.driver", "Drivers\\Chrome_Driver\\chromedriver.exe");
		Commons.clearFolder(downloadFilepath);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	       chromePrefs.put("profile.default_content_settings.popups", 0);
	       System.out.println(downloadFilepath);
	       chromePrefs.put("download.default_directory", downloadFilepath);
	       
	       ChromeOptions options = new ChromeOptions();
	       options.setExperimentalOption("prefs", chromePrefs);

	       options.addArguments("--disable-extensions"); //to disable browser extension popup
	       options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		
		driver =new ChromeDriver(options);
		driver.get(Configurations.getURL());
		
	}
	
	@Test(priority=1)
	public void verifyPageTitle() {
	String expectedTitle ="SOC-Builder | Administrator Panel";
	Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	@Test(priority=2)
	public void Login() {
	driver.findElement(By.name("username")).sendKeys(Configurations.getUserID());
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Configurations.getPassword());
	driver.findElement(By.xpath("//button[@name='submit_login']")).click();
	Boolean flag = driver.findElement(By.xpath("//img[contains(@src, 'http://product.socbuilder.com//assets/backend/images/success.png')]")).isDisplayed();
	Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void DownloadReports() throws InterruptedException {
		
	 driver.findElement(By.xpath("//a[contains(@href,'http://product.socbuilder.com/curriculum_dev/download_course')]")).click();
	 Thread.sleep(2000);
	 Commons.copyFile(downloadFilepath);
	}
	
	
	@Test(priority=4)
	public void testSFR() throws InterruptedException {
		
		float no_student=98;
		float no_faculty=12;
		float result =no_student/no_faculty;
		DecimalFormat df= new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.DOWN);
		
		driver.findElement(By.xpath("//span[text()='SOC Metrics']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='SFR ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='no_student']")).sendKeys(String.valueOf(no_student));
		driver.findElement(By.xpath("//input[@id='no_faculty']")).sendKeys(String.valueOf(no_faculty));
		driver.findElement(By.xpath("//button[text()='Calculate ']")).click();
		String appResult=driver.findElement(By.xpath("//input[@id='sfr_final']")).getAttribute("value");
		System.out.println("appResult #########"+appResult);
		Assert.assertEquals(appResult, String.valueOf(df.format(result))+" : 1");
	
	}
	
	@Test(dataProvider="getData",priority=5)
	public void testFTE(String semester,String yearSTR) throws InterruptedException{
		
		driver.findElement(By.xpath("//a[text()='FTE ']")).click();
		Select term=new Select(driver.findElement(By.xpath("//select[@id='term']")));
		Select year=new Select(driver.findElement(By.xpath("//select[@id='year']")));
		term.selectByValue(semester);
		year.selectByValue(yearSTR);
		
		driver.findElement(By.xpath("//a[text()=' Show']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//*[@id='tab_1']")).getText().contains("sorry, this semester not exists!")){
			System.out.println("NO SEMESTER ERROR");
			Assert.assertTrue(true, "NO SEMESTER ERROR");
		} else if(driver.findElement(By.xpath("//table[@id='table_recordtbl1']")).isDisplayed()){
			Assert.assertTrue(true, "Data for semester exists");	
		}
		else
			Assert.assertFalse(false);
			
		
	}

	
	//To quit the browser after everything is executed
	@AfterClass
	public void tearDown() throws InterruptedException {
	driver.findElement(By.xpath("//span[text()=' Builder']")).click();
	Thread.sleep(1000);
	driver.quit();
	}
	
	@DataProvider
	public Object[][] getData(){
	    return new Object[][] 
	    	{
	            { "Fall", "2020"},
	            { "Winter", "2020"},
	            { "Spring", "2020"},
	            { "Summer","2020"}
	        };

	    }
	
	}	
	
