package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {

    @FindBy(css = "div[class='panel header'] li[data-label='or'] a")
    private WebElementFacade enterSignIn;

    @FindBy(name = "login[username]")
    private WebElementFacade username;

    @FindBy(name = "login[password]")
    private WebElementFacade password;

    @FindBy(name = "send")
    private WebElementFacade signIn;

    @FindBy(css = ".not-logged-in")
    private WebElementFacade homeMessage;

    @FindBy(css = ".base")
    private WebElementFacade beforeLogin;

    @FindBy(css = ".logged-in")
    private WebElementFacade afterLogin;

    public String checkHomeMessage() { return homeMessage.getText(); }

    public void pressSignIn() { enterSignIn.click(); }

    public void enterUsername(String keyword) { username.type(keyword);}

    public void enterPassword(String keyword) { password.type(keyword);}

    public void clickSignIn() { signIn.click();}

    public String checkBeforeLogin() { return beforeLogin.getText(); }

    public String checkAfterLogin() { return afterLogin.getText(); }
}
