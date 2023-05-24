package steps;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;
import pages.RegisterPage;

public class RegisterSteps {

    RegisterPage registerPage;

    @Step
    public void enterName1(String keyword) {
        registerPage.enterFirstName(keyword);
    }

    @Step
    public void enterName2(String keyword) {
        registerPage.enterLastName(keyword);
    }

    @Step
    public void enterEmailAddress(String keyword) {
        registerPage.enterEmail(keyword);
    }

    @Step
    public void enterPass(String keyword) {
        registerPage.enterPassword(keyword);
    }

    @Step
    public void reenterPass(String keyword) {
        registerPage.reenterPassword(keyword);
    }

    @Step
    public void checkStrnght(String strength) {
        Assertions.assertEquals(registerPage.returnPassStrength(), strength);
    }

    @Step
    public void checkNews(){
        registerPage.checkNewsletter();
    }

    @Step
    public void createNewAccount(){
        registerPage.createAccount();
    }

    @Step
    public void checkMessage(String mess) {
        Assertions.assertEquals(registerPage.registerOk(), mess);
    }

    @Step
    public void checkErrorMessage(String mes) { Assertions.assertEquals(registerPage.errorMessage(), mes);}

    @Step
    public void checkNewMess(String mes) { Assertions.assertTrue(registerPage.newsMessage().contains(mes));}

    @Step
    public void checkInfo(String mes) {
        String message = registerPage.getInfo();
        Assertions.assertTrue(message.contains(mes));

    }
}
