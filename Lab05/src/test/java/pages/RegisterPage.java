package pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class RegisterPage extends PageObject {

    @FindBy(name="firstname")
    private WebElementFacade firstName;

    @FindBy(name="lastname")
    private WebElementFacade lastName;

    @FindBy(name="email")
    private WebElementFacade email;

    @FindBy(id = "email_address")
    private WebElementFacade emailField;

    @FindBy(name="password")
    private WebElementFacade password;

    @FindBy(name="password_confirmation")
    private WebElementFacade confirmPassword;

    @FindBy(css = "button[title='Create an Account']")
    private WebElementFacade createAccount;

    @FindBy(className="checkbox")
    private WebElementFacade newsletter;

    @FindBy(css = "#password-strength-meter-label")
    private WebElementFacade strength;

    @FindBy(css = "div[data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    private WebElementFacade message;

    @FindBy(css = "#email_address-error")
    private WebElementFacade invalidMessage;

    @FindBy(css = "div[class='box box-newsletter'] p")
    private WebElementFacade newsMess;

    @FindBy(css = "div[class='box box-information'] p")
    private WebElementFacade info;

    public void enterFirstName(String keyword) { firstName.type(keyword);}

    public void enterLastName(String keyword) { lastName.type(keyword);}

    public void enterEmail(String keyword) { email.type(keyword); }

    public void enterPassword(String keyword) {
        password.type(keyword);
    }

    public void reenterPassword(String keyword) { confirmPassword.type(keyword);}

    public void checkNewsletter(){ newsletter.click(); }

    public void createAccount(){
        createAccount.click();
    }

    public String returnPassStrength(){ return strength.getText(); }

    public String registerOk(){
        return message.getText();
    }

    public String errorMessage(){ return invalidMessage.getText(); }

    public String newsMessage() {
        return newsMess.getText(); }

    public String getInfo() {
        return info.getText(); }

//    public String returnEmail(){
//        String variableName = "email";
//        String variableValue = emailField.getAttribute("value");
//        Serenity.getCurrentSession().put(variableName, variableValue);
//        System.out.println("return email page: " + Serenity.getCurrentSession().get(variableName));
//        return (String) Serenity.getCurrentSession().get(variableName);
//    }

}
