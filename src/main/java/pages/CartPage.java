package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    private @FindBy(xpath = "//div[@class='sc-list-item-content']")
    WebElement productInCart;

    private @FindBy(xpath = "//span[@id='sc-subtotal-label-activecart']")
    WebElement totalAmount;

    private @FindBy(xpath = "//p[@class='a-spacing-mini']")
    List<WebElement> priceInCart;

    private @FindBy(xpath = "//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
    WebElement totalSumInCart;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProductInCart() {
        return productInCart;
    }

    public String getTotalAmountText() {
        return totalAmount.getText().trim();
    }

    public String sumOfAllProductsInCart() {
        double result = 0;
        for (WebElement element : priceInCart) {
            result = result + (Double.parseDouble(element.getText().substring(1)));
        }
        String resultString = String.valueOf(result);
        if(resultString.contains(",")) {
            int index = resultString.indexOf(',');
            return resultString.substring(0,(index-2));
        } else return resultString;
    }

    public String totalSumInCart(){
        return totalSumInCart.getText().substring(1);
    }

}




