package proq;

import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import proq.RespondentVerify;
import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProQRespondentsTest {
  private WebDriver driver;
  private String baseUrl = "http://newdev.proq-erp.com/ui#/login";;
  private StringBuffer verificationErrors = new StringBuffer();
  private SoftAssert softAssert = new SoftAssert();
  public static String proname;
  public static String[] roles = new String[8];
  
  @BeforeTest
  public void setUp() throws Exception {	
	System.setProperty("webdriver.chrome.driver", "D:/ProjectWork/Selenium/chromedriver.exe");
	driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test (priority = 0)
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
    driver.findElement(By.xpath("(//div[@class='card-header']/span[1]/a)[last()]")).click();    
    driver.findElement(By.linkText("Respondents")).click();
    Thread.sleep(1000);
  }
  
  @Test(priority = 1, description = "Save the page by giving only Client Sponsor field")
  public void clientSponsor1() throws Exception {  
	WebElement proj = driver.findElement(By.xpath("//span[@class='badge project-name ng-binding']"));
	proname = proj.getText();
	System.out.println("Project Name is: " +proname);
	WebElement crole1 = driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[@class='ng-scope ng-pristine ng-invalid ng-invalid-required']/td[@class='size ng-binding'][contains(text(), 'Client Sponsor')]"));
	roles[0] = crole1.getText();
	System.out.println("Role is: " +roles[0]);
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader("D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    	String firstname = (String) jsonObject.get("FirstName");
    	String lastname = (String) jsonObject.get("LastName");
    	String role = (String) jsonObject.get("Role");
    	String email = (String) jsonObject.get("Email");
    	if(role.equals("Respondent1")) {
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[2]/input[@placeholder='Enter First Name']")).clear();
    	    driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[2]/input[@placeholder='Enter First Name']")).sendKeys(firstname);
    		Thread.sleep(1000);
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[3]/input[@placeholder='Enter Last Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[3]/input[@placeholder='Enter Last Name']")).sendKeys(lastname);
    		Thread.sleep(1000);
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[4]/input[@placeholder='Enter Valid E-mail id']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[4]/input[@placeholder='Enter Valid E-mail id']")).sendKeys(email);    		
    		Thread.sleep(1000);
    	}
    }			
	driver.findElement(By.xpath("//button[@type='submit' and text()='Save']")).click();
	WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele1 = element1.isEnabled();
	softAssert.assertTrue(ele1);
	System.out.println(ele1);
  }
  
  @Test(priority = 2, description = "Save the page by giving only Client Roles")
  public void clientSponsor2() throws Exception {		
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader("D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    	String firstname = (String) jsonObject.get("FirstName");
    	String lastname = (String) jsonObject.get("LastName");
    	String role = (String) jsonObject.get("Role");    	
    	if(role.equals("Respondent2")) {
    		WebElement crole2 = driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[@class='ng-scope ng-pristine ng-invalid ng-invalid-required']/td[@class='size ng-binding'][contains(text(), 'Project Manager')]"));
    		roles[1] = crole2.getText();
    		System.out.println("Role is: " +roles[1]);
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[2]/input[@placeholder='Enter First Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[2]/input[@placeholder='Enter First Name']")).sendKeys(firstname);
    		
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[3]/input[@placeholder='Enter Last Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[3]/input[@placeholder='Enter Last Name']")).sendKeys(lastname);
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[4]/input[@placeholder='Enter Valid E-mail id']")).click();
    		Thread.sleep(1000);
    	}
    	
    	else if(role.equals("Respondent3")) {
    		WebElement crole3 = driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[@class='ng-scope ng-pristine ng-invalid ng-invalid-required']/td[@class='size ng-binding'][contains(text(), 'Technical Lead')]"));
    		roles[2] = crole3.getText();
    		System.out.println("Role is: " +roles[2]);
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[2]/input[@placeholder='Enter First Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[2]/input[@placeholder='Enter First Name']")).sendKeys(firstname);
    		
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[3]/input[@placeholder='Enter Last Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[3]/input[@placeholder='Enter Last Name']")).sendKeys(lastname);	
    		Thread.sleep(1000); 
    	}
    	
    	else if(role.equals("Respondent4")) {
    		WebElement crole4 = driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[@class='ng-scope ng-pristine ng-invalid ng-invalid-required']/td[@class='size ng-binding'][contains(text(), 'Business Lead')]"));
    		roles[3] = crole4.getText();
    		System.out.println("Role is: " +roles[3]);
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[4]/td[2]/input[@placeholder='Enter First Name']")).sendKeys(firstname);
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[4]/td[3]/input[@placeholder='Enter Last Name']")).sendKeys(lastname);
    		Thread.sleep(1000);
    	}
    }	
	driver.findElement(By.xpath("//button[@type='submit' and text()='Save']")).click();
	WebElement element2 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele2 = element2.isEnabled();
	softAssert.assertTrue(ele2);
	System.out.println(ele2);
  }
  
  @Test(priority = 3, description = "Save the page by entering all the Client and SP roles")
  public void serviceProvider() throws Exception {	
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(new FileReader("D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json"));
    JSONArray jsonArray = (JSONArray) obj;    
    for(int i=0; i<jsonArray.size(); i++) {
    	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
    	String firstname = (String) jsonObject.get("FirstName");
    	String lastname = (String) jsonObject.get("LastName");
    	String role = (String) jsonObject.get("Role");    	
    	if(role.equals("Respondent5")) {
    		WebElement crole5 = driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[@class='ng-scope']/td[@class='size ng-binding'][contains(text(), 'Engagement Manager')]"));
    		roles[4] = crole5.getText();
    		System.out.println("Role is: " +roles[4]);
    		
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[2]/input[@placeholder='Enter first Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[2]/input[@placeholder='Enter first Name']")).sendKeys(firstname);
    		
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[3]/input[@placeholder='Enter last name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[1]/td[3]/input[@placeholder='Enter last name']")).sendKeys(lastname);
    		Thread.sleep(1000);
    	}
		
    	else if(role.equals("Respondent6")) {
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[2]/input[@placeholder='Enter first Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[2]/input[@placeholder='Enter first Name']")).sendKeys(firstname);
    		
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[3]/input[@placeholder='Enter last name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[2]/td[3]/input[@placeholder='Enter last name']")).sendKeys(lastname);	
    		Thread.sleep(1000);	
    	}
	
    	else if(role.equals("Respondent7")) {
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[2]/input[@placeholder='Enter first Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[2]/input[@placeholder='Enter first Name']")).sendKeys(firstname);
    		
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[3]/input[@placeholder='Enter last name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[3]/td[3]/input[@placeholder='Enter last name']")).sendKeys(lastname);	
    		Thread.sleep(1000);	
    	}
	
    	else if(role.equals("Respondent8")) {
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[4]/td[2]/input[@placeholder='Enter first Name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[4]/td[2]/input[@placeholder='Enter first Name']")).sendKeys(firstname);
    		
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[4]/td[3]/input[@placeholder='Enter last name']")).clear();
    		driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr[4]/td[3]/input[@placeholder='Enter last name']")).sendKeys(lastname);
    	}
    }
	driver.findElement(By.xpath("//button[@type='submit' and text()='Save']")).click();
	Thread.sleep(5000);
	WebElement element3 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele3 = element3.isEnabled();
	assertTrue(ele3);	
	System.out.println(element3.getText());	
	Thread.sleep(25000);
 }
  
  @Test(priority = 4, description="Verify the Respondent emails")
  public void email_check() throws Exception {
	String Parent_Window = driver.getWindowHandle();
	String host = "pop-mail.outlook.com";// change accordingly
    String storeType = "pop3";
    String user = "saipradeep6@live.com";// change accordingly
    String password = "harika12345";// change accordingly    
    RespondentVerify.respverify(host, storeType, user, password);
    Thread.sleep(1000);
    driver.switchTo().window(Parent_Window);
  	driver.navigate().refresh();
	Thread.sleep(3000);	
	driver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 sidebar hidden-xs']/div/nav[@role='navigation']/ul/li[4]/ul/li/a/span[text()='Respondents']"));
	Thread.sleep(1000);
	for(int i=1; i<5; i++) {
		WebElement element = driver.findElement(By.xpath("//div[@class='scrollit']/table/tbody/tr["+i+"]/td[5]/i[@tooltip='Verified']"));
		Boolean ele1 = element.isDisplayed();
		softAssert.assertTrue(ele1);
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

