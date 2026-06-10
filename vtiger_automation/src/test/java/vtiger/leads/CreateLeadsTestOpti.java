package vtiger.leads;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ObjectRepository.HomePage;
import ObjectRepository.LeadsPage;
import ObjectRepository.LoginPage;
import baseutility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class CreateLeadsTestOpti extends BaseClass{
	
		@Test
		public void runLeadTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
			ExtentTest test=report.createTest("runOptiTest");
			//FileUtility futil= new FileUtility();
//		String BROWSER=futil.getDataFromJsonFile("bro");
//		String URL=futil.getDataFromJsonFile("url");
//		
//		if(BROWSER.equalsIgnoreCase("chrome")) {
//			driver=new ChromeDriver();
//		}
//
//		WebdriverUtility Wutil= new WebdriverUtility(driver);
//		Wutil.maximizeWindow();
//		Wutil.implicitWait();
//
//		driver.get(URL);
//
//		LoginPage lp=new LoginPage(driver);
//		lp.login();
//		System.out.println("Login successful");

		// ==============================
		// Navigate to leads Module
		// ==============================
		HomePage hp=new HomePage(driver);
		hp.getLeadslink().click();
		LeadsPage lpp= new LeadsPage(driver);
		lpp.getCreateleadicon().click();
		String initialfname="aman";
		String initiallastname="singh";
		String initialComName="everest";
		lpp.getFirstNameField().sendKeys(initialfname);
		lpp.getLastNameField().sendKeys(initiallastname);
		lpp.getCompanyNameField().sendKeys(initialComName);
		lpp.getSavebtn().click();
		String verifylastname=lpp.getVerifyLastName().getText();
		String verifycompname=lpp.getVerifyCOmpany().getText();
		if(initiallastname.equals(verifylastname) && initialComName.equals(verifycompname)) {
			System.out.println("verified");
			test.log(Status.PASS, "contact is varified");
		}else {
			System.out.println("not verified");
			test.log(Status.FAIL, "contact is varified");
		}
		
//		WebElement profileIcon =hp.getProfileicon();
//		Wutil.hover(profileIcon);
//		hp.getSignoutLink().click();
//		System.out.println("Logout successful");
//
//		Thread.sleep(3000);
//		System.out.println("Browser closed successfully");
//		driver.quit();
//		
		
		
	}
}