package demo_Module;

import java.sql.SQLException;

import org.testng.annotations.Test;

import genericUtility.BaseClass;

public class DemoClassTest extends BaseClass {

	@Test
	public void demoTest() throws SQLException {
		
		String data = dUtils.fetchDataFromDatabase(connection, "select * from lead");
		
		System.out.println(data);
		
		dUtils.insertDataToDatabase(connection, "insert into lead values('Rajesh', 'Madhur', 'Qspiders');");
		
	}
}
