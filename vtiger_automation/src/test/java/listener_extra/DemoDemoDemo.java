package listener_extra;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseutility.BaseClass;
@Listeners(listeners_utility.ListImplement.class)
public class DemoDemoDemo extends BaseClass {
 @Test
	public void createcity() {
		System.out.println("city created");
	}
 @Test
	public void modifycity() {
	 Assert.assertTrue(false);
		System.out.println("city modied");
	}
 
 
 @Test(dependsOnMethods = "modifycity")
	public void deletecity() {
		System.out.println("city deleete");
	}
}
