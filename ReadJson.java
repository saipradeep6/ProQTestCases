package proq;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {
	public static String firstname;
	public static String lastname;
	public static String role;
	public static String email;
	public static void jsonRead() {
		JSONParser parser = new JSONParser();		 
        try {
 
            Object obj = parser.parse(new FileReader(
                    "D:/ProjectWork/Selenium/ProQTestCases/src/proq/roles.json")); 
            JSONArray jsonArray = (JSONArray) obj;            
            for(int i=0; i<jsonArray.size(); i++) {
            	JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            	firstname = (String) jsonObject.get("FirstName");
            	lastname = (String) jsonObject.get("LastName");
            	role = (String) jsonObject.get("Role");
            	email = (String) jsonObject.get("Email");
            	System.out.println("First Name: " + firstname);
            	System.out.println("Last Name: " + lastname);
            	System.out.println("Role: " + role);
            	System.out.println("Email: " + email);
            	System.out.println("\n");
            }
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
        jsonRead();
    }
}


