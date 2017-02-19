package examples;

import java.io.*;
import org.apache.poi.hssf.usermodel.*;

public class ReadDataFromFile {

	public static void main(String args[]) {

		try {
			FileInputStream file = new FileInputStream(new File(
					"F:/SOAPUIWorkspace/GeoLocation.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			System.out.println(workbook);
			HSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println(sheet);
			String heading = sheet.getRow(0).getCell(0).getStringCellValue();
			System.out.println(heading);
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
