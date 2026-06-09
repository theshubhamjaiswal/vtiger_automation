package vtiger.products;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.ProductPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class CreateProductTest{
//	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ParseException {
	WebDriver driver;
	@Test
			public void runProductTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
			// ==============================
			// Browser Setup
			// ==============================
			//WebDriver driver = null;
			
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
			String r=futil.getDataFromExcelFile("contact", 2, 0);
			System.out.println(r);
			// ==============================
			// Navigate to contact Module
			// ==============================
			HomePage hp=new HomePage(driver);
			hp.getProductlink().click();
			ProductPage pp=new ProductPage(driver);
			pp.getCreateProduct().click();
			String initialPname="samosha";
			pp.getProductNameField().sendKeys(initialPname);
			pp.getSavebtn().click();
			String verifyPname=pp.getVerifyProductName().getText();

			if(initialPname.equals(verifyPname)) {
				System.out.println("product verified");
			}else {
				System.out.println("product is not verified");
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

