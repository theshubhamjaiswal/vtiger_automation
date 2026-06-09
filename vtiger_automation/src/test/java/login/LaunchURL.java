package login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class LaunchURL {
	
	public static void main(String[] args) throws InterruptedException {
		// ==============================
		// Browser Setup
		// ==============================
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// ==============================
		// Launch Applicationz
		// ==============================
		driver.get("http://localhost:8888/");

		// ==============================
		// Login to CRM Application
		// ==============================
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("manager");

		driver.findElement(By.id("submitButton")).click();

		System.out.println("Login successful");

		
		
		// ==============================
					// Logout from Application
					// ==============================
					WebElement profileIcon = driver.findElement(
							By.cssSelector("img[src='themes/softed/images/user.PNG']"));

					Actions actions = new Actions(driver);

					actions.moveToElement(profileIcon).perform();

					driver.findElement(By.linkText("Sign Out")).click();

					System.out.println("Logout successful");

					// ==============================
					// Close Browser
					// ==============================
					Thread.sleep(3000);

					driver.quit();

					System.out.println("Browser closed successfully");
				
		
		
	}
}
