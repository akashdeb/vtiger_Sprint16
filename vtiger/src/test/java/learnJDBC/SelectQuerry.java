package learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class SelectQuerry {

	public static void main(String[] args) throws SQLException {
		
		Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger", "root", "root");
		
		ResultSet result = connection.createStatement().executeQuery("select lastname from lead where firstname = 'Akash';");
		
		if(result.next()) {
			String firstName = result.getString(1);
			System.out.println(firstName);
		}
		
		else
			System.out.println("Data base is empty");
		

	}

}
