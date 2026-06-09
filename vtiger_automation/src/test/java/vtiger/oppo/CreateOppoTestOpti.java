package vtiger.oppo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OppoPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class CreateOppoTestOpti {
	@Test
	public static void main(String[] args)
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		// ==============================
		// Browser Setup
		// ==============================
		WebDriver driver = null;

		FileUtility futil = new FileUtility();
		String BROWSER = futil.getDataFromJsonFile("bro");
		String URL = futil.getDataFromJsonFile("url");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		WebdriverUtility Wutil = new WebdriverUtility(driver);
		Wutil.maximizeWindow();
		Wutil.implicitWait();

		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.login();
		System.out.println("Login successful");

		// ==============================
		// Navigate to opportunity Module
		// ==============================
		HomePage hp = new HomePage(driver);
		hp.getOpplink().click();
		OppoPage opp = new OppoPage(driver);
		opp.getCreateOppIcon().click();
		String initialoppname="chaiii";
		opp.getOppNameField().sendKeys(initialoppname);
		String pid = driver.getWindowHandle();
		System.out.println("pid windowhandle is captured");
		opp.getSelectOrgDrop().click();
		Set<String> cids = driver.getWindowHandles();
		for (String id : cids) {
			String title = driver.switchTo().window(id).getTitle();
			System.out.println(title);
			if (!id.equals(pid)) {
				System.out.println("comes to child window");
				break;
			}
		}
		opp.getSelectOrg().click();
		driver.switchTo().window(pid);
		System.out.println("returned to parent window");
		
		opp.getSaveBtn().click();
		String verifiedoppname=opp.getVerifyOppName().getText();
		if(initialoppname.equals(verifiedoppname)) {
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
