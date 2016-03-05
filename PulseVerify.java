package proq;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import proq.ProQViewersTest;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PulseVerify {
	public static void verify(String host, String storeType, String user,
		      String password) 
		   {
		      try {

		      //create properties field
		      Properties properties = new Properties();	      

		      properties.put("mail.pop3.host", host);
		      properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3.starttls.enable", "true");
		      Session emailSession = Session.getDefaultInstance(properties);
		  
		      //create the POP3 store object and connect with the pop server
		      Store store = emailSession.getStore("pop3s");

		      store.connect(host, user, password);

		      //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_ONLY);

		      // retrieve the messages from the folder in an array and print it
		   //   Message[] messages = emailFolder.getMessages();
		      Flags seen = new Flags(Flags.Flag.SEEN);
		      FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
		      Message messages[] = emailFolder.search(unseenFlagTerm);
		      System.out.println("messages.length---" + messages.length);
		      if (messages.length == 0) 
		    	  System.out.println("No messages found.");
		      
		      for (int i = 0; i < messages.length; i++) {
		          // stop after listing ten messages
		          if (i > 10) {
		            System.exit(0);
		            emailFolder.close(true);
		            store.close();
		          }
		          int n = (messages.length-1) - i;
		          System.out.println("Message " + (i + 1));
		          System.out.println("From : " + messages[n].getFrom()[0]);
		          System.out.println("Subject : " + messages[n].getSubject());
		          System.out.println("Sent Date : " + messages[n].getSentDate());         
		          String str1 = messages[n].getSubject().toString();
		          System.out.println(str1);		     
		   /*       Multipart mp = (Multipart) messages[n].getContent();
	              BodyPart bp = mp.getBodyPart(0);
	              String str = bp.getContent().toString();	
	              System.out.println("Body: "+str);   */
		          int size = ProQViewersTest.roles.length;
		          for(int j=0; j<size; j++) {		        	  
		        	  if(str1.equals("Invitation to Weekly Pulse Check for Sample Project ZJE Project")) {
			        	  Multipart mp = (Multipart) messages[n].getContent();
			              BodyPart bp = mp.getBodyPart(0);
			              String str = bp.getContent().toString();
			              System.out.println("Body: "+str);         
			              String result = str.substring(str.indexOf("<") + 1, str.indexOf(">"));
			              System.out.println("result: "+ result);		              
//			              WebDriver driver = new FirefoxDriver();
			              System.setProperty("webdriver.chrome.driver", "D:/ProjectWork/Selenium/chromedriver.exe");
			          	  WebDriver driver = new ChromeDriver();
			              driver.manage().window().maximize();
			              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			              driver.get(result);
			              WebElement element1 = driver.findElement(By.xpath("//div[@class='container surveymain']/div[@class='row subhead'][1]/h3"));
			              String ele1 = element1.getText();
			          //    WebElement element2 = driver.findElement(By.xpath("//div[@class='col-lg-6'][1]/div[@class='alert alert-info Heading'][1]"));
			          //    System.out.println(element2);
			              if(ele1.equals("Score Card for Service Provider")) {
			            	  for(i=1; i<2; i++) {
			            		  WebElement agility = driver.findElement(By.xpath("//div[span[contains(text(), 'Agility*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(agility.isDisplayed() == true) {
				            		  agility.click();
				            	  }
			            		  WebElement kt = driver.findElement(By.xpath("//div[span[contains(text(), 'Knowledge Transfer*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(kt.isDisplayed() == true) {
				            		  kt.click();
				            	  }
			            		  WebElement vision = driver.findElement(By.xpath("//div[span[contains(text(), 'Vision*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(vision.isDisplayed() == true) {
				            		  vision.click();
				            	  }
			            		  WebElement focus = driver.findElement(By.xpath("//div[span[contains(text(), 'Industry Focus*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(focus.isDisplayed() == true) {
				            		  focus.click();
				            	  }
			            		  WebElement pd = driver.findElement(By.xpath("//div[span[contains(text(), 'Project Delivery*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(pd.isDisplayed() == true) {
				            		  pd.click();
				            	  }
			            		  WebElement td = driver.findElement(By.xpath("//div[span[contains(text(), 'Timely Delivery*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(td.isDisplayed() == true) {
				            		  td.click();
				            	  }
			            		  WebElement mt = driver.findElement(By.xpath("//div[span[contains(text(), 'Methodology and Tools*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(mt.isDisplayed() == true) {
				            		  mt.click();
				            	  }
			            		  WebElement opip = driver.findElement(By.xpath("//div[span[contains(text(), 'OCM/Post-Implementation Planning*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(opip.isDisplayed() == true) {
				            		  opip.click();
				            	  }
			            		  WebElement fs = driver.findElement(By.xpath("//div[span[contains(text(), 'Functional Solution*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(fs.isDisplayed() == true) {
				            		  fs.click();
				            	  }
			            		  WebElement ts = driver.findElement(By.xpath("//div[span[contains(text(), 'Technical Solution*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
				            	  if(ts.isDisplayed() == true) {
				            		  ts.click();
				            	  }
			            		  WebElement value = driver.findElement(By.xpath("//div[span[contains(text(), 'Value Add*')]]/div[@id='ScoreCard1']["+i+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			            		  if(value.isDisplayed() == true) {
			            			  value.click();
			            		  }
			            	  }		            	  
			              }
			              	
			              else if(ele1.equals("Score Card for Client")) {
			              		for(j=1; j<=12; j++) {
			              		  WebElement mc = driver.findElement(By.xpath("//div[span[contains(text(), 'Management Commitment*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(mc.isDisplayed() == true) {
			              			  mc.click();
			              		  }
			              		  WebElement rl = driver.findElement(By.xpath("//div[span[contains(text(), 'Resource Level*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(rl.isDisplayed() == true) {
			              			  rl.click();
			              		  }
			              		  WebElement sa = driver.findElement(By.xpath("//div[span[contains(text(), 'Scope Adherence*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(sa.isDisplayed() == true) {
			              			  sa.click();
			              		  }
			              		  WebElement ie = driver.findElement(By.xpath("//div[span[contains(text(), 'Infrastructure & Environment*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(ie.isDisplayed() == true) {
			              			  ie.click();
			              		  }
			              		  WebElement pd = driver.findElement(By.xpath("//div[span[contains(text(), 'Project Delivery*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(pd.isDisplayed() == true) {
			              			  pd.click();
			              		  }
			              		  WebElement tk = driver.findElement(By.xpath("//div[span[contains(text(), 'Team Knowledge Transfer*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(tk.isDisplayed() == true) {
			              			  tk.click();
			              		  }
			              		  WebElement mt = driver.findElement(By.xpath("//div[span[contains(text(), 'Methodology and Tools*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(mt.isDisplayed() == true) {
			              			  mt.click();
			              		  }
			              		  WebElement co = driver.findElement(By.xpath("//div[span[contains(text(), 'Cohesion*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(co.isDisplayed() == true){
			              			  co.click();
			              		  }
			              		  WebElement fs = driver.findElement(By.xpath("//div[span[contains(text(), 'Functional Solution*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(fs.isDisplayed() == true) {
			              			  fs.click();
			              		  }
			              		  WebElement ts = driver.findElement(By.xpath("//div[span[contains(text(), 'Technical Solution*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(ts.isDisplayed() == true) {
			              			  ts.click();
			              		  }
			              		  WebElement va = driver.findElement(By.xpath("//div[span[contains(text(), 'Value Add*')]]/div[@id='ScoreCard1']["+j+"]/div[@class='col-lg-9']/div/span[@class='rating-cls ng-isolate-scope ng-pristine ng-valid']/i[7]"));
			              		  if(va.isDisplayed() == true) {
			              			  va.click();
			              		  }
			              	  }
			              }			              		              
			             			              
			              driver.findElement(By.xpath("//button[text()='Submit Response']")).click();
			              Thread.sleep(1000);
			              driver.quit();
			          }
		          }
		          
		      } 	       

		      //close the store and folder objects
		      emailFolder.close(true);
		      store.close();

		      } catch (NoSuchProviderException e) {
		         e.printStackTrace();
		      } catch (MessagingException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
		
	public static void main(String[] args) {
	    String host = "pop-mail.outlook.com";// change accordingly
	    String mailStoreType = "pop3";
	    String username = "saipradeep6@live.com";// change accordingly
	    String password = "harika12345";// change accordingly
	    verify(host, mailStoreType, username, password);
	}

}
