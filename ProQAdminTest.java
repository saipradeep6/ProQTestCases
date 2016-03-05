package proq;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import proq.randomchar;
import proq.AdminVerify;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ProQAdminTest {

	private WebDriver driver;
	private String baseUrl = "http://newdev.proq-erp.com/ui#";
	private StringBuffer verificationErrors = new StringBuffer();
	private SoftAssert softAssert = new SoftAssert();
	public static String Admin;
	public static String Handle;

	   	
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
	   driver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 sidebar hidden-xs']/div/nav/ul/li[1]/ul/li[1]/a/span[text()='ProQ Admins']")).click();
	   Thread.sleep(1000);  
	}
	
	@Test(priority = 1, description="Save the ProQ Admin by entering only first and last name")
	public void Admin_Details_1() throws Exception {
		randomchar rc = new randomchar();
		String str1 = rc.generateRandomString();
		Handle= driver.getWindowHandle();
		driver.findElement(By.xpath("//button[@class='pull-right btn btn-link btn-sm ng-scope']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Admin");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(str1);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	    Boolean ele1 = element1.isEnabled();
	    softAssert.assertTrue(ele1);
	 	Thread.sleep(1000);
	}
	
	@Test(priority = 2, description="Login with the valid credentials")
	public void Admin_Details_2() throws Exception {
		Random randomnum = new Random();
		int num = randomnum.nextInt(1000);
		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("saipradeep6+"+num+"@live.com");
		Thread.sleep(1000);
		Admin = driver.findElement(By.xpath("//input[@placeholder='Email']")).getAttribute("value");
		int num2 = randomnum.nextInt(1000000000);
		driver.findElement(By.xpath("//input[@placeholder='Phone']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Phone']")).sendKeys("0"+num2);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		WebElement element2 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	    Boolean ele2 = element2.isEnabled();
	    assertTrue(ele2);
	 	Thread.sleep(15000);
	}
	
	@Test(priority = 3, description="Verify the Project Admin email")
	  public void email_check() throws Exception {
		String host = "pop-mail.outlook.com";// change accordingly
	    String storeType = "pop3";
	    String user = "saipradeep6@live.com";// change accordingly
	    String password = "harika12345";// change accordingly    
	    AdminVerify.verify(host, storeType, user, password);
	    Thread.sleep(1000); 
	    driver.navigate().refresh();
		Thread.sleep(3000);		
		WebElement element3 = driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[span[text()='"+Admin+"']]/i"));
		Boolean ele3 = element3.isDisplayed();
		assertTrue(ele3);
		Thread.sleep(3000);
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
