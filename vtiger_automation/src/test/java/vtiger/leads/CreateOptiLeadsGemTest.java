package vtiger.leads;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ObjectRepository.HomePage;
import ObjectRepository.LeadsPage;
import ObjectRepository.LoginPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class CreateOptiLeadsGemTest {

	// Global declarations for better reusability across TestNG configurations
	private WebDriver driver;
	private FileUtility futil;
	private WebdriverUtility wUtil;
	private LoginPage lp;
	private HomePage hp;
	private LeadsPage lpp;

	@BeforeMethod
	public void setUp() throws IOException, ParseException {
		futil = new FileUtility();
		String browser = futil.getDataFromJsonFile("bro");
		String url = futil.getDataFromJsonFile("url");

		// Dynamic browser initialization
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}

		wUtil = new WebdriverUtility(driver);
		wUtil.maximizeWindow();
		wUtil.implicitWait();

		driver.get(url);

		// Login Pre-condition
		lp = new LoginPage(driver);
		lp.login();
		System.out.println("INFO: Login successful");
	}

	@Test()
	public void createLeadAndVerifyTest() {
		hp = new HomePage(driver);
		lpp = new LeadsPage(driver);

		// Test Data (Kept exactly as requested)
		String initialFname = "aman";
		String initialLastname = "singh";
		String initialComName = "everest";

		// Step 1: Navigate to Leads Module
		hp.getLeadslink().click();
		lpp.getCreateleadicon().click();

		// Step 2: Fill Lead Information & Save
		lpp.getFirstNameField().sendKeys(initialFname);
		lpp.getLastNameField().sendKeys(initialLastname);
		lpp.getCompanyNameField().sendKeys(initialComName);
		lpp.getSavebtn().click();

		// Step 3: Fetch Verification Text
		String verifyLastname = lpp.getVerifyLastName().getText();
		String verifyCompname = lpp.getVerifyCOmpany().getText();

		// Step 4: Industry-standard TestNG Assertions (Replaces the if-else blocks)
		Assert.assertEquals(verifyLastname, initialLastname, "CRITICAL: Last Name verification failed!");
		Assert.assertEquals(verifyCompname, initialComName, "CRITICAL: Company Name verification failed!");
		System.out.println("INFO: Lead details verified successfully.");
	}

	@AfterMethod
	public void tearDown() {
		// Post-condition: Logout safely
		try {
			if (hp != null) {
				WebElement profileIcon = hp.getProfileicon();
				wUtil.hover(profileIcon);
				hp.getSignoutLink().click();
				System.out.println("INFO: Logout successful");
			}
		} catch (Exception e) {
			System.out.println("WARNING: Failed to log out cleanly: " + e.getMessage());
		} finally {
			// Ensures browser closes even if the logout step fails
			if (driver != null) {
				driver.quit();
				System.out.println("INFO: Browser closed successfully");
			}
		}
	}
}