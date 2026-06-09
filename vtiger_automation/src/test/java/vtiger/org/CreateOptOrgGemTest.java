package vtiger.org;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrganPage;
import generic_utility.FileUtility;
import generic_utility.JavaUtility; // Recommended optimization
import generic_utility.WebdriverUtility;

/**
 * @author shubham
 * @experience Advanced Automation Engineer
 * @component Organizations Module
 * * Test Case Description:
 * Verifies the dynamic creation of a new Organization with randomized unique data
 * and validates the creation entry using hard assertions.
 */
public class CreateOptOrgGemTest {

    private WebDriver driver;
    private FileUtility fUtil;
    private WebdriverUtility wUtil;
    private LoginPage lp;
    private HomePage hp;
    private OrganPage op;

    @BeforeMethod
    public void setUp() throws IOException, ParseException {
        fUtil = new FileUtility();
        String browser = fUtil.getDataFromJsonFile("bro");
        String url = fUtil.getDataFromJsonFile("url");
        
        // Note: credentials are kept here since your original script passed them explicitly
        String username = fUtil.getDataFromJsonFile("un");
        String password = fUtil.getDataFromJsonFile("pwd");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }

        wUtil = new WebdriverUtility(driver);
        wUtil.maximizeWindow();
        wUtil.implicitWait();

        driver.get(url);

        // Explicit login implementation using retrieved JSON properties
        lp = new LoginPage(driver);
        lp.getUnfield().sendKeys(username);
        lp.getPwdfield().sendKeys(password);
        lp.getLoginbtn().click();
        System.out.println("LOG: Authentication successful.");
    }

    @Test
    public void createOrganizationAndVerifyTest() {
        hp = new HomePage(driver);
        op = new OrganPage(driver);

        // Navigation Context
        hp.getOrglink().click();
        op.getPlusicon().click();

        // Unique Test Data generation (Kept intact)
        int randomNum = (int) (Math.random() * 1000);
        String expectedOrgName = "AutomationWithPiyush_" + randomNum;

        op.getOrgnamefield().sendKeys(expectedOrgName);
        op.getSavebtn().click();
        System.out.println("LOG: Organization submission recorded.");

        // UI Assertion Check
        String actualOrgName = op.getVerifyOrgNameField().getText();
        
        Assert.assertEquals(actualOrgName, expectedOrgName, 
            "CRITICAL MISMATH: Created Organization record does not match input data!");
        System.out.println("LOG: Verified validation entry -> " + actualOrgName);
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (hp != null) {
                WebElement profileIcon = hp.getProfileicon();
                // Optimization: Replaced raw Actions block with your utility wrapper
                wUtil.hover(profileIcon); 
                hp.getSignoutLink().click();
                System.out.println("LOG: Application logout executed cleanly.");
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: Error during teardown step: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("LOG: Target browser context safely destroyed.");
            }
        }
    }
}