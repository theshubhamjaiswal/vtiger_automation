package baseutility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClassDummy {
	 public WebDriver driver ;
	 public ExtentReports report;
	public ExtentSparkReporter spark;
	@BeforeSuite
	public void reportconfig() {
		spark=new ExtentSparkReporter("./advance_report/rep1.html");
		spark.config().setDocumentTitle("title");
		spark.config().setReportName("name");
		spark.config().setTheme(Theme.STANDARD);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("type", "web");
		report.setSystemInfo("tool", "vtiger");
		report.setSystemInfo("os", "window");
		report.setSystemInfo("os version", "window 12");
	}
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	@BeforeMethod
	public void login() {
		driver.get("http://localhost:8888/");

		// ==============================
		// Login to CRM Application
		// ==============================
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Login successful");
	}
	
	@AfterMethod
	public void logout() {
		// ==============================
		// Logout from Application
		// ==============================
		WebElement profileIcon = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(profileIcon).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout successful");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	@AfterSuite
	public void reportBackup() {
	report.flush();	
	}
	
}
