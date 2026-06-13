package vtiger.contact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ObjectRepository.ContactPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import baseutility.BaseClass;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

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
@Listeners(listeners_utility.List_Imp.class)
public class CreateOptimiseContactTest extends BaseClass {
	
	//public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ParseException {
@Test
public void runOptiTest() throws FileNotFoundException, IOException, ParseException {
	//ExtentTest test=report.createTest("runOptiTest");
		// ==============================
		// Browser Setup
		// ==============================
		
		// ==============================
		// Navigate to contact Module
		// ==============================
		WebdriverUtility Wutil= new WebdriverUtility(driver);
		HomePage hp=new HomePage(driver);
		hp.getContactlink().click();
		ContactPage cp=new ContactPage(driver);
		cp.getCreateContactIcon().click();
		WebElement dropdown1 = cp.getFirstNameDrop();
		Wutil.selectDropdownByIndex(dropdown1, 1);
		System.out.println("mr dropdown is selected");
		String firstName = "shubham";
		cp.getFirstNameField().sendKeys(firstName);
		System.out.println(firstName + "is entered as first name");
		String lastName = "jaiswal";
		cp.getLastNameField().sendKeys(lastName);
		System.out.println(lastName + "is entered as last name");
		String pid = driver.getWindowHandle();
		System.out.println("pid windowhandle is captured");
		cp.getSelectoOrgIcon().click();
		Set<String> cids = driver.getWindowHandles();
		for (String id : cids) {
			String title = driver.switchTo().window(id).getTitle();
			System.out.println(title);
			if (!id.equals(pid)) {
				System.out.println("comes to child window");
				break;
			}
		}
		cp.getSelectOrgLink().click();
		driver.switchTo().window(pid);
		System.out.println("returned to parent window");
		cp.getSaveContact().click();

		String verifiedFirstname = cp.getVerifyFirstName().getText();
		String verifiedLastname = cp.getVerifyLastName().getText();
		Assert.assertEquals(firstName,verifiedFirstname);
		Assert.assertEquals(lastName,verifiedLastname);
		boolean status= (firstName.equals(verifiedFirstname) && lastName.equals(verifiedLastname));
		Assert.assertTrue(status);
		
//		
//		if (firstName.equals(verifiedFirstname) && lastName.equals(verifiedLastname)) {
//			System.out.println("contact is varified");	
//			test.log(Status.PASS, "contact is varified");
//		} else {
//			System.out.println("verification is failed");
//			test.log(Status.FAIL, "contact is varified");
////		}
		
		
	
	}
}
