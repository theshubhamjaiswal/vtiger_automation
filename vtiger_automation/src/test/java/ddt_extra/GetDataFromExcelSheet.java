package ddt_extra;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetDataFromExcelSheet {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
	FileInputStream fis=new FileInputStream("./src/test/resources/testscriptdataorg.xlsx");
	
	Workbook wb=WorkbookFactory.create(fis);
	
	Sheet sh=wb.getSheet("org");   //must be interface and .ss  (not.sl)
	
	Row row=sh.getRow(8);    // numbering start from 0
	
	Cell cell=row.getCell(0);  //numbering start from 0
	
	String value=cell.getStringCellValue();
	System.out.println(value);
	
	wb.close();
	fis.close();
	
	
}
}
