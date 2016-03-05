package proq;

import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ProQViewersTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private SoftAssert softAssert = new SoftAssert();
  public static String proname;
  public static String[] roles = new String[3];
  
  @BeforeTest
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "D:/ProjectWork/Selenium/chromedriver.exe");
	driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseUrl = "http://newdev.proq-erp.com/ui#/login";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test(priority = 0, description="Login with the valid credentials")
  public void testProfile() throws Exception {
    driver.get(baseUrl);
    JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader(
            "D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);    	    	
    	String role = (String) jsonObject.get("Role");
    	String email = (String) jsonObject.get("Email");
    	if(role.equals("Project Admin")) {
    		driver.findElement(By.id("userEmail")).clear();
    	    driver.findElement(By.id("userEmail")).sendKeys(email);
    	}
    }    
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("1234");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(1000);
 /*
    driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/a[@tooltip='View Projects'])[last()]")).click();
    Thread.sleep(1000);  */
    driver.findElement(By.xpath("(//div[@class='card-header']/span[@class='']/a)[last()]")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 sidebar hidden-xs']/div/nav[@role='navigation']/ul/li[4]/ul/li/a/span[text()='Viewers']")).click();
    Thread.sleep(3000);    
  }
    
  @Test(priority = 1, description="Enter the Client Role")
  public void Role_Email() throws Exception {	
	WebElement proj = driver.findElement(By.xpath("//span[@class='badge project-name ng-binding']"));
	proname = proj.getText();
	System.out.println("Project Name is: " +proname);
	driver.findElement(By.xpath("//button[@class='btn btn-info btn-xs margin-right']")).click();
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader("D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    	String firstname = (String) jsonObject.get("FirstName");
    	String lastname = (String) jsonObject.get("LastName");
    	String role = (String) jsonObject.get("Role");
    	String email = (String) jsonObject.get("Email");
    	List<WebElement> rows = driver.findElements(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr")); 
		int j = rows.size();
		System.out.println("Total number of rows :"+ j);
    	if(role.equals("ClientViewer1") && j > 0) {
    		WebElement crole2 = driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[1]/input[@placeholder='Enter Role']"));
    		crole2.sendKeys("Team Manager");
    		Thread.sleep(1000);	
    		roles[0] = crole2.getAttribute("value");
    		System.out.println("Role is: " +roles[0]);
    		driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[4]/input[@placeholder='Enter Valid E-mail id']")).clear();
    		driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[4]/input[@placeholder='Enter Valid E-mail id']")).sendKeys(email);
    		Thread.sleep(1000);
    		driver.findElement(By.xpath("//button[@type='submit' and text()='Save']")).click();    			
    		driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[2]/input[@placeholder='Enter First Name']")).clear();
    		driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[2]/input[@placeholder='Enter First Name']")).sendKeys(firstname);
    		Thread.sleep(1000);
    		driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[3]/input[@placeholder='Enter Last Name']")).clear();
    		driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[3]/input[@placeholder='Enter Last Name']")).sendKeys(lastname);
    		Thread.sleep(1000);
    	}
    }	
	    driver.findElement(By.xpath("//button[@type='submit' and text()='Save']")).click();
	    WebElement element2 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	    Boolean ele2 = element2.isEnabled();
	    softAssert.assertTrue(ele2);
	    System.out.println(ele2);
	    Thread.sleep(2000);
	}   
  
  @Test(priority = 2, description="Enter the Service Provider Role")
  public void SP_Role() throws Exception {	
	driver.findElement(By.xpath("//div[1]/div/nav/ul/li[4]/ul/li[3]/a/span")).click();
	Thread.sleep(1000);	
	new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("SP");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@class='btn btn-info btn-xs margin-right']")).click();
    JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader("D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    	String firstname = (String) jsonObject.get("FirstName");
    	String lastname = (String) jsonObject.get("LastName");
    	String role = (String) jsonObject.get("Role");
    	String email = (String) jsonObject.get("Email");
    	List<WebElement> rows = driver.findElements(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr")); 
		int j = rows.size();
		System.out.println("Total number of rows :"+ j);
    	if(role.equals("SPViewer1") && j > 0) {
    		WebElement sprole2 = driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[1]/input[@placeholder='Enter Role']"));
    	    sprole2.sendKeys("GM");
    	    Thread.sleep(1000);
    	    roles[1] = sprole2.getAttribute("value");
    	    driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[2]/input[@placeholder='Enter First Name']")).clear();
    	    driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[2]/input[@placeholder='Enter First Name']")).sendKeys(firstname);
    	    Thread.sleep(1000);
    	    driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[3]/input[@placeholder='Enter Last Name']")).clear();
    	    driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[3]/input[@placeholder='Enter Last Name']")).sendKeys(lastname);
    	    Thread.sleep(1000);
    	    driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[4]/input[@placeholder='Enter Valid E-mail id']")).clear();    
    	    driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr["+j+"]/td[4]/input[@placeholder='Enter Valid E-mail id']")).sendKeys(email);
    	    Thread.sleep(1000);    			
    	}
    }    
        driver.findElement(By.xpath("//button[@type='submit' and text()='Save']")).click();
        Thread.sleep(1000);
        WebElement element3 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
        Boolean ele3 = element3.isEnabled();
        assertTrue(ele3);
        System.out.println(ele3);
        Thread.sleep(15000);        
  }
  
  @Test(priority = 3, description="Verify the Viewer Email")
  public void email_check() throws Exception {
	String Parent_Window = driver.getWindowHandle();
	String host = "pop-mail.outlook.com";// change accordingly
    String storeType = "pop3";
    String user = "saipradeep6@live.com";// change accordingly
    String password = "harika12345";// change accordingly    
    ViewerVerify.viewverify(host, storeType, user, password);
    driver.switchTo().window(Parent_Window);    
	driver.navigate().refresh();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 sidebar hidden-xs']/div/nav[@role='navigation']/ul/li[4]/ul/li/a/span[text()='Viewers']")).click();
	Thread.sleep(1000);
	WebElement element1 = driver.findElement(By.xpath("//div[h4[contains(text(),'Client Roles')]]/div[@class='scrollit']/table/tbody/tr/td/button[@tooltip='Verified']"));
	WebElement element2 = driver.findElement(By.xpath("//div[h4[contains(text(),'Service Provider Roles')]]/div[@class='scrollit']/table/tbody/tr/td/button[@tooltip='Verified']"));
	Boolean ele1 = element1.isDisplayed();
	Boolean ele2 = element2.isDisplayed();
	Boolean ele3 = ele1 & ele2;
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

