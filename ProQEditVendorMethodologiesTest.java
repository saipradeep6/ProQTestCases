package proq;
import java.util.concurrent.TimeUnit;
import proq.randomchar;
import static org.junit.Assert.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ProQEditVendorMethodologiesTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  randomchar rc = new randomchar();
  String str1 = rc.generateRandomString();
  String Methodology = "Methodology "+str1;
  String str2 = rc.generateRandomString();
  String Phase = "Phase "+str2;
  
  @BeforeTest
  public void setUp() throws Exception {
	driver = new FirefoxDriver();
//	System.setProperty("webdriver.chrome.driver", "D:/ProjectWork/Selenium/chromedriver.exe");
//	driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseUrl = "http://newdev.proq-erp.com/ui#/login";
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  @Test(priority = 0, description="Login with the valid credentials")
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
    
    driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/paneltool[@class='ng-scope'])[last()]")).click();    
 }
  
  @Test(priority = 1, description="Add the New Methodology")
  public void New_Methodology() throws Exception {
	driver.findElement(By.linkText("Methodologies")).click();
    driver.findElement(By.xpath("//button[@class='btn btn-info btn-xs buttonadjust'][i[@tooltip='Enter New Methodology']]")).click();
	driver.findElement(By.xpath("//input[@placeholder='Enter New Methodology']")).clear();
	
	driver.findElement(By.xpath("//input[@placeholder='Enter New Methodology']")).sendKeys(Methodology);
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	System.out.println("New Methodology has been added successfully");
	Thread.sleep(3000);
  }

  @Test(priority = 2, description="Add the New Phase")
  public void New_Phase() throws Exception {
	driver.findElement(By.xpath("//div[label[text()='Add New Phase']]/button[@type='button']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Enter New Phase']")).clear();	
	driver.findElement(By.xpath("//input[@placeholder='Enter New Phase']")).sendKeys(Phase);
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	driver.findElement(By.xpath("//div[@class='pull-right ng-scope']/button[text()='Save' and @type='button']")).click();
	Thread.sleep(3000);	
	WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele1 = element1.isEnabled();
	assertTrue(ele1);
	System.out.println(element1.getText());
  }
	
  @Test(priority = 3, description="Add the New Sub-Phase")
  public void New_Subphase() throws Exception {
	randomchar rc = new randomchar();
	String str3 = rc.generateRandomString();
	driver.findElement(By.xpath("//div[strong[contains(text(),'"+Phase+"')]]/button[@type='button']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Enter New SubPhase' and @class='ng-pristine ng-valid']")).clear();
	driver.findElement(By.xpath("//input[@placeholder='Enter New SubPhase' and @class='ng-pristine ng-valid']")).sendKeys("Sub-phase "+str3);
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	
	String str4 = rc.generateRandomString();
	driver.findElement(By.xpath("//div[strong[contains(text(),'"+Phase+"')]]/button[@type='button']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Enter New SubPhase' and @class='ng-valid ng-dirty']")).clear();
	driver.findElement(By.xpath("//input[@placeholder='Enter New SubPhase' and @class='ng-valid ng-dirty']")).sendKeys("Sub-phase "+str4);
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);  
	
	driver.findElement(By.xpath("//div[@class='pull-right ng-scope']/button[text()='Save' and @type='button']")).click();
	Thread.sleep(3000);
	WebElement element2 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele2 = element2.isEnabled();
	assertTrue(ele2);
	System.out.println(element2.getText());	
  }

  @Test(priority = 4, description="Check the New Methodology in Project Profile")
  public void Frame_Test() throws Exception {
	driver.findElement(By.linkText("Vendors")).click();
    driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/a[@tooltip='View Projects'])[last()]")).click();
    driver.findElement(By.xpath("//button[contains(text(), 'Add Project')]")).click();
    Thread.sleep(1000);
    
    WebElement element1 = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.methodology']"));
    element1.click();
    Select mySelect = new Select(element1);
    mySelect.selectByVisibleText("Methodology "+str1);
    WebElement option = mySelect.getFirstSelectedOption();
    System.out.println(option.getText());
    Boolean ele3 = option.isEnabled();
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
