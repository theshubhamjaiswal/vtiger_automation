package types_of_execution;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ContactsTest {
	WebDriver driver;
	@Test
	public void createContactsTest() throws InterruptedException {
		driver= new ChromeDriver();
		System.out.println("contact created");
		Thread.sleep(2000);
		driver.quit();
	}
	@Test
	public void modifyContactsTest() throws InterruptedException {
		driver= new ChromeDriver();
		System.out.println("contact modified");
		Thread.sleep(2000);
		driver.quit();
	}
	@Test
	public void deleteContactsTest() throws InterruptedException {
		driver= new ChromeDriver();
		System.out.println("contact deleted");
		Thread.sleep(2000);
		driver.quit();
	}
}
