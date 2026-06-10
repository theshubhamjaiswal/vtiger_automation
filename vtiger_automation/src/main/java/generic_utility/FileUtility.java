package generic_utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility {
 
	public String getDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("./src/test/resources/commondata.json"));
		JSONObject jobj=(JSONObject) obj;
		String value = jobj.get(key).toString();
		return value;
	}
	public String getDataFromExcelFile(String sheetName,int rowIndex,int cellIndex) throws EncryptedDocumentException, FileNotFoundException, IOException {
		Workbook wb=WorkbookFactory.create(new FileInputStream("./src/test/resources/testscriptdataorg.xlsx"));
		Sheet sh= wb.getSheet(sheetName);
		Row rw=sh.getRow(rowIndex);
		Cell cl=rw.getCell(cellIndex);
		String value=cl.getStringCellValue();
		return value;
	}
	
	
	
	
}
