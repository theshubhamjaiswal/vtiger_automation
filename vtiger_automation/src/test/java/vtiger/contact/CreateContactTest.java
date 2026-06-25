package vtiger.contact;
/**
 * Test Case: Create Organization in CRM Application
 * 
 * Author      : shubham
 * Experience  : Automation Tester | 2 Years
 * Tool Stack  : Java + Selenium WebDriver
 * 
 * Objective:
 * Automate the end-to-end flow of creating a new Organization
 * in the CRM application and validate successful creation.
 * 
 * Test Flow:
 * 1. Launch Browser
 * 2. Login to CRM Application
 * 3. Navigate to Organizations Module
 * 4. Create New Organization
 * 5. Validate Organization Creation
 * 6. Logout from Application
 * 7. Close Browser
 */

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseutility.BaseClassDummy;

public class CreateContactTest extends BaseClassDummy{
	
	@Test	
	public void createTest() {
				ExtentTest test=report.createTest("createTest");
				// ==============================
				// Navigate to contact Module
				// ==============================
				driver.findElement(By.linkText("Contacts")).click();
				driver.findElement(By.cssSelector("img[title='Create Contact...']")).click();
				WebElement dropdown1=driver.findElement(By.name("salutationtype"));
				Select sel= new Select(dropdown1);
				sel.selectByIndex(1);
				System.out.println( "mr dropdown is selected");
				String firstName="shubham";
				driver.findElement(By.name("firstname")).sendKeys(firstName);
				System.out.println(firstName+"is entered as first name");
				String lastName="jaiswal";
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				System.out.println(lastName+"is entered as last name");
				String pid=driver.getWindowHandle();
				System.out.println("pid windowhandle is captured");
				driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img")).click();
				Set<String> cids=driver.getWindowHandles();
				for(String id:cids) {
					String title=driver.switchTo().window(id).getTitle();
					System.out.println(title);
					if(!id.equals(pid)) {
						System.out.println("comes to child window");
						break;
					}
				}
				driver.findElement(By.linkText("shubhamautomation_org")).click();
				driver.switchTo().window(pid);
				System.out.println("returned to parent window");
				driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
				
				String verifiedFirstname=driver.findElement(By.id("dtlview_First Name")).getText();
				String verifiedLastname=driver.findElement(By.id("dtlview_Last Name")).getText();
				if(firstName.equals(verifiedFirstname)||lastName.equals(verifiedLastname)) {
					System.out.println("contact is varified");
					test.log(Status.PASS,"contact is varified");
				}else {
					System.out.println("contact is not varified");
					test.log(Status.FAIL, "contact is not varified");
				}

				
				
}
}
