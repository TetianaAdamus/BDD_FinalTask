package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    private @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchField;

    private @FindBy(xpath = "//a[@id='nav-link-accountList']")
    WebElement hello;

    private @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    WebElement helloButton;

    private @FindBy(xpath = "//a[@id='nav-cart']")
    WebElement cartItem;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url){
        driver.get(url);
    }

    public void enterTextToSearchField(final String searchText) {
        searchField.sendKeys(searchText, Keys.ENTER);
    }

    public void welcomeMessageIsVisible(){
        hello.isDisplayed();
    }

    public void HelloButtonClick(){
        helloButton.click();
    }

    public WebElement getCartItem(){
        return cartItem;
    }

    public void clickCartItem(){
        cartItem.click();
    }

}
