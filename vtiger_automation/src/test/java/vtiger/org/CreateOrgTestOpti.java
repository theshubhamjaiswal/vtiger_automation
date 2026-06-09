package vtiger.org;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrganPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class CreateOrgTestOpti {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ParseException {
		WebDriver driver = null;
		
		FileUtility futil= new FileUtility();
		String BROWSER=futil.getDataFromJsonFile("bro");
		String URL=futil.getDataFromJsonFile("url");
		String USERNAME=futil.getDataFromJsonFile("un");
		String PASSWORD=futil.getDataFromJsonFile("pwd");
		
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		WebdriverUtility Wutil= new WebdriverUtility(driver);
		Wutil.maximizeWindow();
		Wutil.implicitWait();
		
		LoginPage lp=new LoginPage(driver);
		lp.getUnfield().sendKeys(USERNAME);
		lp.getPwdfield().sendKeys(PASSWORD);
		lp.getLoginbtn().click();

		System.out.println("Login successful");

		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
	
		OrganPage op=new OrganPage(driver);
		op.getPlusicon().click();
	
		int randomNum = (int) (Math.random() * 1000);

		String expectedOrgName = "AutomationWithPiyush_" + randomNum;

		
		op.getOrgnamefield().sendKeys(expectedOrgName);

		op.getSavebtn().click();
		System.out.println("Organization creation form submitted");

	
		String actualOrgName=op.getVerifyOrgNameField().getText();
		if (actualOrgName.equals(expectedOrgName)) {

			System.out.println("PASS : Organization created successfully");
			System.out.println("Created Organization Name : " + actualOrgName);

		} else {

			System.out.println("FAIL : Organization creation failed");
			System.out.println("Expected : " + expectedOrgName);
			System.out.println("Actual   : " + actualOrgName);
		}

		WebElement profileIcon=hp.getProfileicon();
		Actions actions = new Actions(driver);
		actions.moveToElement(profileIcon).perform();
		hp.getSignoutLink().click();
		System.out.println("Logout successful");
		Thread.sleep(3000);
		driver.quit();
		System.out.println("Browser closed successfully");

	}
}


