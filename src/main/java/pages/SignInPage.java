package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    private @FindBy(xpath = "//input[contains(@class, 'a-input-text a-span12')]")
    WebElement signInField;

    private @FindBy(xpath = "//h4[@class='a-alert-heading']")
    WebElement alertString;

    private @FindBy(xpath = "//a[@id='createAccountSubmit']")
    WebElement createAccountButton;


    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void signInFieldFilling(String inputWord) {
        signInField.sendKeys(inputWord, Keys.ENTER);
    }

    public String getAlertMessageText() {
       return alertString.getText();
    }

    public WebElement getCreateAccountButton(){
        return createAccountButton;
    }

    public void createAccountButtonClick(){
        createAccountButton.click();
    }
}
