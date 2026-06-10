package vtiger.org;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrganPage;
import baseutility.BaseClass;

/**
 * Test Case: Create Organization in CRM Application
 * 
 * Author : shubham Experience : Automation Tester | 2 Years Tool Stack : Java +
 * Selenium WebDriver
 * 
 * Objective: Automate the end-to-end flow of creating a new Organization in the
 * CRM application and validate successful creation.
 * 
 * Test Flow: 1. Launch Browser 2. Login to CRM Application 3. Navigate to
 * Organizations Module 4. Create New Organization 5. Validate Organization
 * Creation 6. Logout from Application 7. Close Browser
 */

public class CreateOganisationTest extends BaseClass {
	@Test
	public void createOrg() throws InterruptedException {
	ExtentTest test=report.createTest("createOrg");
		// ==============================
		// Browser Setup
		// ==============================
	//	WebDriver driver = new ChromeDriver();
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//
//		// ==============================
//		// Launch Application
//		// ==============================
//		//driver.get("http://localhost:8888/");
//
//		// ==============================
//		// Login to CRM Application
//		// ==============================
//		//driver.findElement(By.name("user_name")).sendKeys("admin");
//		//driver.findElement(By.name("user_password")).sendKeys("manager");
//
//		//driver.findElement(By.id("submitButton")).click();
//		
//		LoginPage lp=new LoginPage(driver);
//		lp.getUnfield().sendKeys("admin");
//		lp.getPwdfield().sendKeys("manager");
//		lp.getLoginbtn().click();
//
//		System.out.println("Login successful");
//
//		// ==============================
//		// Navigate to Organizations Module
//		// ==============================
//		//driver.findElement(By.linkText("Organizations")).click();
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		// Click on Create Organization Icon
		//driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();
		OrganPage op=new OrganPage(driver);
		op.getPlusicon().click();
		// ==============================
		// Create New Organization
		// ==============================

		// Generate unique organization name
		int randomNum = (int) (Math.random() * 1000);

		String expectedOrgName = "AutomationWithPiyush_" + randomNum;

		// Enter Organization Name
		//WebElement orgNameTextField = driver.findElement(By.name("accountname"));
		//orgNameTextField.sendKeys(expectedOrgName);
		op.getOrgnamefield().sendKeys(expectedOrgName);
		// Save Organization
		//driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		op.getSavebtn().click();
		System.out.println("Organization creation form submitted");

		// ==============================
		// Validation
		// ==============================
		//String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		String actualOrgName=op.getVerifyOrgNameField().getText();
		if (actualOrgName.equals(expectedOrgName)) {
			test.log(Status.PASS, "Organization created successfully");
			System.out.println("PASS : Organization created successfully");
			System.out.println("Created Organization Name : " + actualOrgName);

		} else {
			test.log(Status.FAIL, "FAIL : Organization creation failed");
			System.out.println("FAIL : Organization creation failed");
			System.out.println("Expected : " + expectedOrgName);
			System.out.println("Actual   : " + actualOrgName);
		}

		// ==============================
		// Logout from Application
		// ==============================
//		//WebElement profileIcon = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
//		WebElement profileIcon=hp.getProfileicon();
//		Actions actions = new Actions(driver);
//
//		actions.moveToElement(profileIcon).perform();
//
//		//driver.findElement(By.linkText("Sign Out")).click();
//		hp.getSignoutLink().click();
//		System.out.println("Logout successful");
//
//		// ==============================
//		// Close Browser
//		// ==============================
//		Thread.sleep(3000);
//
//		driver.quit();

		//System.out.println("Browser closed successfully");

	}
}
