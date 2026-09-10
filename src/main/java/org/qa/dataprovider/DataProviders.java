package org.qa.dataprovider;

import org.qa.utilities.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.List;

public class DataProviders {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/TestData.xlsx";

    // Create a MethOD to fetch data from Excel Sheet
    private static String[][] FetchSheetData(String sheetName) {
        List<String[]> data = ExcelUtils.getSheetData(FILE_PATH, sheetName);
        String[][] result = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i);
        }
        return result;
    }

    // DataProvider for Login Test
    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginDataForTest() {
        return FetchSheetData("validLoginData");
    }

    @DataProvider(name = "inValidLoginData")
    public static Object[][] invalidLoginDataForTest() {
        return FetchSheetData("invalidLoginData");
    }

}
