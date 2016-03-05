package proq;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProQLoginTest {
  private WebDriver driver;
  private String baseUrl = "http://newdev.proq-erp.com/ui#/login";
  private SoftAssert softAssert = new SoftAssert();
  
  @BeforeTest(description="Launches the ProQ-ERP site")
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test(priority = 0, description="Scenario #1: Attempting Login Functionality with the invalid Password.")
  public void Invalid_Password() throws Exception {
    driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).click();
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("ProQ2015");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(3000);
    WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-danger > div"));
    String Str1 = element1.getText();
    Boolean ele1 = element1.isDisplayed();
    softAssert.assertTrue(ele1);   
    System.out.println(Str1);
  } 
    
  @Test(priority = 1, description="Login with ProQ Admin Credentials")
  public void ProQ_Admin() throws Exception {
    
	driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).click();
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("proq123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    String Str3 = driver.getCurrentUrl();
    Assert.assertEquals(Str3, baseUrl);
    Thread.sleep(6000);
    driver.findElement(By.cssSelector("span.ng-binding")).click();    
    driver.findElement(By.linkText("Logout")).click();
    System.out.println(Str3);
    Thread.sleep(5000);    
  }
  
  @Test(priority = 2, description="Login with Vendor Admin Credentials")
  public void Vendor_Admin() throws Exception {	    
	driver.findElement(By.id("userEmail")).clear();
	driver.findElement(By.id("userEmail")).sendKeys("saipradeep6@gmail.com");
	driver.findElement(By.id("userPassword")).click();
	driver.findElement(By.id("userPassword")).clear();
	driver.findElement(By.id("userPassword")).sendKeys("proq123");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	String Str3 = driver.getCurrentUrl();
	Assert.assertEquals(Str3, baseUrl);
	Thread.sleep(5000);
	driver.findElement(By.cssSelector("span.ng-binding")).click();	
	driver.findElement(By.linkText("Logout")).click();
	System.out.println(Str3);
	Thread.sleep(2000);    
  }
  
  @Test(priority = 3, description="Login with Project Admin Credentials")
  public void Project_Admin() throws Exception {	    
	driver.findElement(By.id("userEmail")).clear();
	driver.findElement(By.id("userEmail")).sendKeys("saipradeep6+1@gmail.com");
	driver.findElement(By.id("userPassword")).click();
	driver.findElement(By.id("userPassword")).clear();
	driver.findElement(By.id("userPassword")).sendKeys("123456");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	String Str3 = driver.getCurrentUrl();
	Assert.assertEquals(Str3, baseUrl);
	Thread.sleep(5000);
	driver.findElement(By.cssSelector("span.ng-binding")).click();	
	driver.findElement(By.linkText("Logout")).click();
	System.out.println(Str3);
	Thread.sleep(2000);    
  }
  
  @Test(priority = 4, description="Login with Respondent Credentials")
  public void Respondent() throws Exception {	    
	driver.findElement(By.id("userEmail")).clear();
	driver.findElement(By.id("userEmail")).sendKeys("saipradeep6+2@gmail.com");
	driver.findElement(By.id("userPassword")).click();
	driver.findElement(By.id("userPassword")).clear();
	driver.findElement(By.id("userPassword")).sendKeys("123456");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	String Str3 = driver.getCurrentUrl();
	Assert.assertEquals(Str3, baseUrl);
	Thread.sleep(5000);
	driver.findElement(By.cssSelector("span.ng-binding")).click();	
	driver.findElement(By.linkText("Logout")).click();
	System.out.println(Str3);
	Thread.sleep(2000);    
  }
  
  @Test(priority = 5, description="Login with Viewer Credentials")
  public void Viewer() throws Exception {	    
	driver.findElement(By.id("userEmail")).clear();
	driver.findElement(By.id("userEmail")).sendKeys("saipradeep6+987@gmail.com");
	driver.findElement(By.id("userPassword")).click();
	driver.findElement(By.id("userPassword")).clear();
	driver.findElement(By.id("userPassword")).sendKeys("123456");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	String Str3 = driver.getCurrentUrl();
	Assert.assertEquals(Str3, baseUrl);
	Thread.sleep(5000);
	driver.findElement(By.cssSelector("span.ng-binding")).click();	
	driver.findElement(By.linkText("Logout")).click();
	System.out.println(Str3);
	Thread.sleep(2000);    
  }

  @AfterTest
  public void tearDown() throws Exception {
	driver.quit();    
  }
	}
