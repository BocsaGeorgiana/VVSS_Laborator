package steps;

import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    @Step
    public void checkHomePageMessage(String mess){ Assertions.assertEquals(loginPage.checkHomeMessage(), mess);}

    @Step
    public void goToSignIn(){ loginPage.pressSignIn(); }

    @Step
    public void checkMessageBeforeLogin(String mess) { Assertions.assertEquals(loginPage.checkBeforeLogin(), mess); }

    @Step
    public void writeEmail(String keyword){
        loginPage.enterUsername(keyword);
    }

    @Step
    public void writePassword(String keyword){
        loginPage.enterPassword(keyword);
    }

    @Step
    public void pressSignIn(){
        loginPage.clickSignIn();
    }

    @Step
    public void checkMessageAfterLogin(String mess) { Assertions.assertTrue(loginPage.checkAfterLogin().contains(mess)); }

}
