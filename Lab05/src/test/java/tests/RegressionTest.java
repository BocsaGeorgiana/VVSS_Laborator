package tests;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.CatalogSteps;
import steps.LoginSteps;
import steps.LogoutSteps;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@ExtendWith(SerenityJUnit5Extension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegressionTest {
    @Managed(driver = "chrome")
    WebDriver driver;


    String CSV_PATH = "src\\test\\resources\\RegressionData.csv";
    private CSVReader csvReader;
    String[] csvCell;

    @Steps
    public LoginSteps loginSteps;

    @Steps
    public CatalogSteps catalogSteps;

    @Steps
    public LogoutSteps logoutSteps;

    @BeforeClass
    public static void openBrowser(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @Order(1)
    public void login_addWishlist_removeWishlist_Logout() throws IOException, CsvValidationException, InterruptedException {

        csvReader = new CSVReader(new FileReader(CSV_PATH));
        CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(new FileReader(CSV_PATH));
        // skip first row
        csvReader.skip(0);
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
        while ((csvCell = csvReader.readNext()) != null) {
            String email = csvCell[0];
            String pass = csvCell[1];

            //LOGIN
            //check message on home page
            loginSteps.checkHomePageMessage("Default welcome msg!");
            loginSteps.goToSignIn();
            //verify message before login
            loginSteps.checkMessageBeforeLogin("Customer Login");
            loginSteps.writeEmail(email);
            loginSteps.writePassword(pass);
            loginSteps.pressSignIn();
            //verify message after login
            loginSteps.checkMessageAfterLogin("Welcome");

            //SEARCH A PRODUCT
            String itemSearched = "hoodie";
            catalogSteps.searchForAnItem(itemSearched);
            catalogSteps.checkSearchedItem(itemSearched);
            catalogSteps.checkNumberOfItems(12);

            //ADD TO WISHLIST
            //check wishlist is empty
            catalogSteps.checkWishlist("You have no items in your wish list.");
            catalogSteps.addItemsToWishlist();
            //check item was added to wishlist
            catalogSteps.countWishlistItems(1);

            //REMOVE FROM WISHLIST
            //remove from wishlist
            catalogSteps.removeFromWishlist();
            //check empty wishlist message
            catalogSteps.checkWishlist("You have no items in your wish list.");

            //LOGOUT
            logoutSteps.showSignOutButton();
            logoutSteps.clickSignOutButton();
            //check logout message
            logoutSteps.checkLogoutMessage("You are signed out");
            driver.close();
        }
    }


}
