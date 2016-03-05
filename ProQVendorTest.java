package proq;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import proq.randomchar;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProQVendorTest {
  private WebDriver driver;
  private String baseUrl = "http://newdev.proq-erp.com/ui#/login";
  private StringBuffer verificationErrors = new StringBuffer();
  private SoftAssert softAssert = new SoftAssert();
  public static String Vendor;

   	
  @BeforeTest(description="Launches the ProQ-ERP site")
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test(priority = 0, description="Login with the valid credentials")
  public void Valid_Credentials() throws Exception {
	WebDriverWait wait = new WebDriverWait(driver, 10);
	driver.get(baseUrl);
    driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).click();
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("proq123");
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    System.out.println("Logged into ProQ Successfully");
    driver.findElement(By.xpath("//button[@class='btn btn-link btn-sm']")).click();
    Thread.sleep(1000);  
  }
  
  @Test(priority = 1, description="Save the vendor by entering only the Vendor Name and keep the remaining fields blank")
  public void Vendor_Name_Only() throws Exception {	
	randomchar rc = new randomchar();
	String str1 = rc.generateRandomString();
	driver.findElement(By.xpath("//input[@placeholder='Vendor Name']")).sendKeys("Sample Vendor Z"+str1);	
	Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele1 = element1.isEnabled();
    try {
		softAssert.assertTrue(ele1);
		System.out.println(element1.getText());
		Thread.sleep(5000);
	}
	catch(Throwable e) {
		System.out.println("Exception is: "+e);
	}	
 	Thread.sleep(1000);
  }	
    
  @Test(priority = 2, description="Save the vendor process by entering only the Vendor Name and URL")
  public void URL_Phone() throws Exception {
	String str2 = driver.findElement(By.xpath("//input[@placeholder='Vendor Name']")).getAttribute("value");
	Vendor = str2;
	str2 = str2.replaceAll("\\s+","");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@placeholder='Website']")).sendKeys("http://"+str2.toLowerCase()+".com");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	WebElement element2 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele2 = element2.isEnabled();
	try {
		softAssert.assertTrue(ele2);
		System.out.println(element2.getText());
		Thread.sleep(5000);
	}
	catch(Throwable e) {
		System.out.println("Exception is: "+e);
	}	
  }
  
  @Test(priority = 3, description="Save the vendor process by entering all the fields")
  public void URL_Address() throws Exception {
 
	Random randomnum = new Random();
	int num = randomnum.nextInt(1000000000);
	driver.findElement(By.xpath("//input[@placeholder='Phone']")).sendKeys("0"+num);
	driver.findElement(By.xpath("//textarea[@placeholder='Address']")).sendKeys("Jubilee Hills\nHyderabad");
	Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    WebElement element3 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele3 = element3.isEnabled();
	try {
		assertTrue(ele3);
		System.out.println(element3.getText());
		Thread.sleep(5000);
	}
	catch(Throwable e) {
		System.out.println("Exception is: "+e);
	}
           
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
