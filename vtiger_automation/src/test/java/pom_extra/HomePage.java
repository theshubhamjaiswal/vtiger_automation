package pom_extra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement threeline;
	
	public WebElement getThreeLine() {
		return threeline;
	}
	
	@FindBy(id="logout_sidebar_link")
	private WebElement logout;
	
	public WebElement getLogOut() {
		return logout;
	}
	
}
