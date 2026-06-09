package testng.extra;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DemoTest {

	
	@Test
	public void add() {
		Reporter.log("this is demo",true);
	}
	@Test
	public void add2() {
		Reporter.log("this is demo 2",true);
	}
	
}
