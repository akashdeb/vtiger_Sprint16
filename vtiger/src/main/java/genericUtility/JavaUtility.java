package genericUtility;

import java.util.Date;
import java.util.Random;
/**
 * 
 * @author Akash Deb
 *
 */
public class JavaUtility {
	
	/**
	 * This method is used to generate random no
	 * @param limit
	 * @return
	 */
	public int generateRandomNumber(int limit) {
		
		Random ran = new Random();
		return ran.nextInt(limit);
		
	}
	
	/**
	 * This method is used to generate system date and time
	 * @return
	 */
	public String generateSystemDateAndTime() {
		
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
		
	}

}
