package pom_extra;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

public class SauceDemoTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		FileUtility futil= new FileUtility();
		String BROWSER=futil.getDataFromJsonFile("bro");
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		
		driver.get("https://www.saucedemo.com/");
		LoginPage lp= new LoginPage(driver);
		WebElement username=lp.getUn();
		WebElement password=lp.getPwd();
		WebElement loginbtn=lp.getLoginBtn();
		
		username.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
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
	}
}
