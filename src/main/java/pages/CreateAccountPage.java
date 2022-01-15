package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends BasePage{
    private @FindBy(xpath = "//input[@id='ap_email']")
    WebElement emailField;

    private @FindBy(xpath = "//input[@id='ap_password']")
    WebElement passwordField;

    private @FindBy(xpath = "//input[@id='ap_password_check']")
    WebElement passwordCheckField;

    private @FindBy(xpath = "//input[@id='continue']")
    WebElement continueButton;

    private @FindBy(xpath = "//div[contains(text(),'Enter your name')]")
    WebElement nameAlertMessage;

    private @FindBy(xpath = "//div[contains(text(),'Wrong or Invalid email address')]")
    WebElement emailAlertMessage;

    private @FindBy(xpath = "//div[@id='auth-password-invalid-password-alert']//div[contains(text(),'Minimum 6 characters required')]")
    WebElement passwordAlertMessage;

    private @FindBy(xpath = "//div[contains(text(),'Passwords must match')]")
    WebElement passwordConfirmAlertMessage;

    private @FindBy(xpath = "//input[@id='ap_customer_name']")
    WebElement nameField;


   public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public void fillEmailField(String emailAccount){
       emailField.sendKeys(emailAccount);
    }

    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void inputPassword(String password){
       passwordField.sendKeys(password, Keys.ENTER);
    }

    public void fillPasswordCheckField(String rePassword) {
        passwordCheckField.sendKeys(rePassword);
    }

    public void fillNameField(String name){
       nameField.sendKeys(name);
    }

    public void continueButtonClick(){
       continueButton.click();
    }

    public WebElement getContinueButton(){
       return continueButton;
    }

    public boolean nameAlertMessageVisibility(){
       return nameAlertMessage.isDisplayed();
    }

    public boolean emailAlertMessageVisibility(){
       return emailAlertMessage.isDisplayed();
    }

    public WebElement getEmailAlertElement(){
       return emailAlertMessage;
    }

    public boolean passwordAlertMessageVisibility(){
        return passwordAlertMessage.isDisplayed();
    }

    public boolean passwordConfirmAlertMessageVisibility(){
       return passwordConfirmAlertMessage.isDisplayed();
    }
}
