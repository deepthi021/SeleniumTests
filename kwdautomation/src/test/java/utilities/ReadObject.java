package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
	
	Properties p = new Properties();
	
	public Properties getObjectProperty(String propFilePath) {
		
		try {
		//	System.out.println(propFilePath);			
			InputStream strm = new FileInputStream(new File(propFilePath));
			p.load(strm);
		}catch(Exception e) {
			System.out.println("Something wrong in getting properties: "+e);
			
		}
		return p;
	}

}
