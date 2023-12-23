package learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class NonSelectQuerry {

	public static void main(String[] args) throws SQLException {
Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger", "root", "root");
		
		int result = connection.createStatement().executeUpdate("insert into lead values('Nayaz', 'Rehman', 'CA');");
		
		if(result == 1) {
			System.out.println("Data Base is updated");
		}
		
		else
			System.out.println("Data Base is not updated");
		
		connection.close();

	}

}
