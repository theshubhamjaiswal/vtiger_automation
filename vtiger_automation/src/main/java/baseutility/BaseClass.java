package baseutility;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class BaseClass {

	public WebDriver driver;
	public ExtentSparkReporter spark;
	public ExtentReports report;
	@BeforeSuite
	public void reportConfig() {
		spark = new ExtentSparkReporter("./advance_report/rep1.html") ;
		spark.config().setDocumentTitle("vtiger report");
		spark.config().setReportName("vtiger crm");
		spark.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("type", "web");
		report.setSystemInfo("tool", "tool");
		report.setSystemInfo("os", "window");
		report.setSystemInfo("os version", "window 12");
		
		
	}
	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException, ParseException {
		FileUtility futil = new FileUtility();
		String BROWSER = futil.getDataFromJsonFile("bro");
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		WebdriverUtility Wutil = new WebdriverUtility(driver);
		Wutil.maximizeWindow();
		Wutil.implicitWait();

	}

	@BeforeMethod
	public void login() throws FileNotFoundException, IOException, ParseException {
		FileUtility futil= new FileUtility();
		String URL=futil.getDataFromJsonFile("url");
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.login();
		System.out.println("Login successful");
		String r=futil.getDataFromExcelFile("contact", 2, 0);
		System.out.println(r);
	}

	@AfterMethod
	public void logout() {
		WebdriverUtility Wutil= new WebdriverUtility(driver);
		HomePage hp=new HomePage(driver);
		WebElement profileIcon =hp.getProfileicon();
		Wutil.hover(profileIcon);
		hp.getSignoutLink().click();
		System.out.println("Logout successful");
	}

	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Browser closed successfully");
		driver.quit();
	}
	@AfterSuite
	public void reportBackup() {
		report.flush();
	}
}
