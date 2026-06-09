package learning_data_provider;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;
import pom_extra.HomePage;
import pom_extra.LoginPage;

public class SauceDemoByDataProTest {
	//public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
	
		@DataProvider
		public Object[][] getData() {
		Object[][] obj= new Object[6][2];
		
		obj[0][0]="standard_user";
		obj[0][1]="secret_sauce";
		obj[1][0]="locked_out_user";
		obj[1][1]="secret_sauce";
		obj[2][0]="problem_user";
		obj[2][1]="secret_sauce";
		obj[3][0]="performance_glitch_user";
		obj[3][1]="secret_sauce";
		obj[4][0]="error_user";
		obj[4][1]="secret_sauce";
		obj[5][0]="visual_user";
		obj[5][1]="secret_sauce";
		return obj;
		}
	
		@Test(dataProvider = "getData")
		public void run(String un, String pwd) throws FileNotFoundException, IOException, ParseException {
		FileUtility futil= new FileUtility();
		String BROWSER=futil.getDataFromJsonFile("bro");
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		
	//System.setProperty("webdriver.edge.driver","pat of file where u store the .exe file of edgedriver" );	
	//	WebDriver driver = new EdgeDriver();
		
		
		driver.get("https://www.saucedemo.com/");
		LoginPage lp= new LoginPage(driver);
		WebElement username=lp.getUn();
		WebElement password=lp.getPwd();
		WebElement loginbtn=lp.getLoginBtn();
		
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbtn.click();
		
		Boolean tag=driver.findElement(By.xpath("//div[text()='Swag Labs']")).isDisplayed();
		if(tag) {
			System.out.println("verification complete");
		}
		WebdriverUtility wutil=new WebdriverUtility(driver);
		Dimension swin=wutil.getSizeOfWindow();
		System.out.println(swin);
		HomePage hp=new HomePage(driver);
		WebElement threeline=hp.getThreeLine();
		threeline.click();
		
		
		WebElement logout=hp.getLogOut();
		wutil.waitForElementClickable(logout, 10);
		logout.click();
		driver.quit();
	}
}
