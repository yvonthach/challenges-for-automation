package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage{

    WebDriver driver;

    public ProductDetailPageObject(WebDriver driver) {
	this.driver = driver;
    }

    public boolean isItemDetailDisplayed(String productdetail) {
	waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_NAME);
	
	return getElementText(driver, ProductDetailPageUI.PRODUCT_NAME).equals(productdetail);
    }
}
