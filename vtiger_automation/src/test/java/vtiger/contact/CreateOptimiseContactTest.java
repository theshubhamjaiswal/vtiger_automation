package vtiger.contact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import ObjectRepository.ContactPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
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
public class CreateOptimiseContactTest {
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ParseException {

		// ==============================
		// Browser Setup
		// ==============================
		WebDriver driver = null;
		
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
		// Navigate to contact Module
		// ==============================
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
		//if (firstName.equals(verifiedFirstname) && lastName.equals(verifiedLastname)) {
	//		System.out.println("contact is varified");	
		//} else {
	//		System.out.println("verification is failed");
		//}
		
		
		WebElement profileIcon =hp.getProfileicon();
		Wutil.hover(profileIcon);
		hp.getSignoutLink().click();
		System.out.println("Logout successful");

		Thread.sleep(3000);
		System.out.println("Browser closed successfully");
		driver.quit();
	}
}
