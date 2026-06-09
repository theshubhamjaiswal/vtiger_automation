package vtiger.leads;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepository.HomePage;
import ObjectRepository.LeadsPage;
import ObjectRepository.LoginPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class CreateLeadsTestOpti {
	//public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ParseException {

		// ==============================
		// Browser Setup
		// ==============================
		WebDriver driver = null;
		
		@Test
		public void runLeadTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		FileUtility futil= new FileUtility();
		String BROWSER=futil.getDataFromJsonFile("bro");
		String URL=futil.getDataFromJsonFile("url");
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}

		WebdriverUtility Wutil= new WebdriverUtility(driver);
		Wutil.maximizeWindow();
		Wutil.implicitWait();

		driver.get(URL);

		LoginPage lp=new LoginPage(driver);
		lp.login();
		System.out.println("Login successful");

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
		}else {
			System.out.println("not verified");
		}
		
		WebElement profileIcon =hp.getProfileicon();
		Wutil.hover(profileIcon);
		hp.getSignoutLink().click();
		System.out.println("Logout successful");

		Thread.sleep(3000);
		System.out.println("Browser closed successfully");
		driver.quit();
		
		
		
	}
}