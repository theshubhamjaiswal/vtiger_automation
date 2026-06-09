package generic_utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility {
	
	
	 WebDriver driver;
	 Actions act;
	 WebDriverWait wait;
	 
	 
	 public WebdriverUtility(WebDriver driver){
		this.driver=driver;
		this.act=new Actions(driver);
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(15));
				
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public void fullScreenWindow() {
		driver.manage().window().fullscreen();
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	public void waitForElementVisible( WebElement element,int timeoutofsecond) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutofsecond)).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementClickable(WebElement element, int timeoutofsecond) {
		new WebDriverWait(driver, Duration.ofSeconds(timeoutofsecond)).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void selectDropdownByIndex(WebElement element, int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void hover(WebElement element) {
		act.moveToElement(element).build().perform();
	}
	
	public Dimension getSizeOfWindow() {
	Dimension dim=	driver.manage().window().getSize();
	return dim;
	}
	
	
	
	
}
