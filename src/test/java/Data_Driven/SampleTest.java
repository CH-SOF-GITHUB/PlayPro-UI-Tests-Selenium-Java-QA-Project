package Data_Driven;

import Data_Driven.utility.Constant;
import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Arrays;

import static Data_Driven.testdata.DataProviderUtils.getLoginExcelData;
import static Data_Driven.utility.ExcelUtils.getExcelSheet;

public class SampleTest {

    public static void main(String[] args) {
        System.out.println("This is a sample test class.");
        XSSFSheet DataFile = getExcelSheet(Constant.Path_TestData + Constant.File_TestData, "Feuille1");
        if (DataFile == null) {
            System.out.println("Erreur: La feuille Excel n'a pas été chargée. Vérifiez le chemin et le nom de la feuille.");
        } else {
            System.out.println("Sheet Name: " + DataFile.getSheetName());
            System.out.println("Row Count: " + DataFile.getPhysicalNumberOfRows());
        }

        Object [][] data = getLoginExcelData();
        System.out.println(Arrays.deepToString(data));

        // Loop through the rows and print the first cell of each row
        /*
        for (int i = 1; i < DataFile.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = DataFile.getRow(i);
            if (row == null) continue;

            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) continue;

                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        break;
                    case BLANK:
                        System.out.print("BLANK\t");
                        break;
                    default:
                        System.out.print("UNKNOWN\t");
                }
            }
            System.out.println();
        }*/
    }
}
