package proq;

import java.util.concurrent.TimeUnit;
import proq.randomchar;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import proq.ProAdminVerify;

import java.io.FileReader;
import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ProQProfileTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private SoftAssert softAssert = new SoftAssert();
  public static String protitle;
  
  @BeforeTest
  public void setUp() throws Exception {   
    System.setProperty("webdriver.chrome.driver", "D:/ProjectWork/Selenium/chromedriver.exe");
	driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseUrl = "http://newdev.proq-erp.com/ui#/login";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test(priority = 1, description="Login with the Valid Credentials")
  public void Valid_Credentials() throws Exception {	
    driver.get(baseUrl);
    JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader(
            "D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);    	
    	String role = (String) jsonObject.get("Role");
    	String email = (String) jsonObject.get("Email");
    	if(role.equals("Vendor Admin")) {
    		driver.findElement(By.id("userEmail")).clear();
    		driver.findElement(By.id("userEmail")).sendKeys(email);    		
    		Thread.sleep(1000);
    	}
    }    
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("1234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(1000);    
    driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/a[@tooltip='View Projects'])[last()]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[contains(text(), 'Add Project')]")).click();
    Thread.sleep(1000);
  }
  
  @Test(priority = 2, description="Save the project profile by entering only the Client Name and Project Title")
  public void Client_Name() throws Exception {    
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	randomchar rc = new randomchar();
	String str1 = rc.generateRandomString();
    driver.findElement(By.xpath("//input[@placeholder='Client Name']")).clear();
    driver.findElement(By.xpath("//input[@placeholder='Client Name']")).sendKeys("Client "+str1);
    Thread.sleep(1000);
    String str2 = rc.generateRandomString();
	driver.findElement(By.xpath("//input[@placeholder='Project Title']")).clear();
    driver.findElement(By.xpath("//input[@placeholder='Project Title']")).sendKeys("Sample Project Z"+str2);
    Thread.sleep(1000);  
    jse.executeScript("scroll(0, 400)");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele1 = element1.isEnabled();
    softAssert.assertTrue(ele1);
  }
  
  @Test(priority = 3, description="Save the project profile by entering all the fields")
  public void ProjectTitle_AdminName() throws Exception {    
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("scroll(400, 0)");	
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader(
            "D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    	String firstname = (String) jsonObject.get("FirstName");    	
    	String role = (String) jsonObject.get("Role");
    	String email = (String) jsonObject.get("Email");
    	if(role.equals("Project Admin")) {
    		driver.findElement(By.xpath("//input[@placeholder='Project admin name']")).clear();
    	    driver.findElement(By.xpath("//input[@placeholder='Project admin name']")).sendKeys(firstname);
    	    Thread.sleep(1000);
    	    driver.findElement(By.xpath("//input[@placeholder='Project admin email']")).clear();
    	    driver.findElement(By.xpath("//input[@placeholder='Project admin email']")).sendKeys(email);
    	    Thread.sleep(1000);
    	}
    }    
    protitle = driver.findElement(By.xpath("//input[@placeholder='Project Title']")).getAttribute("value");
    System.out.println("Project Admin is: " +protitle);
    
    WebElement pt = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.projectType']"));  
    new Select(pt).selectByVisibleText("Implementation");
    Thread.sleep(1000);
    
    jse.executeScript("scroll(300, 0)");
    WebElement psp = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.si.name']"));  
    new Select(psp).selectByVisibleText("Cognizant");
    Thread.sleep(1000);
    
    jse.executeScript("scroll(300, 0)");
    WebElement scp = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.subContractor']"));  
    new Select(scp).selectByVisibleText("HP");
    Thread.sleep(1000); 
    
    jse.executeScript("scroll(300, 0)");
    WebElement hpip = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.hostingPartner']")); 
    new Select(hpip).selectByVisibleText("IBM");
    Thread.sleep(1000);
    
    jse.executeScript("scroll(0, 300)");
    WebElement ps = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.projectSites']"));
    new Select(ps).selectByVisibleText("Multi-site");
    Thread.sleep(1000);
    
    WebElement cs = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.country']"));
    new Select(cs).selectByIndex(1);
    Thread.sleep(1000);
    
    WebElement em = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.methodology']"));
    new Select(em).selectByIndex(0);
    Thread.sleep(1000);
    
    WebElement st = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.scheduleType']"));
    new Select(st).selectByIndex(1);
    Thread.sleep(1000);
    
    WebElement budget = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.approxBudget']"));
    new Select(budget).selectByIndex(1);
    Thread.sleep(1000);
    
    WebElement pm = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.siPricingMethod']"));
    new Select(pm).selectByVisibleText("Fixed Fee");
    Thread.sleep(1000);
    
    WebElement car = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.clientAnnualRevenue']"));
    new Select(car).selectByVisibleText("$300M - $1B");
    Thread.sleep(1000);
    
    WebElement svp = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.sapProduct']"));
    new Select(svp).selectByIndex(0);
    Thread.sleep(1000);
    
    WebElement spsv = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.vendorAlsoSI']"));
    new Select(spsv).selectByIndex(0);
    Thread.sleep(1000);
    
    WebElement ind= driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.industry']"));
    new Select(ind).selectByVisibleText("Hospitality");
    Thread.sleep(1000);
    
    WebElement kpr = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.keyProjectRegion']"));
    new Select(kpr).selectByIndex(1);
    Thread.sleep(1000);
    
    WebElement kar = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.keyProjectRegion2']"));
    if (kar.isDisplayed() == true) {
    new Select(kar).selectByIndex(0);
    Thread.sleep(1000);
    }
   
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    WebElement element3 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele3 = element3.isEnabled();
    assertTrue(ele3);
    Thread.sleep(1000);
  }
  
  @Test(priority = 4, description="Verify the Project Admin email")
  public void email_check() throws Exception {
	String Parent_Window = driver.getWindowHandle();
	String host = "pop-mail.outlook.com";// change accordingly
    String storeType = "pop3";
    String user = "saipradeep6@live.com";// change accordingly
    String password = "harika12345";// change accordingly    
    ProAdminVerify.verify(host, storeType, user, password);
    Thread.sleep(1000);    
    driver.switchTo().window(Parent_Window);
	driver.navigate().refresh();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 sidebar hidden-xs']/div/nav[@role='navigation']/ul/li[4]/ul/li/a/span[text()='Profile']"));
	Thread.sleep(3000);
	WebElement element = driver.findElement(By.xpath("//i[@tooltip='Verified']"));
	Boolean ele1 = element.isDisplayed();
	assertTrue(ele1);
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

