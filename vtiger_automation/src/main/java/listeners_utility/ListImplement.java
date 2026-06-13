package listeners_utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseutility.BaseClass;
import generic_utility.JavaUtility;

public class ListImplement implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		String time = JavaUtility.getCurrentTime();
		ExtentSparkReporter spark = new ExtentSparkReporter("./advance_report/" + time + ".html");
		spark.config().setDocumentTitle("listner report");
		spark.config().setReportName("lit1");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("type", "web");
		report.setSystemInfo("os", "window");

	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		test = report.createTest(methodname);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		test.log(Status.PASS, methodname + "passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		test.log(Status.FAIL, methodname + "failed");
		
		TakesScreenshot tks=(TakesScreenshot) BaseClass.sdriver;
		String ss=tks.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(ss,methodname);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodname + "skipeed");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
	}
}
