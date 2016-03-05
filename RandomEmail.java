package proq;

public class RandomEmail {
	
	public static final String[] proemail = new String[20];	
	
	public static void proadmin(int j){
		for(int i=10; i<20; i++, j++) {
			proemail[j] = "saipradeep6+"+i+"@live.com";
			System.out.println(proemail[j]);
		}
	}
	
	public static void main(String a[]) 
	{ 
		proadmin(0);        
	}
}
