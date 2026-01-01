package dataproviders;

import org.testng.annotations.DataProvider;

public class DataprovidersSampleTest {
    // carina : where "Data1", "Data2", "Data3" in this example is the tests unique identifiers.
    @DataProvider(name = "dataprovider")
    public static Object[][] dataprovider() {
        return new Object[][]{
                {"TUID1", "chaker.nehos@yopmail.com", "Admin123!", "Veuillez vérifier votre email/ mot de passe"},
                {"TUID2", "chaker.nehos1@yopmail.com", "Admin1234!", "Veuillez vérifier votre email/ mot de passe"},
                {"TUID", "chaker.nehos2@yopmail.com", "Admin12345!", "Veuillez vérifier votre email/ mot de passe"},
                //{"DATA4", "chaker.nehos@yopmail.com", "", "Adresse email est obligatoire"},
                //{"DATA5", "", "Admin1234!", "Mot de passe est obligatoire"},
                //{"DATA6", "", "", "Adresse email est obligatoire \nMot de passe est obligatoire"}
        };
    }
}
