package pom_extra;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import generic_utility.FileUtility;

public class TeachRadha {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
	
    FileUtility futil=new FileUtility();
   String Browser= futil.getDataFromJsonFile("bro");
   WebDriver driver= null;
   if(Browser.equalsIgnoreCase("chrome")) {
	   driver=new ChromeDriver();
   }
   
   
   
}
}