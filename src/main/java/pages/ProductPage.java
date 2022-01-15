package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends BasePage{
    private @FindBy(xpath = "//input[@id='add-to-cart-button']")
    WebElement addProductToCartButton;

    private @FindBy(xpath = "//a[@id='attach-close_sideSheet-link']")
    WebElement popUpClose;

    private @FindBy(xpath = "//select[@name='quantity']")
    List<WebElement> dropDownList;


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddProductToCartButton(){
        return addProductToCartButton;
    }

    public void clickAddToCartButton(){
        addProductToCartButton.click();
    }

    public WebElement getPopUpClose(){
        return popUpClose;
    }

    public void chooseDropDownElement(String quantity){
        Select selectelement = new Select(dropDownList.get(0));
        selectelement.selectByVisibleText(quantity);

    }


}
