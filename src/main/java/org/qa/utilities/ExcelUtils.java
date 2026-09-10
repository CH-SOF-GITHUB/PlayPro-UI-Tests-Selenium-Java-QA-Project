package org.qa.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {


    public static List<String[]> getSheetData(String filePath, String sheetName) {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return data;
            }

            // Iterate through rows starting from row 0
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                // Skip header row (row 0)
                if (i <= 0) {
                    continue;
                }

                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    data.add(new String[]{"", "", ""}); // Empty row
                    continue;
                }

                short lastCellNum = row.getLastCellNum();
                if (lastCellNum < 0) {
                    continue;
                }
                String[] rowData = new String[lastCellNum];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = getCellValue(cell);
                }
                data.add(rowData);
            }
            workbook.close();
        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
            e.fillInStackTrace();
        }
        return data;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return cell.toString();
        }
    }

    //private static XSSFSheet ExcelWSheet;
    //private static XSSFWorkbook ExcelWBook;
    // private static XSSFCell Cell;
    //private static XSSFRow Row;

    /* This method is to get the Excel File by path and file name
    public static XSSFSheet getExcelSheet(String fullPath, String sheetName) {
        try {
            // Opn the Excel File
            FileInputStream ExcelFile = new FileInputStream(fullPath);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExcelWSheet;
    }*/

    /* This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }*/

    /* This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            return Cell.getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    } */

    /* This method is to write in the Excel cell, Row num and Col num are the parameters
    public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }*/
}
