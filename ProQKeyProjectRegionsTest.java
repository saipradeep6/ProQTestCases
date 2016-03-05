package proq;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.*;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ProQKeyProjectRegionsTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private SoftAssert softAssert = new SoftAssert();
  
  @BeforeTest
  public void setUp() throws Exception {
	driver = new FirefoxDriver();
//	System.setProperty("webdriver.chrome.driver", "D:/ProjectWork/Selenium/chromedriver.exe");
//	driver = new ChromeDriver();
    driver.manage().window().maximize();
    baseUrl = "http://newdev.proq-erp.com/ui#/login";
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
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
  
  @Test(priority = 1, description="Enter the option2 field with Non-Hierachical Data")
  public void Key_Region() throws Exception {
	driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/paneltool[@class='ng-scope'])[last()]")).click();
	driver.findElement(By.linkText("Key Project Region")).click();
	Thread.sleep(1000);
	new Select(driver.findElement(By.xpath("//select[@class='ng-pristine ng-valid']"))).selectByVisibleText("2");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@type='text' and @ng-model='regionLabels[1]']")).clear();
	driver.findElement(By.xpath("//input[@type='text' and @ng-model='regionLabels[1]']")).sendKeys("Key Areas");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[@tooltip='Save' and @type='submit']")).click();
	WebElement element1 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success"));
    Boolean ele1 = element1.isEnabled();
    softAssert.assertTrue(ele1);
    System.out.println(element1.getText());
  }
  
  @Test(priority = 2, description="Enter the Key Areas with Non-Hierachical Data")
  public void Non_Hierarchical_Key_Areas() throws Exception {
	driver.findElement(By.xpath("//div[@class='col-md-6']/label[@class='push-left ng-binding']/button[@ng-click='vk.addLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='tableadjust scrollit']/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value' and @type='text']")).clear();
	driver.findElement(By.xpath("//div[@class='tableadjust scrollit']/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value' and @type='text']")).sendKeys("North America");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='tableadjust scrollit']/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-6']/table/tbody/tr[@ng-show='regionLevel1']/td[@class='point ng-binding']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-6']/label[@class='push-left ng-binding']/button[@ng-click='vk.addSubLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-6']/div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@type='text']")).clear();
	driver.findElement(By.xpath("//div[@class='col-md-6']/div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@type='text']")).sendKeys("Jamaica");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-6']/div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-6']/label[@class='push-left ng-binding']/button[@ng-click='vk.addSubLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-6']/div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@tooltip='Enter Value']")).clear();
	driver.findElement(By.xpath("//div[@class='col-md-6']/div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@tooltip='Enter Value']")).sendKeys("Canada");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-6']/div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-12']/div[@class='pull-right']/button[@type='submit' and text()='Save']")).click();
	WebElement element2 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele2 = element2.isEnabled();
    softAssert.assertTrue(ele2);
    System.out.println(element2.getText());
  }
  
  @Test(priority = 3, description="Test the Key Project Region details in Project Profile")
  public void ProjectProfile_NonHierarchical() throws Exception {
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	driver.findElement(By.linkText("Vendors")).click();
	driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/a[@tooltip='View Projects'])[last()]")).click();
	driver.findElement(By.xpath("//button[contains(text(), 'Add Project')]")).click();
    Thread.sleep(3000);
    jse.executeScript("scroll(0, 400)");    
    WebElement element1 = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.keyProjectRegion']"));
    element1.click();
    Select mySelect1 = new Select(element1);
    mySelect1.selectByVisibleText("North America");
    WebElement option1 = mySelect1.getFirstSelectedOption();
    System.out.println(option1.getText());
    Boolean ele3 = option1.isEnabled();
    Thread.sleep(2000);
    WebElement element2 = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.keyProjectRegion2']"));
    element2.click();
    Select mySelect2 = new Select(element2);
    mySelect2.selectByVisibleText("Canada");
    WebElement option2 = mySelect2.getFirstSelectedOption();
    System.out.println(option2.getText());
    Boolean ele4 = option2.isEnabled();    
    softAssert.assertTrue(ele3&ele4);
    System.out.println(option2.getText());
    Thread.sleep(5000);
  }  
  
  @Test(priority = 4, description="Enter the Key Project Regions with Hierachical Data")
  public void Hierarchical_Key_Region() throws Exception {
	driver.findElement(By.linkText("Vendors")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[4]/div/div[4]/span[3]/paneltool[1]/a")).click();
	Thread.sleep(2000);
	driver.findElement(By.linkText("Key Project Region")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/label[@class='push-left ng-binding']/button[@ng-click='vk.addLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).clear();
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).sendKeys("Southern Europe");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/label[@class='push-left ng-binding']/button[@ng-click='vk.addLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).clear();
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).sendKeys("North America");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='tableadjust']/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-12']/div[@class='pull-right']/button[@type='submit' and text()='Save']")).click();
	Thread.sleep(1000);
	WebElement element4 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele4 = element4.isEnabled();
    softAssert.assertTrue(ele4);
    System.out.println(element4.getText());
  }
  
  @Test(priority = 5, description="Enter the option2 field with Hierarchical Data")
  public void Hierarchical_Key_Area() throws Exception {
	new Select(driver.findElement(By.xpath("//select[@class='ng-pristine ng-valid']"))).selectByVisibleText("2");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@type='text' and @ng-model='regionLabels[1]']")).clear();
	driver.findElement(By.xpath("//input[@type='text' and @ng-model='regionLabels[1]']")).sendKeys("Key Areas");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//button[@tooltip='Save' and @type='submit']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-12']/div[@class='pull-right']/button[@type='submit' and text()='Save']")).click();
	Thread.sleep(1000);
	WebElement element5 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele5 = element5.isEnabled();
    softAssert.assertTrue(ele5);
    System.out.println(element5.getText());
  }
  
  @Test(priority = 6, description="Enter the Key Areas with Hierachical Data")
  public void Key_Areas() throws Exception {
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/span[text()='Southern Europe']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/label[@class='push-left ng-binding']/button[@ng-click='vk.addSubLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).sendKeys("Spain");
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/label[@class='push-left ng-binding']/button[@ng-click='vk.addSubLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).sendKeys("Portugal");
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/span[text()='North America']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/label[@class='push-left ng-binding']/button[@ng-click='vk.addSubLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).sendKeys("United States");
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/label[@class='push-left ng-binding']/button[@ng-click='vk.addSubLevelValue()']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/input[@placeholder='Enter Value']")).sendKeys("Mexico");
	driver.findElement(By.xpath("//div[@class='col-md-4']/div/table/tbody/tr[@class='ng-scope']/td/button[@class='btn btn-xs btn-success adjust']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//div[@class='col-md-12']/div[@class='pull-right']/button[@type='submit' and text()='Save']")).click();
	WebElement element6 = driver.findElement(By.cssSelector("div.uk-notify-message.uk-notify-message-success > div"));
    Boolean ele6 = element6.isEnabled();
    softAssert.assertTrue(ele6);
    System.out.println(element6.getText());	
  }
  
  @Test(priority = 7, description="Test the Key Project Regions in Project Profile")
  public void ProjectProfile_Hierarchical() throws Exception {
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	driver.findElement(By.linkText("Vendors")).click();
	driver.findElement(By.xpath("(//div[@class='row panel-heading']/span/a[@tooltip='View Projects'])[last()]")).click();
	driver.findElement(By.xpath("//button[contains(text(), 'Add Project')]")).click();
	Thread.sleep(3000);
	jse.executeScript("scroll(0, 400)");    
	WebElement element1 = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.keyProjectRegion']"));
	element1.click();
	Select mySelect1 = new Select(element1);
	mySelect1.selectByVisibleText("North America");
	WebElement option1 = mySelect1.getFirstSelectedOption();
	System.out.println(option1.getText());
	Boolean ele3 = option1.isEnabled();
	Thread.sleep(2000);
	WebElement element2 = driver.findElement(By.xpath("//select[@ng-model='pc.projectDetail.data.keyProjectRegion2']"));
	element2.click();
	Select mySelect2 = new Select(element2);
	mySelect2.selectByVisibleText("Canada");
	WebElement option2 = mySelect2.getFirstSelectedOption();
	System.out.println(option2.getText());
	Boolean ele4 = option2.isEnabled();    
	assertTrue(ele3&ele4);
	System.out.println(option2.getText());
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
