package ddt_extra;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetDataFromJsonFile {
public static void main(String[] args) throws IOException, ParseException {
//step 1 create java representation object of physical file
	FileReader fr= new FileReader("./src/test/resources/commondata.json");
//steep 2 we have a class jsonparser make object of it
	JSONParser parser=new JSONParser();
// step 3 call the nonstatic method of jsonparser which is parse() which return type is object or we have to store it into object
	Object obj=parser.parse(fr);   //that parse method accept reador thats why filereader is used
//step 4 downcast object into json object 	
	JSONObject jObj=(JSONObject)obj;
//step 5 call a method .get() where you put key and get value so u are gaining object to get into string by to string ()
	String URL= jObj.get("url").toString();
	System.out.println(URL);
// close the reader file
	
	fr.close();
	
}
}
