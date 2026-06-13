package listener_extra;

import  org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseutility.BaseClass;
@Listeners(listeners_utility.List_Imp.class)
public class DemoTest extends BaseClass{
     @Test
	private void createCity() {
	System.out.println("city created.....");
	}
     @Test
	public void modifyCity(){
    	 Assert.assertTrue(false);
	System.out.println("modify created......");	
	}
     @Test(dependsOnMethods = "modifyCity")
	private void deleteCity() {
	System.out.println("city deleted....");

	}
     
     
}
