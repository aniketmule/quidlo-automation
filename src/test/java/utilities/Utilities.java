package utilities;
import java.util.*;  
import java.io.*;  

public class Utilities {
	public String defaultExlpicitTime;
	public String defaultImplicitTime;
	public String primaryUrl;
	public String secondaryUrl;
	public FileReader fileRead;
	public FileReader emailRead;
    public Properties p = new Properties();  
    
	public void getConfigProperties() {
		try {
			fileRead = new FileReader(System.getProperty("user.dir")+"/config.properties");
			p.load(fileRead);
			defaultExlpicitTime=p.getProperty("defaultExplicitTime");
			defaultImplicitTime=p.getProperty("defaultImplicitTime");
			primaryUrl=p.getProperty("primaryUrl");
			fileRead.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void setEmail(String value) {
		try {
			FileWriter fileWrite = new FileWriter(System.getProperty("user.dir")+"/email.properties");
			p.setProperty("ValidUserEmailId", value);
			p.store(fileWrite, "Email Property has been set");
			fileWrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getConfigProperty(String key) {
		return p.getProperty(key);
	}
	public String getEmail() {
		String email = null;
		try {
			emailRead = new FileReader(System.getProperty("user.dir")+"/email.properties");
			p.load(emailRead);
			email = p.getProperty("ValidUserEmailId");
			emailRead.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}


}

