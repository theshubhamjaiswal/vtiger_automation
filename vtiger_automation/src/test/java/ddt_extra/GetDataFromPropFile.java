package ddt_extra;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetDataFromPropFile {
public static void main(String[] args) throws IOException {
	
	//step 1 create java representation object of physical file
	FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
	//step 2 create object of properties 
	Properties pObj= new Properties();
	//step 3 call a method of properties load() and provide jro into it by which it load all the data
	pObj.load(fis);
	//step 4 call a method getProperty()and provide key into it 
	String BROWSER=pObj.getProperty("bro");
	System.out.println(BROWSER);
	
	// step 4 close jro
	fis.close();
}
}
