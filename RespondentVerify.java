package proq;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import proq.ProQProfileTest;
import proq.ProQRespondentsTest;
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
import org.openqa.selenium.chrome.ChromeDriver;

public class RespondentVerify {
	public static void respverify(String host, String storeType, String user,
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
		          System.out.println(ProQProfileTest.protitle);
		          int size = ProQRespondentsTest.roles.length;
		          for(int j=0; j<size; j++) {		        	  
		        	  if(str1.equals("Please confirm your email address for Project \""+ProQRespondentsTest.proname+"\" as \""+ProQRespondentsTest.roles[j]+"\" Role.")) {
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
			              driver.findElement(By.xpath("//input[@placeholder='Enter new Password']")).sendKeys("1234");
			              Thread.sleep(1000);
			              driver.findElement(By.xpath("//input[@placeholder='Enter confirm Password']")).sendKeys("1234");
			              Thread.sleep(1000);
			              driver.findElement(By.xpath("//button[@type='submit']")).click();
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
	    respverify(host, mailStoreType, username, password);

	}

}
