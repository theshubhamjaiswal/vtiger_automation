package cbt_xml_executions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OrgTest {
	WebDriver driver;
	
	
	@Parameters("bro")
	@Test
	public void createOrg(String Browser) throws InterruptedException {
		if(Browser.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(Browser.equals("edge")) {
			driver= new EdgeDriver();
		}else if(Browser.equals("firefox")) {
			driver= new FirefoxDriver();
		}else {
			driver=new ChromeDriver();
		}
		Thread.sleep(1000);
		System.out.println("Org created");
		driver.quit();
	}
	
}
