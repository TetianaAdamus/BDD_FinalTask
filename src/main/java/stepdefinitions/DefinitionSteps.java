package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {

    private static final long DEFAULT_TIMEOUT = 30;

    WebDriver driver;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    SignInPage signInPage;
    CreateAccountPage createAccountPage;
    PageFactoryManager pageFactoryManager;
    ProductPage productPage;
    CartPage cartPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("User opens {string} page")
    public void openPage(String url){
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }


    @When("User makes search by keyword {string}")
    public void inputKeywordIntoSearchField(String keyword) {
        homePage.enterTextToSearchField(keyword);

    }

    @And("User inputs {string} value into minimal price filter field")
    public void inputsMinPriceValue(String minPrice) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.inputMinPriceValue(minPrice);
    }

    @And("User inputs {string} value into maximal price filter field")
    public void InputsMaxPriceValue(String maxPrice) {
        searchResultsPage.inputMaxPriceValue(maxPrice);
    }

    @And("User clicks filter button")
    public void filterButtonClick() {
        searchResultsPage.priceFilterButtonClick();
    }

    @Then("User checks all filtered products are within {string} and {string}")
    public void checkFilteredProductsWithinPrice(String minPrice, String maxPrice) {
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        for (WebElement tempElement: searchResultsPage.getPriceFilteredList()) {
            int productPrice = Integer.parseInt(tempElement.getText());
            assertTrue(productPrice>minPriceInt && productPrice<maxPriceInt);
        }
    }

    @And("User checks welcome message visibility")
    public void welcomeMessageIsVisible() {
        homePage.welcomeMessageIsVisible();
    }

    @When("User clicks welcome message button")
    public void WelcomeMessageClick() {
        homePage.HelloButtonClick();
    }

    @And("User inputs incorrect data {string} in the 'Sigh-In' field")
    public void inputDataIntoSignInField(String characterSet) {
        signInPage = pageFactoryManager.getSignInPage();
        signInPage.signInFieldFilling(characterSet);
    }

    @Then("User checks that {string} is visible")
    public void checkAlertMessageIsVisible(String alertMessage) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(signInPage.getAlertMessageText().trim(), alertMessage);
    }

    @And("User clicks create account button")
    public void clickCreateAccountButton() {
        signInPage = pageFactoryManager.getSignInPage();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signInPage.getCreateAccountButton());
        signInPage.getCreateAccountButton().click();
    }

    @And("User puts incorrect {string} in the 'Mobile number or email' field")
    public void putIncorrectEmail(String email) {
        createAccountPage = pageFactoryManager.getCreateAcountPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        createAccountPage.fillEmailField(email);
    }

    @And("User puts incorrect {string} in the field 'Password'")
    public void putIncorrectPassword(String password) {
        createAccountPage.fillPasswordField(password);
    }

    @And("User puts invalid {string} in the 'Re-enter password' field")
    public void putInvalidPasswordConfirm(String passwordConfirm) {
        createAccountPage.fillPasswordCheckField(passwordConfirm);
    }

    @And("User clicks continue button")
    public void clickContinueButton() {
        createAccountPage = pageFactoryManager.getCreateAcountPage();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, createAccountPage.getContinueButton());
        createAccountPage.continueButtonClick();
    }

    @Then("User checks visibility of the alert message about empty 'Your name' field")
    public void checkNameAlertMessage() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(createAccountPage.nameAlertMessageVisibility());
    }

    @And("User checks visibility of the alert message about invalid email or mobile number")
    public void checkEmailAlertMessage() {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, createAccountPage.getEmailAlertElement());
        assertTrue(createAccountPage.emailAlertMessageVisibility());
    }

    @And("User checks visibility of the alert message about invalid password")
    public void checkPasswordAlertMesage() {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(createAccountPage.passwordAlertMessageVisibility());
    }

    @And("User checks visibility of the alert message about invalid password confirmation")
    public void checkPasswordConfirmAlertMessage() {
        homePage.waitImplicity(DEFAULT_TIMEOUT);
        createAccountPage.passwordConfirmAlertMessageVisibility();
    }

    @And("User chooses brand filter using {int}")
    public void filterByBrand(int brandsListIndex) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getBrandButtonsList().get(brandsListIndex));
        searchResultsPage.setBrandName(brandsListIndex);
        searchResultsPage.getBrandButtonsList().get(brandsListIndex).click();
    }

    @And("User check that current URL contains  brand name")
    public void checkURLContainsBrandName() {
        homePage.waitVisibilityWordInUrl(DEFAULT_TIMEOUT, searchResultsPage.getBrandName());
        assertTrue(driver.getCurrentUrl().contains(searchResultsPage.getBrandName()));
    }

    @And("User chooses the first listed product")
    public void getFirstListedProduct() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.clickFirstListedProduct();
    }

    @And("User adds product to the cart")
    public void addProductToCart() {
        productPage = pageFactoryManager.getProductPage();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT,productPage.getAddProductToCartButton());
        productPage.clickAddToCartButton();

    }

    @And("User clicks 'Cart' item")
    public void cartItemClick() {
        homePage.waitElementIsClicable(DEFAULT_TIMEOUT, homePage.getCartItem());
        if (!homePage.getCartItem().isDisplayed() && !homePage.getCartItem().isEnabled()) {
            productPage.getPopUpClose().click();
        }
        homePage.clickCartItem();

    }

    @Then("User checks the product is in the cart")
    public void checkProductIsInCart() {
        cartPage = pageFactoryManager.getCartPage();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, cartPage.getProductInCart());
        assertTrue(cartPage.getProductInCart().isDisplayed());
    }

    @And("User selects {string} of products in the drop-down list")
    public void selectQuantityInDropDownList(String option) {
        productPage = pageFactoryManager.getProductPage();
        productPage.chooseDropDownElement(option);
    }

    @Then("User checks total {string} of products in the cart")
    public void checkProductsQuantityInCart(String quantity) {
        cartPage = pageFactoryManager.getCartPage();
        assertEquals(cartPage.getTotalAmountText(), "Subtotal ("+quantity+" items):");

    }

    @And("User puts correct {string} in the 'Your name' field")
    public void fillYourNameField(String name) {
        homePage.waitImplicity(DEFAULT_TIMEOUT);
        createAccountPage = pageFactoryManager.getCreateAcountPage();
        createAccountPage.fillNameField(name);
    }

    @And("User puts correct {string} in the 'Mobile number or email' field")
        public void putCorrectEmail(String email) {
            homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
            createAccountPage.fillEmailField(email);
    }

    @And("User puts correct {string} in the field 'Password'")
    public void putCorrectPassword(String password) {
        createAccountPage.fillPasswordField(password);
    }

    @And("User puts the same {string} in the 'Re-enter password' field")
    public void confirmPassword(String password) {
        createAccountPage.fillPasswordCheckField(password);
    }


    @Then("User checks 'Authentication required' {string} is shown")
    public void checkAuthenticationRequiredPageIsShown(String pageTitle) {
        assertEquals(pageTitle, homePage.getPageTitleName());
    }


    @And("User adds to cart related products")
    public void addRelatedProductsToCart() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.RelatedProductsAddToCart();
    }

    @Then("User checks total sum in the cart")
    public void checkTotalSumInTheCart() {
        cartPage = pageFactoryManager.getCartPage();
        assertEquals(cartPage.sumOfAllProductsInCart(), cartPage.totalSumInCart());
    }

    @Then("User check that current URL contains {string}")
    public void checkCurrentUrlByKeyWord(String keyword) {
        homePage.waitVisibilityWordInUrl(DEFAULT_TIMEOUT, keyword);
        assertTrue(driver.getCurrentUrl().contains(keyword));

    }

    @And("User inputs correct {string} in the 'Sigh-In' field")
    public void inputEmailInTheSighInField(String email) {
            signInPage = pageFactoryManager.getSignInPage();
            homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
            signInPage.signInFieldFilling(email);
    }

    @And("User inputs correct {string} in the 'Password' field")
    public void inputPassword(String password) {
        createAccountPage = pageFactoryManager.getCreateAcountPage();
        createAccountPage.inputPassword(password);
    }

    @Then("User checks {string} is shown")
    public void checkAccountPageIsShown(String pageTitle) {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(pageTitle, homePage.getPageTitleName());
    }

}
