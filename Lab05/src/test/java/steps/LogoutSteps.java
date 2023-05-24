package steps;

import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;
import pages.LogoutPage;

public class LogoutSteps {

    LogoutPage logoutPage;

    @Step
    public void showSignOutButton() { logoutPage.showSignOut();}

    @Step
    public void clickSignOutButton(){ logoutPage.pressSignOut();}

    @Step
    public void checkLogoutMessage(String mess){ Assertions.assertEquals(logoutPage.signOutMessage(),mess); }
}
