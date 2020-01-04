package com.group.SocBuilder.SocBuilderDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Login test cases
 */

public class AppTest 
{
	WebDriver driver;
	
	//Browser configuraztion setup method
	@BeforeMethod
	public void setUp()
	{	
		System.setProperty("webdriver.chrome.driver", "C:/Users/Kiran/selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("product.socbuilder.com/");
		
	}
	
	@Test(priority=1)
	public void verifyPageTitle() {
	String expectedTitle ="SOC-Builder | Administrator Panel";
	Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	@Test(priority=2)
	public void Login() {
	driver.findElement(By.name("username")).sendKeys("admin");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123");
	driver.findElement(By.xpath("//button[@name='submit_login']")).click();
	Boolean flag = driver.findElement(By.xpath("//div[@class='alert alert-success']")).isDisplayed();
	Assert.assertTrue(flag);
	}
	
	@Test
	public void DownloadReports() {
		
	 driver.findElement(By.xpath("//a[@contains(text(),'Download']")).click();
	
	}	
	
	//To quit the browser after everything is executed
	@AfterMethod
	public void tearDown() {
	driver.quit();
	}
	}
	
