package com.braindigit.addagent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelconnection {
	
	
	public String[][] getExcelData(String fileName, String sheetName, int r, int c) throws EncryptedDocumentException, InvalidFormatException {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = WorkbookFactory.create(fs);
			

			
			arrayExcelData = new String[r][c];
			
			for (int i= 0 ; i < r; i++) {

				for (int j=0; j < c; j++) {
					Cell cell = wb.getSheet(sheetName).getRow(i).getCell(j);
					CellType type = cell.getCellTypeEnum();
                    if (type == CellType.NUMERIC) {                 	 
                 	  cell.setCellType(CellType.STRING);                 	 
                    }
					arrayExcelData[i][j] = cell.getStringCellValue();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} 
		return arrayExcelData;
	}
	
	public static int getRowCount (String path, String Sheet)

    {

        try

             {

                 FileInputStream fis = new FileInputStream(path);

                 Workbook wb = WorkbookFactory.create(fis);

                 return wb.getSheet(Sheet).getLastRowNum();

                    }

                    catch (Exception e)

                    {

                  	  return 0;

                    }

    }
	                    

}
