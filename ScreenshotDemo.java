package proq;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScreenshotDemo {
  private WebDriver driver;
  private String baseUrl = "https://guarded-plateau-2467.herokuapp.com/ui#/login";

  @Test
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
	try{  
	driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).click();
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("proq123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[5]/div/div[1]/span/a")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[3]/div/div[1]/div/div/div[2]/div/div[1]/span[2]/a")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 sidebar hidden-xs']/div/nav/ul/li[4]/ul/li[6]/a/span[text()='Pulse Analysis']")).click();
    Thread.sleep(5000);
    getScreenshot();
	}
	catch (Exception e){
	e.printStackTrace();		
	}
    }

  public void getScreenshot() throws Exception {
	  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	  Date date = new Date();
	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);      
      FileUtils.copyFile(scrFile, new File("D:\\Selenium-Screenshots\\Screenshot "+dateFormat.format(date)+".png"));	
  	}

@AfterTest
  public void tearDown() throws Exception {
	driver.quit();    
  }
}
