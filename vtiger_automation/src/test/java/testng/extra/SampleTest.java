package testng.extra;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class SampleTest {

	
	
	@Test
	public void case1() {
		Reporter.log("this is test");
	}
	@Test
	public void case2() {
		Reporter.log("this is test 2");
	}
}
