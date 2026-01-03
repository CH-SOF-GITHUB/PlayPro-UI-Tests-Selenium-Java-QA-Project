package dataproviders;

import org.testng.annotations.DataProvider;

public class DataprovidersSampleTest {
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
}
