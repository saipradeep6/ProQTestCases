package proq;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import proq.PulseVerify;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.support.ui.Select;

public class ProQFrameTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  public static String proname;
  
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
    driver.findElement(By.id("userEmail")).clear();
    driver.findElement(By.id("userEmail")).sendKeys("demo@proq-erp.com");
    driver.findElement(By.id("userPassword")).clear();
    driver.findElement(By.id("userPassword")).sendKeys("proq123");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(1000);
 
    driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/a[@tooltip='View Projects'])[last()]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("(//div[@class='card-header']/span[1]/a)[last()]")).click();
//    driver.findElement(By.xpath("//div[1]/div/div[1]/span[1]/a")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Frame")).click();
    Thread.sleep(1000);
    
  }
    
  @Test(priority = 1, description="Starting the Sub-Phases")
  public void Start_Frame() throws Exception {		
	List<WebElement> tbody = driver.findElements(By.xpath("//div[@class='ng-scope']/table/tbody"));	
	System.out.println("Total number of rows :"+ tbody.size());
	int j = 2;
	for(int i = 1; i < tbody.size(); i++) {
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='ng-scope']/table/tbody["+i+"]/tr[2]/td/table/tbody/tr"));		
		System.out.println("Total number of rows :"+ rows.size());		
		if(j < rows.size()) {
			WebElement element1 = driver.findElement(By.xpath("//div[@class='ng-scope']/table/tbody["+i+"]/tr[2]/td/table/tbody/tr["+j+"]/td[2]/div/div[1]/button[text()='start']"));
			WebElement element2 = driver.findElement(By.xpath("//div[@class='ng-scope']/table/tbody["+i+"]/tr[2]/td/table/tbody/tr["+(j+1)+"]/td[2]/div/div[1]/button[text()='start']"));
			Boolean ele1 = element1.isDisplayed();
			Boolean ele2 = element2.isDisplayed();			
			if(ele1 == true){
				element1.click();
				driver.findElement(By.xpath("//button[@class='btn btn-primary'][contains(text(),'Confirm')]")).click();
				Thread.sleep(5000);
				break;
			}
			else if(ele2 == true) {
				element2.click();
				driver.findElement(By.xpath("//button[@class='btn btn-primary'][contains(text(),'Confirm')]")).click();
				Thread.sleep(5000);
				break;
			}			
		}	
	 }		
  }
  
  @Test(priority = 2, description="Starting the Pulse Check")
  public void Test_PulseCheck() throws Exception {
	driver.findElement(By.xpath("//div[@class='col-sm-3 col-md-2 sidebar hidden-xs']/div/nav[@role='navigation']/ul/li[4]/ul/li/a/span[text()='Pulse Check']")).click();
	Thread.sleep(2000);
	WebElement element4 = driver.findElement(By.xpath("//div/h4/button[@class='pull-right btn btn-link btn-sm' and @ng-click='psm.onAddWeek()']"));
	if(element4.isDisplayed() == true){
		element4.click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary'][contains(text(),'Confirm')]")).click();
		Thread.sleep(1000);
	}	
	driver.findElement(By.linkText("Start Pulse Check")).click();
	driver.findElement(By.xpath("//button[@class='btn btn-primary'][contains(text(),'Confirm')]")).click();
	Thread.sleep(1000);
	WebElement element5 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
	Boolean ele5 = element5.isEnabled();
	assertTrue(ele5);
	Thread.sleep(25000);
  }
  
  @Test(priority = 3, description="Verify the Pulse Check email")
  public void email_check() throws Exception {
	String Parent_Window = driver.getWindowHandle();
	String host = "pop-mail.outlook.com";// change accordingly
    String storeType = "pop3";
    String user = "saipradeep6@live.com";// change accordingly
    String password = "harika12345";// change accordingly    
    PulseVerify.verify(host, storeType, user, password);    
    Thread.sleep(1000);
    driver.switchTo().window(Parent_Window);
    driver.navigate().refresh();
	Thread.sleep(3000);		
	driver.findElement(By.xpath("//table/tbody/tr[1]/td[5]/a[contains(text(), 'Details')]")).click();
	for(int i=1; i<=8; i++) {
		WebElement element3 = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[6]/i[@class='fa fa-check-circle text-success']"));
		Boolean ele3 = element3.isDisplayed();
		assertTrue(ele3);
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

