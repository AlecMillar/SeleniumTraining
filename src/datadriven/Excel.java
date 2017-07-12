package datadriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {

	private static int numRows;
	private static int numColumns;

	// This method will call and return Excel data into a double array
	public static String[][] get(String filename) {
		// Create a local file and open Excel workbook and sheet
		String[][] dataTable = null;
		File file = new File(filename);
		try {
			// Create a file input stream to read Excel workbook & worksheet
			FileInputStream xlfile = new FileInputStream(file);
			@SuppressWarnings("resource")
			HSSFWorkbook xlwb = new HSSFWorkbook(xlfile);
			HSSFSheet xlSheet = xlwb.getSheetAt(0);

			// Get the number of rows & columns
			numRows = xlSheet.getLastRowNum() + 1;
			numColumns = xlSheet.getRow(0).getLastCellNum();

			System.out.println("ROWS: " + numRows + " x COLUMNS: " + numColumns);

			// Create Double Array datatable ROWS x COLUMNS
			dataTable = new String[numRows][numColumns];

			for (int i=0; i<numRows; i++) {
				// For each row, create an HSSF row, then iterate through the "column"
				// For each "column", create an HSSF cell to grab the value of the specified cell (i,j)
				HSSFRow xlRow = xlSheet.getRow(i);
				for (int j=0; j < numColumns; j++) {
					HSSFCell xlCell = xlRow.getCell(j);
					//Convert xlCell (currently HSSFCell) to a String
					String value = cellToString(xlCell);
					dataTable[i][j] = value;
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("ERROR, file not found: " + e.toString());
		} catch (IOException e) {
			System.out.println("ERROR, could not read file: " + e.toString());
		}

		// Return dataTable
		return dataTable;
	}

	// This method will convert an Excel cell value into a string value.
	@SuppressWarnings("deprecation")
	private static String cellToString(HSSFCell cell) {
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC: //0
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING: //1
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: //2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case HSSFCell.CELL_TYPE_BLANK: //3
			result = "-";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: //4
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR: //5
			throw new RuntimeException ("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: " + type);
		}
		return result.toString();
	}
}
