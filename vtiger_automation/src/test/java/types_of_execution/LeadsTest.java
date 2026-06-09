package types_of_execution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LeadsTest {
	WebDriver driver;
	@Test
	public void createLeadsTest() throws InterruptedException {
		driver= new ChromeDriver();
		System.out.println("leads created");
		Thread.sleep(2000);
		driver.quit();
	}
	@Test
	public void modifyLeadsTest() throws InterruptedException {
		driver= new ChromeDriver();
		System.out.println("leads modified");
		Thread.sleep(2000);
		driver.quit();
	}
	@Test
	public void deleteLeadsTest() throws InterruptedException {
		driver= new ChromeDriver();
		System.out.println("leads deleted");
		Thread.sleep(2000);
		driver.quit();
	}
}
