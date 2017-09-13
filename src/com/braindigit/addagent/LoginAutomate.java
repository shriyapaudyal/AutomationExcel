 package com.braindigit.addagent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginAutomate {
	
	public WebDriver driver;
	public WebDriverWait wait;
	String appURL = "http://lb25.braindigit.com/qa18/index.php?option=com_users&view=login";
  
  
  @BeforeTest
  public void setFirst() throws InterruptedException{
	  System.setProperty("webdriver.chrome.driver","D:\\shriya\\backup\\chromedriver_win32\\chromedriver.exe");		
		 driver = new ChromeDriver();
		// driver.get("http://lb25.braindigit.com/qa18/");

	  Thread.sleep(5000);
  }
  @Test(dataProvider="empLogin")
  public void login(String username, String password, String title){
	  driver.navigate().to(appURL);
	  //driver.findElement(By.id(".//*[@id='topnav']/div/ul/li/a")).click();
	  driver.findElement(By.id("username")).sendKeys(username);
	  driver.findElement(By.id("password")).sendKeys(password);
	  driver.findElement(By.xpath("html/body/section/div/div/div[1]/div[2]/form/fieldset/div[4]/div/button")).click();
	  
	  String actualTitleDisplayed = driver.getTitle();
	  String requiredTitleMessage = title;
	
	  Assert.assertEquals(requiredTitleMessage, actualTitleDisplayed);
  }
  excelconnection ex = new excelconnection();
  String sh ="agentlogin";
  @DataProvider(name="empLogin")
	public Object[][] loginData() throws EncryptedDocumentException, InvalidFormatException {
	  int rownum= ex.getRowCount("D:/eclipse/agents.xlsx", sh);
		Object[][] arrayObject = ex.getExcelData("D:/eclipse/agents.xlsx", sh, rownum+1, 3);
		return arrayObject;
	}
  
  
  @AfterTest
	public void tearDown() {
		driver.quit();
	}
}
