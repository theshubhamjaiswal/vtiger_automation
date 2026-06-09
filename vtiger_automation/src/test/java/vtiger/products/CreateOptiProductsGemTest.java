package vtiger.products;

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
import ObjectRepository.ProductPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

/**
 * @author shubham
 * @experience Automation Tester | 2 Years
 * @component Products Module
 * * Test Case Description:
 * Verifies the end-to-end flow of launching the application, creating a new product record,
 * and performing a validation check on the submitted product name.
 */
public class CreateOptiProductsGemTest{ 

    private WebDriver driver;
    private FileUtility fUtil;
    private WebdriverUtility wUtil;
    private LoginPage lp;
    private HomePage hp;
    private ProductPage pp;

    @BeforeMethod
    public void setUp() throws IOException, ParseException {
        fUtil = new FileUtility();
        String browser = fUtil.getDataFromJsonFile("bro");
        String url = fUtil.getDataFromJsonFile("url");

        // Dynamic multi-browser initialization matrix
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

        // Establish environmental session baseline
        driver.get(url);
        lp = new LoginPage(driver);
        lp.login();
        System.out.println("LOG: Application login successful.");
    }

    @Test
    public void createProductAndVerifyTest() {
        hp = new HomePage(driver);
        pp = new ProductPage(driver);
        
        // Exact target data payload preserved
        String initialPname = "samosha";

        // Step 1: Navigate to Products module and click create icon
        hp.getProductlink().click();
        pp.getCreateProduct().click();
        
        // Step 2: Populating the payload and submitting form
        pp.getProductNameField().sendKeys(initialPname);
        pp.getSavebtn().click();
        System.out.println("LOG: Product form submitted securely.");

        // Step 3: Extracting runtime UI properties for assertions
        String verifyPname = pp.getVerifyProductName().getText();

        // Structural Hard Assertion (Replaces brittle if-else logging)
        Assert.assertEquals(verifyPname, initialPname, 
            "CRITICAL EXCEPTION: Displayed Product Name does not match expected input!");
        System.out.println("LOG: Product validation checks passed cleanly.");
    }

    @AfterMethod
    public void tearDown() {
        // Step 4: Safe session termination protocols
        try {
            if (hp != null) {
                WebElement profileIcon = hp.getProfileicon();
                wUtil.hover(profileIcon);
                hp.getSignoutLink().click();
                System.out.println("LOG: Security context cleared; user logged out.");
            }
        } catch (Exception e) {
            System.out.println("WARNING: Logout interaction bypassed: " + e.getMessage());
        } finally {
            // Guarantees no headless or lingering background browser processes remain open
            if (driver != null) {
                driver.quit();
                System.out.println("LOG: WebDriver driver engine shutdown complete.");
            }
        }
    }
}