package vtiger.products;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ObjectRepository.HomePage;
import ObjectRepository.ProductPage;
import baseutility.BaseClass;
import generic_utility.WebdriverUtility;

public class CreateProductTest extends BaseClass {

	@Test
	public void runProductTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		ExtentTest test=report.createTest("createOrg");
		// ==============================
		// Navigate to contact Module
		// ==============================
		
		WebdriverUtility wdUtil = new WebdriverUtility(driver);
		
		
		HomePage hp = new HomePage(driver);
		wdUtil.waitAndClick(hp.getContactlink());
		ProductPage pp = new ProductPage(driver);
		pp.getCreateProduct().click();
		String initialPname = "samosha";
		pp.getProductNameField().sendKeys(initialPname);
		pp.getSavebtn().click();
		String verifyPname = pp.getVerifyProductName().getText();

		if (initialPname.equals(verifyPname)) {
			System.out.println("product verified");
			test.log(Status.PASS, "Organization created successfully");

		} else {
			test.log(Status.FAIL, "FAIL : Organization creation failed");
	}
	}
}