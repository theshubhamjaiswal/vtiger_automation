package vtiger.oppo;


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

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OppoPage;
import generic_utility.FileUtility;
import generic_utility.WebdriverUtility;

/**
 * @author shubham
 * @experience Advanced Automation Engineer
 * @component Opportunities Module
 * * Test Case Description:
 * Verifies the end-to-end creation of a New Opportunity in vTiger CRM, 
 * including window handling mechanics to map an Organization association.
 */
public class CreateOptiOppoGemTest {


    private WebDriver driver;
    private FileUtility fUtil;
    private WebdriverUtility wUtil;
    private LoginPage lp;
    private HomePage hp;
    private OppoPage opp;

    @BeforeMethod
    public void setUp() throws IOException, ParseException {
        fUtil = new FileUtility();
        String browser = fUtil.getDataFromJsonFile("bro");
        String url = fUtil.getDataFromJsonFile("url");

        // Factory pattern approach for dynamic browser configuration
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

        // Environment Navigation & Session Initialization
        driver.get(url);
        lp = new LoginPage(driver);
        lp.login();
        System.out.println("LOG: Session authentication successful.");
    }

    @Test
    public void createOpportunityAndVerifyTest() {
        hp = new HomePage(driver);
        opp = new OppoPage(driver);
        
        // Target Test Data Matrix (Preserved exactly)
        String initialOppName = "chaiii";

        // Step 1: Navigate to Opportunity Creation Panel
        hp.getOpplink().click();
        opp.getCreateOppIcon().click();
        opp.getOppNameField().sendKeys(initialOppName);

        // Step 2: Complex Window Handling Protocol
        String parentWindowId = driver.getWindowHandle();
        System.out.println("LOG: Stored master window reference ID.");
        
        opp.getSelectOrgDrop().click(); // Activates child look-up lookup pop-up
        Set<String> allWindowIds = driver.getWindowHandles();

        for (String currentWindowId : allWindowIds) {
            String runtimeTitle = driver.switchTo().window(currentWindowId).getTitle();
            System.out.println("LOG: Inspecting frame layer -> " + runtimeTitle);
            
            if (!currentWindowId.equals(parentWindowId)) {
                System.out.println("LOG: Diverted driver driver context to child window.");
                break;
            }
        }

        // Step 3: Perform Child Component selection & safely revert focus
        opp.getSelectOrg().click();
        driver.switchTo().window(parentWindowId);
        System.out.println("LOG: Context restored to master application window.");

        // Step 4: Persistent Layer Submission & Validation
        opp.getSaveBtn().click();
        String verifiedOppName = opp.getVerifyOppName().getText();

        // Advanced Validation Engine (Replaces structural print streams)
        Assert.assertEquals(verifiedOppName, initialOppName, 
            "CRITICAL: UI Validation Failed! Opportunity record string mismatch.");
        System.out.println("LOG: System validation match confirmed.");
    }

    @AfterMethod
    public void tearDown() {
        // Safe contextual application teardown sequence
        try {
            if (hp != null) {
                WebElement profileIcon = hp.getProfileicon();
                wUtil.hover(profileIcon);
                hp.getSignoutLink().click();
                System.out.println("LOG: User logout processed securely.");
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: Post-condition logout sequence bypassed: " + e.getMessage());
        } finally {
            // Defends against rogue, unclosed driver instances hanging in background memory
            if (driver != null) {
                driver.quit();
                System.out.println("LOG: Test automation run safely terminated.");
            }
        }
    }
}