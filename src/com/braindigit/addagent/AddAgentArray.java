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





public class AddAgentArray {
	
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
	public void LoginAgency() {

			try {	
				
			driver.findElement(By.xpath("//a[contains(.,'Login')]")).click();
			driver.findElement(By.id("modlgn-username")).clear();
			driver.findElement(By.id("modlgn-username")).sendKeys("shriya");
			driver.findElement(By.id("modlgn-passwd")).clear();
			driver.findElement(By.id("modlgn-passwd")).sendKeys("brain");
		
				Thread.sleep(500);
		
			driver.findElement(By.id("modlgn-passwd")).click();

				Thread.sleep(1000);
			
				
			driver.findElement(By.name("Submit")).click();
		} catch (InterruptedException e)
			{
			System.out.println(e.getMessage());
			}
			//boolean expvalue = driver.findElement(By.id("daterange")).isDisplayed();
			//Assert.assertTrue(expvalue);
			
	 }
	 
 String[] agentlist =new String[]{"id","username","email","password","password1", "name","prefrences","suburb","address","phone","fax","facebook","twitter","linkedin","website","title"};
// String[] propertyList = new String[]{"title", "category", "area", "bathroom", "bedroom", "garage", "asd", "dsdf", "purpose", "price", "suburbs", "address"};

 
  @Test( dataProvider="agentlogin",priority =2 )

 public void LoginAgent(String[] agentlist) throws InterruptedException
  {
	
	  if(agentlist != null && agentlist.length>0)
	  {
		  
	
		//driver.navigate().refresh();
		
		driver.findElement(By.xpath("html/body/div[1]/aside/div/section/ul/li[4]/a[contains(.,'Agents')]")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/section[2]/form/div[2]/div[1]/div/a[contains(.,'New')]")).click() ;
		//id
		driver.findElement(By.id("internal_id")).sendKeys(agentlist[0]+lp.SetRandomString());
		//username		
		driver.findElement(By.id("username")).sendKeys(agentlist[1]+lp.SetRandomString());				
		//email				
		driver.findElement(By.id("email")).sendKeys(lp.SetRandomString()+agentlist[2]);				
		//password		
		driver.findElement(By.id("password")).sendKeys(agentlist[3]);				
		//password1				
		driver.findElement(By.id("password2")).sendKeys(agentlist[4]);				
		//username		
		driver.findElement(By.id("name")).sendKeys(agentlist[5]+lp.SetRandomString());				
		//description		
		driver.findElement(By.id("preferences")).sendKeys(agentlist[6]);
		//suburb				
		driver.findElement(By.id("area_search")).sendKeys(agentlist[7]);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[starts-with(@id,'ui-id-')]")).click();
    	//address			
		driver.findElement(By.id("address")).sendKeys(agentlist[8]);
		//phone
		Thread.sleep(500);
		driver.findElement(By.id("phone")).sendKeys(agentlist[9]);
		//fax
		driver.findElement(By.id("fax")).sendKeys(agentlist[10]);
		//zipcode
				//String zipcode1=ExcelConnect.getCellValue(path, sheet, i, count++);
				//driver.findElement(By.id("zipcode")).sendKeys(zipcode1);
		//facebook
		driver.findElement(By.id("facebook")).sendKeys(agentlist[11]);
		//twitter
		driver.findElement(By.id("twitter")).sendKeys(agentlist[12]);
		//linkedin
		driver.findElement(By.id("linkedin")).sendKeys(agentlist[13]);
		//website
		driver.findElement(By.id("website")).sendKeys(agentlist[14]);	
			
		driver.findElement(By.name("saveAgent")).click();				
	
		 String actualTitleDisplayed = driver.getTitle();
		  String requiredTitleMessage = agentlist[15];
		
		  Assert.assertEquals(actualTitleDisplayed,requiredTitleMessage);
		  
		  
		  
		  } 
  }
  
  excelconnection ex = new excelconnection();
	String path="D:/eclipse/backup.xlsx";
	String sheet = "agents";
  @DataProvider(name="agentlogin")
	public Object[][] loginData() throws EncryptedDocumentException, InvalidFormatException 
  {
	  int rownum= ex.getRowCount(path, sheet);
			Object[][] arrayObject = ex.getExcelData(path, sheet, rownum+1, 16);
			return arrayObject;
	}

  
  @AfterTest
	public void tearDown() throws InterruptedException {
				
		//driver.findElement(By.xpath("html/body/div[1]/header/nav/div/ul/li[3]/a")).click();
		//driver.findElement(By.xpath("html/body/div[1]/header/nav/div/ul/li[3]/ul/li[4]/a")).click();
	driver.quit();
	 
		}

	
}

