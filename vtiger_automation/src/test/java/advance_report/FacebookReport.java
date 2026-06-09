package advance_report;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class FacebookReport {
	@Test
	public void login() throws InterruptedException {
		ExtentSparkReporter spark= new ExtentSparkReporter("./advance_report/rep1.html");
		spark.config().setDocumentTitle("facebook report");
		spark.config().setReportName("faceReport1");
		spark.config().setTheme(Theme.DARK);
		
		ExtentReports report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("browser", "chrome");
		report.setSystemInfo("device", "infinix");
		report.setSystemInfo("os", "window");
		
		ExtentTest test =report.createTest("login");
		test.log(Status.FAIL,"failed........");
		test.log(Status.PASS,"passed........");
		test.log(Status.SKIP,"skipedd........");
		test.log(Status.WARNING,"warning........");
		test.log(Status.INFO,"info........");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.fb.com");
		Thread.sleep(1000);
		driver.quit();
		report.flush();
	}
}
