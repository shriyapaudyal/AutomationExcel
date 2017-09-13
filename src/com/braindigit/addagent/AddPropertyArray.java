package com.braindigit.addagent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddPropertyArray {
	public WebDriver driver;
	public WebDriverWait wait;
	LociplaceImplementaion lp =new LociplaceImplementaion();
	
	@BeforeTest
	 public void openDriver()
	 {		 
		 System.setProperty("webdriver.chrome.driver","D:\\shriya\\backup\\chromedriver_win32\\chromedriver.exe");		
			 driver = new ChromeDriver();
			 driver.get("http://lb25.braindigit.com/qa18/");
			// String actual="http://lb25.braindigit.com/qa18/";
			// Assert.assertEquals(actual, driver.getCurrentUrl());
	 }
	
	 @Test(priority = 1)
	 public void LoginAgent() {
		
		try {		

		driver.findElement(By.xpath("//a[contains(.,'Login')]")).click();
		driver.findElement(By.id("modlgn-username")).clear();
		
		driver.findElement(By.id("modlgn-username")).sendKeys("lina0");
		
		driver.findElement(By.id("modlgn-passwd")).clear();
		driver.findElement(By.id("modlgn-passwd")).sendKeys("brain");
	
			Thread.sleep(500);
	
		driver.findElement(By.id("modlgn-passwd")).click();

			Thread.sleep(1000);
		
			
		driver.findElement(By.name("Submit")).click();
	} catch (InterruptedException e) {
		System.out.println(e.getMessage());
	}
	}

	
	
	
}
