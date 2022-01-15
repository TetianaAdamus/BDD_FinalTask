package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends BasePage{
    private String brandName;

    private @FindBy(xpath = "//input[@id='low-price']")
    WebElement minPrice;

    private @FindBy(xpath = "//input[@id='high-price']")
    WebElement maxPrice;

    private @FindBy(xpath = "//input[@class='a-button-input']")
    WebElement filterButton;

    private @FindBy(xpath = "//span[@class='a-price-whole']")
    List<WebElement> priceFilteredList;

    private @FindBy(xpath = "//a[contains(@id,'p_89/')]")
    List<WebElement> brandButtons;


    private @FindBy(xpath = "//span[@class='a-price']")
    WebElement firstListedProduct;

    private @FindBy(xpath = "//input[@name='submit.addToCart']")
    WebElement relatedProduct;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void inputMinPriceValue(String minPriceValue){
        minPrice.sendKeys(minPriceValue);
    }

    public void inputMaxPriceValue(String maxPriceValue){
        maxPrice.sendKeys(maxPriceValue);
    }

    public void priceFilterButtonClick(){
        filterButton.click();
    }

    public List<WebElement> getPriceFilteredList(){
        return priceFilteredList;
    }

    public List<WebElement> getBrandButtonsList(){
        return brandButtons;
    }

    public void setBrandName(int listIndex){
        this.brandName = brandButtons.get(listIndex).getText().trim();
    }

    public String getBrandName(){
        return brandName;
    }

    public void clickFirstListedProduct(){
        firstListedProduct.click();
    }

    public void RelatedProductsAddToCart(){
        relatedProduct.click();
    }


}

