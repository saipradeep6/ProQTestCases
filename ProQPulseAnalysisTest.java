package proq;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ProQPulseAnalysisTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  
  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    baseUrl = "http://guarded-plateau-2467.herokuapp.com/ui#/login";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test(priority = 0, description="Login with the valid credentials")
  public void testProfile() throws Exception {
//	JavascriptExecutor jse = (JavascriptExecutor)driver;
    driver.get(baseUrl);
    driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("proq123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);
 //   jse.executeScript("scroll(0, 350)");
    driver.findElement(By.xpath("//div[23]/div/div[1]/span/a")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div[3]/div/div[1]/div/div/div/div/div[1]/span[1]/a")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div[2]/div/div[1]/div/nav/ul/li[4]/ul/li[6]/a/span")).click();
    Thread.sleep(3000);    
  }
    
  @Test(priority = 1, description="Testing the Pulse Analysis")
  public void Test_Frame() throws Exception {
	new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("1");
	Thread.sleep(4000);
	
	driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(7000);
    
    driver.findElement(By.linkText("Client Point of View")).click();
    Thread.sleep(7000);
    
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    Thread.sleep(5000);
    
    driver.findElement(By.linkText("Participation Report")).click();
    Thread.sleep(7000);
    
    driver.findElement(By.xpath("//a[contains(text(),'Pulse Analysis')]")).click();
    Thread.sleep(3000);
    
    Actions action = new Actions(driver);
    WebElement Report1 = driver.findElement(By.xpath("//tr[3]/td[2]"));
    action.moveToElement(Report1).click().perform();
    Thread.sleep(5000);
    
    WebElement Report2 = driver.findElement(By.xpath("//tr[4]/td[2]"));
    action.moveToElement(Report2).click().perform();
    Thread.sleep(5000);   
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

