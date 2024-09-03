package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	public static HashMap<String, String> storeValues = new HashMap<String, String>();
	
	public static List<HashMap<String,String>> data(String filepath,String sheetname) throws IOException{
		
		List<HashMap<String,String>> myData = new ArrayList<HashMap<String,String>>();
		
		  FileInputStream fis = new FileInputStream(filepath);
		  XSSFWorkbook wb = new XSSFWorkbook(fis);
		  XSSFSheet sheet = wb.getSheet(sheetname);
		  int totalRows = sheet.getLastRowNum();
		  
		  //creating the first row as header
		  XSSFRow headerRow = sheet.getRow(0);
		  
		  //values are always kept from 2nd row , hence index of reading rows should be 1,i=1
		  for(int i=1;i<=totalRows;i++) {
			  XSSFRow currentRow = sheet.getRow(i);
			  
			  HashMap<String,String> currentMap = new HashMap<>();
			  //Columns start from index 0,j=0 ,currentRow.getLastCellNum()-> this returns the total columns in that row
			  for(int j=0;j<currentRow.getLastCellNum();j++) {
				  
				  XSSFCell currentCell = currentRow.getCell(j);
				  //key = header(like-user) and value=cell value of that header in current row (ex- username)
				  //so if there are 3 columns, so hashmap would have 3 key-value pairs
				  currentMap.put(headerRow.getCell(j).toString(), currentCell.toString());
			  }
			  //adding each row -map as single member of the list..
			  myData.add(currentMap);
		  }
		  
		  return myData;
	}

}
