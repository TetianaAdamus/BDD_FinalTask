package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public SearchResultsPage getSearchResultsPage(){
        return new SearchResultsPage(driver);
    }

    public SignInPage getSignInPage(){
        return new SignInPage(driver);
    }

    public CreateAccountPage getCreateAcountPage(){
        return new CreateAccountPage(driver);
    }

    public ProductPage getProductPage(){
        return new ProductPage(driver);
    }

    public CartPage getCartPage(){
        return new CartPage(driver);
    }


}
