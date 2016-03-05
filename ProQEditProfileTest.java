package proq;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ProQEditProfileTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  
  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    baseUrl = "http://guarded-plateau-2467.herokuapp.com/ui#/login";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testProfile() throws Exception {
	JavascriptExecutor jse = (JavascriptExecutor)driver;
    driver.get(baseUrl);
    driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("proq123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);
    
    jse.executeScript("scroll(0, 350)");
    driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div/div/div/div[8]/div/div[1]/span/a")).click();
    Thread.sleep(3000);
    
    driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div/div[1]/div/div/div[3]/div/div[1]/paneltool/a/i")).click();
    Thread.sleep(3000);
        
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Taj Vivanta");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("RFID Integration");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Dinakar");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("sspg007+1995@gmail.com");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//select")).click();
    new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("Optimization");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[3]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[3]/div[2]/div/select"))).selectByVisibleText("Deloitte");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[4]/div[1]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[4]/div[1]/div/select"))).selectByVisibleText("CSC");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[4]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[4]/div[2]/div/select"))).selectByVisibleText("IBM");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[5]/div[1]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[5]/div[1]/div/select"))).selectByVisibleText("Single-site");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[5]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[5]/div[2]/div/select"))).selectByVisibleText("One country");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[6]/div[1]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[6]/div[1]/div/select"))).selectByVisibleText("A2O");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[6]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[6]/div[2]/div/select"))).selectByVisibleText("Standard");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[7]/div[1]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[7]/div[1]/div/select"))).selectByVisibleText("Minor $100K - $1M");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[7]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[7]/div[2]/div/select"))).selectByVisibleText("Time & Expenses");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[8]/div/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[8]/div/div/select"))).selectByVisibleText("$Less than 300M");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[8]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[8]/div[2]/div/select"))).selectByVisibleText("Business One");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div/form/div[9]/div[1]/div/select")).click();
    new Select(driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div/form/div[9]/div[1]/div/select"))).selectByVisibleText("Asia / Pacific");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div/form/div[9]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div/form/div[9]/div[2]/div/select"))).selectByVisibleText("Hospitality");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[10]/div[1]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[10]/div[1]/div/select"))).selectByVisibleText("No");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//div[10]/div[2]/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[10]/div[2]/div/select"))).selectByVisibleText("No");
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(2000);
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

   }

