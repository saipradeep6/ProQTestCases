package proq;
import java.io.FileReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import proq.ProQVendorTest;
import proq.EmailVerification;
import static org.junit.Assert.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProQEditVendorTest extends ProQVendorTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private SoftAssert softAssert = new SoftAssert();
  public static String vname;
       
  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    baseUrl = "http://newdev.proq-erp.com/ui#/login";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);     
  }

  @Test(priority = 0, description="Login with the valid credentials")
  public void Valid_Credentials() throws Exception {
	driver.get(baseUrl);
	
    driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).click();
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("proq123");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    System.out.println("Logged into ProQ Successfully");    
    driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/paneltool[@class='ng-scope'])[last()]")).click();    
 }
  
  @Test(priority = 1, description="Save the Vendor Info without phone number")
  public void Edit_Profile_Fail() throws Exception {	  
	driver.findElement(By.xpath("//input[@placeholder='Phone']")).clear();	          
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    vname = driver.findElement(By.xpath("//input[@placeholder='Vendor Name']")).getAttribute("value");
    System.out.println(vname);
    WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success"));
    Boolean ele1 = element1.isEnabled();
    softAssert.assertTrue(ele1);        
  } 
  
  @Test(priority = 2, description="Save the Vendor Info by entering all the fields")
  public void Edit_Profile_Pass() throws Exception {
	Random randomnum = new Random();
	int num = randomnum.nextInt(1000000000);
	driver.findElement(By.xpath("//input[@placeholder='Phone']")).sendKeys("0"+num);		     
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(1000); 
    WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success"));
    Boolean ele1 = element1.isEnabled();        
    softAssert.assertTrue(ele1);
    System.out.println(element1.getText());                
  } 
 
  @Test(priority = 3, description="Add the Vendor Admin by giving only first and last names")
  public void Vendor_Users_Fail() throws Exception {
	
	driver.findElement(By.xpath("//div[@class='ng-scope']/ul/li[2]/a[text()='Admin']")).click();	
	Thread.sleep(1000);	
	driver.findElement(By.linkText("Add Vendor Admin")).click();
	Thread.sleep(1000);
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader(
            "D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    	String firstname = (String) jsonObject.get("FirstName");
    	String lastname = (String) jsonObject.get("LastName");
    	String role = (String) jsonObject.get("Role");    	
    	if(role.equals("Vendor Admin")) {
    		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstname);
    		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastname);
    		Thread.sleep(1000);
    	}
    }	
	driver.findElement(By.xpath("//button[@class='btn btn-round btn-success' and @type='submit']")).click();
	WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele1 = element1.isEnabled();
    softAssert.assertTrue(ele1);	
  }
  
  @Test(priority = 4, description="Add the Vendor Admin by entering all the fields")
  public void Vendor_Users_Pass() throws Exception {
	Random randomnum = new Random();		
	int num2 = randomnum.nextInt(1000000000);
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader(
            "D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);    	
    	String role = (String) jsonObject.get("Role");
    	String email = (String) jsonObject.get("Email");
    	if(role.equals("Vendor Admin")) {
    		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);    		
    		Thread.sleep(1000);
    	}
    }
	driver.findElement(By.xpath("//input[@placeholder='Phone']")).sendKeys("0"+num2);	
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele1 = element1.isEnabled();
    softAssert.assertTrue(ele1);
    System.out.println(element1.getText());    
    Thread.sleep(15000);
  }	
  
  @Test(priority = 5, description="Verify the Vendor Admin email")
  public void email_check() throws Exception {
	String Parent_Window = driver.getWindowHandle();
	String host = "pop-mail.outlook.com";// change accordingly
    String storeType = "pop3";
    String user = "saipradeep6@live.com";// change accordingly
    String password = "harika12345";// change accordingly    
    EmailVerification.check(host, storeType, user, password);
    Thread.sleep(1000);
    driver.switchTo().window(Parent_Window);
    driver.navigate().refresh();
    Thread.sleep(3000);
    WebElement element2 = driver.findElement(By.xpath("//i[@tooltip='Verified']")); 
	Boolean ele2 = element2.isDisplayed();
	softAssert.assertTrue(ele2);    
  }  
 
  @Test(priority = 6, description="Testing the Vendor Details")
  public void Vendor_Details() throws Exception {
	
	driver.findElement(By.linkText("Project Profile")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr/td[text()='Primary Service Provider']")).click();
	driver.findElement(By.xpath("//label[contains(text(),'Primary Service Provider')]/button[@type='button']")).click();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@type='text' and @placeholder='Enter Value']")).sendKeys("CMS");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='CSC']]/i[@class='fa fa-pencil']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='CSC']]/input[@type='text']")).clear();
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='CSC']]/input[@type='text']")).sendKeys("Computer Science Corporation");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='CSC']]/button[@class='btn btn-xs btn-success adjust']")).click();
	driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	Thread.sleep(2000);	
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr/td[text()='Project Type']")).click();
	driver.findElement(By.xpath("//label[contains(text(),'Project Type')]/button[@type='button']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@type='text' and @placeholder='Enter Value']")).sendKeys("Instrumentation");
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Other']]/i[@class='fa fa-pencil']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Other']]/input[@type='text']")).clear();
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Other']]/input[@type='text']")).sendKeys("Deployment");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Other']]/button[@class='btn btn-xs btn-success adjust']")).click();
	
	driver.findElement(By.cssSelector("button.btn.btn-primary")).click();	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr/td[text()='Approx Total Project Budget for Services']")).click();
	driver.findElement(By.xpath("//label[contains(text(),'Approx Total Project Budget for Services')]/button[@type='button']")).click();	
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@type='text' and @placeholder='Enter Value']")).sendKeys("Big $10M - $15M");
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Minor $100K - $1M']]/i[@class='fa fa-pencil']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Minor $100K - $1M']]/input[@type='text']")).clear();
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Minor $100K - $1M']]/input[@type='text']")).sendKeys("Minor $500K - $3M");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Minor $100K - $1M']]/button[@class='btn btn-xs btn-success adjust']")).click();
	
	driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	Thread.sleep(2000);	
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr/td[text()='Industry']")).click();
	driver.findElement(By.xpath("//label[contains(text(),'Industry')]/button[@type='button']")).click();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@type='text' and @placeholder='Enter Value']")).sendKeys("Advertising");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@class='btn btn-xs btn-success adjust']")).click();
	
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Animation']]/i[@class='fa fa-pencil']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Animation']]/input[@type='text']")).clear();
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Animation']]/input[@type='text']")).sendKeys("Multimedia");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//tr[@class='ng-scope']/td[span[text()='Animation']]/button[@class='btn btn-xs btn-success adjust']")).click();
	
	driver.findElement(By.cssSelector("button.btn.btn-primary")).click();	
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
