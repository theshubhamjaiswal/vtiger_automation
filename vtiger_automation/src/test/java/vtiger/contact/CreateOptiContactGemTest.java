package vtiger.contact;



import java.io.IOException;
import java.util.Set;
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

import ObjectRepository.ContactPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

/**
 * @author shubham
 * @experience Automation Tester | 2 Years
 * @testsuite Regression Suite
 * 
 * Test Case Objective: 
 * Automate the end-to-end flow of creating a new Contact linked with an 
 * Organization in the vTiger CRM application, including child-window handling.
 * 
 * Test Steps:
 * 1. Fetch environment configurations and launch the specified browser.
 * 2. Log into the vTiger CRM application using property/JSON credentials.
 * 3. Navigate to the Contacts Module and click the 'Create Contact' icon.
 * 4. Fill in Personal Details (Salutation, First Name, Last Name).
 * 5. Handle the child window pop-up to select an existing Organization.
 * 6. Return to the parent window and save the Contact records.
 * 7. Validate successful Contact creation using TestNG Assertions.
 * 8. Perform post-condition cleanup (Logout and terminate browser session).
 */
public class CreateOptiContactGemTest {

    // Object declarations for framework reuse
    private WebDriver driver;
    private FileUtility futil;
    private WebdriverUtility wUtil;
    private LoginPage lp;
    private HomePage hp;
    private ContactPage cp;

    @BeforeMethod
    public void setUp() throws IOException, ParseException {
        futil = new FileUtility();
        String browser = futil.getDataFromJsonFile("bro");
        String url = futil.getDataFromJsonFile("url");

        // Dynamic Cross-Browser Initialization
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

        // Step 1 & 2: Navigate and Login
        driver.get(url);
        lp = new LoginPage(driver);
        lp.login();
        System.out.println("INFO: Login successful.");
    }

    @Test
    public void createContactWithOrganizationTest() {
        hp = new HomePage(driver);
        cp = new ContactPage(driver);

        // Test Data (Preserved exactly as provided)
        String firstName = "shubham";
        String lastName = "jaiswal";

        // Step 3: Navigate to Contact Module
        hp.getContactlink().click();
        cp.getCreateContactIcon().click();

        // Step 4: Fill Basic Contact Information
        WebElement dropdown1 = cp.getFirstNameDrop();
        wUtil.selectDropdownByIndex(dropdown1, 1);
        System.out.println("INFO: 'Mr.' salutation dropdown selected.");

        cp.getFirstNameField().sendKeys(firstName);
        System.out.println("INFO: First Name entered: " + firstName);
        
        cp.getLastNameField().sendKeys(lastName);
        System.out.println("INFO: Last Name entered: " + lastName);

        // Step 5: Window Handling (Parent to Child Window Switch)
        String parentWindowId = driver.getWindowHandle();
        System.out.println("INFO: Parent window handle captured.");
        
        cp.getSelectoOrgIcon().click(); // Triggers organization look-up child window
        Set<String> allWindowIds = driver.getWindowHandles();

        for (String windowId : allWindowIds) {
            String title = driver.switchTo().window(windowId).getTitle();
            System.out.println("INFO: Switched to Window Title -> " + title);
            
            if (!windowId.equals(parentWindowId)) {
                System.out.println("INFO: Context successfully shifted to Child Window.");
                break;
            }
        }

        // Interact with Child Window elements
        cp.getSelectOrgLink().click();

        // Step 6: Return context back to Parent Window
        driver.switchTo().window(parentWindowId);
        System.out.println("INFO: Context safely returned to Parent Window.");
        
        cp.getSaveContact().click();

        // Step 7: Industry-Standard TestNG Assertions (Replaces structural if-else blocks)
        String verifiedFirstname = cp.getVerifyFirstName().getText();
        String verifiedLastname = cp.getVerifyLastName().getText();
        
        Assert.assertEquals(verifiedFirstname, firstName, "CRITICAL ERROR: First Name verification failed!");
        Assert.assertEquals(verifiedLastname, lastName, "CRITICAL ERROR: Last Name verification failed!");
        System.out.println("INFO: Contact details successfully verified against target records.");
    }

    @AfterMethod
    public void tearDown() {
        // Step 8: Safe Post-Condition Teardown Routine
        try {
            if (hp != null) {
                WebElement profileIcon = hp.getProfileicon();
                wUtil.hover(profileIcon);
                hp.getSignoutLink().click();
                System.out.println("INFO: Logout successful.");
            }
        } catch (Exception e) {
            System.out.println("WARNING: Logout sequence interrupted: " + e.getMessage());
        } finally {
            // Ensures browser execution tree terminates properly to prevent memory leaks
            if (driver != null) {
                driver.quit();
                System.out.println("INFO: Browser closed successfully.");
            }
        }
    }
}