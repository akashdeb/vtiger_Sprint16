package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class contains the method retrieve data from property file
 * @author Akash Deb
 *
 */
public class FileUtility {
	
	/**
	 * This method is used to fetch data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String fetchDataFromPropertFile(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream(IPathConstant.PROPERTY_FILE_PATH);
		
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
			
	}

}
