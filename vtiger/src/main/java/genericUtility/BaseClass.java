package genericUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.mysql.jdbc.Driver;

import pomRepository.HomePage;
import pomRepository.LoginPage;
/**
 * 
 * @author Akash Deb
 *
 */
public class BaseClass {
	public WebDriver driver;
	public static WebDriver sDriver;
	public FileUtility fUtils=new FileUtility();
	public JavaUtility jUtils=new JavaUtility();
	public ExcelUtility eUtils=new ExcelUtility();
	public WebDriverUtility wUtils=new WebDriverUtility();
	public DataBaseUtility dUtils = new DataBaseUtility();
	
	public Connection connection;
	
	@BeforeSuite(groups = {"smoke", "regression"})
	public void bsConfig() throws SQLException {
		
		Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);
		
		connection = DriverManager.getConnection(IPathConstant.DATA_BASE_URL, IPathConstant.DATA_BASE_USERNAME, IPathConstant.DATA_BASE_PASSWORD);
		
		System.out.println("Data base Connection established");
		
	}
	
	@BeforeClass(groups = {"smoke", "regression"})
	public void bcConfig() throws IOException {
		
		String browser=fUtils.fetchDataFromPropertFile(IPathConstant.BROWSER_KEY);
		String url=fUtils.fetchDataFromPropertFile(IPathConstant.URL_KEY);

		if(browser.equals(IPathConstant.BROWSER_VALUE_CHROME)) {
			driver = new ChromeDriver();
		}
		
		else if(browser.equals(IPathConstant.BROWSER_VALUE_FIREFOX)) {
			driver = new FirefoxDriver();
		}
		
		else if(browser.equals(IPathConstant.BROWSER_VALUE_EDGE)) {
			driver = new EdgeDriver();
		}
		System.out.println("The Browser has been launched");
		wUtils.maximizeTheWindow(driver);
		wUtils.waitForElement(driver);
		driver.get(url);
		System.out.println("The URL is navigated");
		sDriver = driver;
	}
	
	@BeforeMethod(groups = {"smoke", "regression"})
	public void bmConfig() throws IOException {
		
		String username=fUtils.fetchDataFromPropertFile(IPathConstant.USERNAME_KEY);
		String password=fUtils.fetchDataFromPropertFile(IPathConstant.PASSWORD_KEY);
		
		LoginPage login = new LoginPage(driver);
		login.loginAction(username, password);
		System.out.println("The User has Logged in");
		
		
	}
	
	@AfterMethod(groups = {"smoke", "regression"})
	public void amConfig() {
		
		HomePage home = new HomePage(driver);
		home.signOutAction();
		System.out.println("The User has logged out");
	}
	
	@AfterClass(groups = {"smoke", "regression"})
	public void acConfig() {
		
		driver.quit();
		System.out.println("The Browser has closed");
	}
	
	@AfterSuite(groups = {"smoke", "regression"})
	public void asConfig() throws SQLException {
		connection.close();

		System.out.println("Data base Connection Closed");
		
	}

}
