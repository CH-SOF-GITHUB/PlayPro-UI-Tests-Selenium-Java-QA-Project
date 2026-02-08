package Data_Driven.testdata;

import Data_Driven.utility.Constant;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;

import static Data_Driven.utility.ExcelUtils.getExcelSheet;

public class DataProviderUtils {
    // carina : where "Data1", "Data2", "Data3" in this example is the tests unique identifiers.
    @DataProvider(name = "dataprovider")
    public static Object[][] dataprovider() {
        return new Object[][]{
                {"TUID1", "chaker.nehos@yopmail.com", "Admin123!", "Veuillez vérifier votre email/ mot de passe"},
                {"TUID2", "chaker.nehos1@yopmail.com", "Admin1234!", "Veuillez vérifier votre email/ mot de passe"},
                {"TUID3", "chaker.nehos2@yopmail.com", "Admin12345!", "Veuillez vérifier votre email/ mot de passe"},
                {"TUID4", "chaker.nehos@yopmail.com", "", "Mot de passe est obligatoire"}, // data with empty password
                {"TUID5", "", "Admin1234!", "Adresse email est obligatoire"} // data with empty email
        };
    }

    public static Object[][] GoToReservationsData() {
        return new Object[][]{
                {"TUID1", "", "", ""},
                {"TUID2", "", "", ""}
        };
    }

    @DataProvider(name = "loginExcelData")
    public static Object[][] getLoginExcelData() {
        XSSFSheet sheet = getExcelSheet(Constant.Path_TestData + Constant.File_TestData, "Feuille1");
        int rowCount = sheet.getPhysicalNumberOfRows();

        // Assuming first row is header, so data starts from row 1
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    data[i - 1][j] = ""; // or null
                    continue;
                }
                switch (cell.getCellType()) {
                    case STRING:
                        data[i - 1][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i - 1][j] = cell.getNumericCellValue();
                        break;
                    case BOOLEAN:
                        data[i - 1][j] = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        data[i - 1][j] = "";
                        break;
                    default:
                        data[i - 1][j] = "";
                }
            }
        }
        return data;
    }
}
