package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LogoutPage extends PageObject {

    @FindBy(css = ".customer-name")
    private WebElementFacade showSignOut;

    @FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")
    private WebElementFacade signOutButton;

    @FindBy(css = ".base")
    private WebElementFacade signOutMessage;

    public void showSignOut() { showSignOut.click(); }

    public void pressSignOut(){ signOutButton.click();}

    public String signOutMessage(){ return signOutMessage.getText(); }
}
