package tests;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.RegisterSteps;
import java.io.FileReader;
import java.io.IOException;

@ExtendWith(SerenityJUnit5Extension.class)
public class RegisterTests {

    String CSV_PATH_VALID = "src\\test\\resources\\RegisterData.csv";
    String CSV_PATH_INVALID = "src\\test\\resources\\RegisterInvalidData.csv";
    private CSVReader csvReader;
    String[] csvCell;

    @Managed(driver = "chrome")
    WebDriver driver;
    WebDriverWait wait;

    @Steps
    public RegisterSteps registerSteps;

    //email needs to be changed before run successfulLogin() in RegisterData.csv
    //because we cannot create 2 accounts with the same email
    @Test
    public void successfulLogin() throws IOException, CsvValidationException, InterruptedException {

        csvReader = new CSVReader(new FileReader(CSV_PATH_VALID));
        CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(new FileReader(CSV_PATH_VALID));
        // skip first row
        csvReader.skip(0);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        driver.manage().window().maximize();
        while ((csvCell = csvReader.readNext()) != null) {
            String name1 = csvCell[0];
            String name2 = csvCell[1];
            String email = csvCell[2];
            String password = csvCell[3];
            String repassword = csvCell[4];

            registerSteps.enterName1(name1);
            registerSteps.enterName2(name2);
            registerSteps.checkNews();
            registerSteps.enterEmailAddress(email);
            //save entered email
            registerSteps.enterPass(password);
            registerSteps.reenterPass(repassword);
            //check password's strength is 'Strong'
            registerSteps.checkStrnght("Strong");
            registerSteps.createNewAccount();
            Thread.sleep(5000);
            //check if the register was successful
            registerSteps.checkMessage("Thank you for registering with Main Website Store.");
            //test for checking a user subscribed to newsletter
            registerSteps.checkNewMess("You are subscribed");
            //check if email address on the info page
            //is same with the email on the register page
            registerSteps.checkInfo(email);
            driver.close();
        }

    }

    @Test
    public void unsuccessfulLogin() throws IOException, CsvValidationException {

        csvReader = new CSVReader(new FileReader(CSV_PATH_INVALID));
        CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(new FileReader(CSV_PATH_INVALID));
        // skip first row
        csvReader.skip(0);
        while ((csvCell = csvReader.readNext()) != null) {
            driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
            driver.manage().window().maximize();

            String name1 = csvCell[0];
            String name2 = csvCell[1];
            String email = csvCell[2];
            String password = csvCell[3];
            String repassword = csvCell[4];

            registerSteps.enterName1(name1);
            registerSteps.enterName2(name2);
            registerSteps.checkNews();
            registerSteps.enterEmailAddress(email);
            registerSteps.enterPass(password);
            registerSteps.reenterPass(repassword);
            //check password's strength is 'Strong'
            registerSteps.checkStrnght("Strong");
            registerSteps.createNewAccount();
            //error message appears after trying to create an account
            //when a required field is empty
            registerSteps.checkErrorMessage("This is a required field.");
            driver.close();
        }

    }
}
