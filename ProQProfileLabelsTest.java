package proq;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProQProfileLabelsTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private SoftAssert softAssert = new SoftAssert();
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		baseUrl = "http://newdev.proq-erp.com/ui#/login";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	@Test(priority = 0, description="Login with the Valid Credentials")
	public void Valid_Credentials() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("userEmail")).clear();
		driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
		driver.findElement(By.id("userPassword")).click();
		driver.findElement(By.id("userPassword")).clear();
		driver.findElement(By.id("userPassword")).sendKeys("proq123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Logged into ProQ Successfully");
		Thread.sleep(3000);
	}
	
	@Test(priority = 1, description="Save with empty Project Profile Label")
	public void Empty_Label() throws Exception {		
		driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/paneltool[@class='ng-scope'])[last()]")).click();
		driver.findElement(By.linkText("Project Profile Labels")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-xs space']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='col-sm-12']/table/tbody/tr[2]/td[2]/input[@type='text']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-round btn-success pull-right']")).click();
		Thread.sleep(1000);
		WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
		Boolean ele1 = element1.isEnabled();
		softAssert.assertTrue(ele1);
	}	
		
	@Test(priority = 2, description="Edit the Project Profile Labels")
	public void Profile_Labels() throws Exception {	
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		driver.findElement(By.xpath("//div[@class='col-sm-12']/table/tbody/tr[2]/td[2]/input[@type='text']")).sendKeys("Secondary Service Provider");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='col-sm-12']/table/tbody/tr[8]/td[2]/input[@type='text']")).clear();
		driver.findElement(By.xpath("//div[@class='col-sm-12']/table/tbody/tr[8]/td[2]/input[@type='text']")).sendKeys("Total Budget for the Project");
		Thread.sleep(1000);
		jse.executeScript("scroll(0, 400)");
		driver.findElement(By.xpath("//div[@class='col-sm-12']/table/tbody/tr[12]/td[2]/input[@type='text']")).clear();
		driver.findElement(By.xpath("//div[@class='col-sm-12']/table/tbody/tr[12]/td[2]/input[@type='text']")).sendKeys("Line of Business");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-round btn-success pull-right']")).click();
		Thread.sleep(1000);	
		jse.executeScript("scroll(400, 0)");
		WebElement element2 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
		Boolean ele2 = element2.isEnabled();
		assertTrue(ele2);
		System.out.println(element2.getText());
	}
	
	@Test(priority = 3, description="Check the edited Profile Labels in Project Profile")
	public void ProjectProfile_Hierarchical() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		driver.findElement(By.linkText("Vendors")).click();
		driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/a[@tooltip='View Projects'])[last()]")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'Add Project')]")).click();
		WebElement element2 = driver.findElement(By.xpath("//label[text()='Secondary Service Provider : ']"));
		Boolean ele2 = element2.isEnabled();
		Thread.sleep(2000);
		js.executeScript("scroll(0, 250)");
		WebElement element3 = driver.findElement(By.xpath("//label[text()='Total Budget for the Project : ']"));
		Boolean ele3 = element3.isEnabled();
		WebElement element4 = driver.findElement(By.xpath("//label[text()='Line of Business : ']"));
		Boolean ele4 = element4.isEnabled();		
		assertTrue(ele2&ele3&ele4);
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