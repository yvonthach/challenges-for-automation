package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.ProductCategoryPageUI;

public class ProductCategoryPageObject extends BasePage {

    WebDriver driver;

    public ProductCategoryPageObject(WebDriver driver) {
	this.driver = driver;
    }

    public boolean isTShirtsPageDisplayed() {
	waitForElementVisible(driver, ProductCategoryPageUI.TSHIRT_HEADING_TEXT);
	return isElementDisplayed(driver, ProductCategoryPageUI.TSHIRT_HEADING_TEXT);
    }

    public void selectSortItemInSortByDropdown(String textItem) {
	waitForElementVisible(driver, ProductCategoryPageUI.SORT_BY_CONTAINER_DROPDOWN);
	selectItemInDefaultDropDown(driver, ProductCategoryPageUI.SORT_BY_CONTAINER_DROPDOWN, textItem);

    }

    public boolean isProductPriceSortByAscendingPrice() {
	// list contains product in UI
	ArrayList<Float> productUIList = new ArrayList<Float>();
	
	// list element
	List<WebElement> productPriceText = getListWebElement(driver, ProductCategoryPageUI.PRODUCT_PRICE_TEXT);

	// get text of above element and add those text into an array
	for (WebElement productPrice : productPriceText) {
	    String priceText = productPrice.getText();
	    priceText.replace("$", "");
	    Float priceTextFloat = Float.parseFloat(priceText);    
	    productUIList.add(priceTextFloat);
	    System.out.println("Product price: " + productPrice.getText());
	}

	// create a new array to sort the old list
	ArrayList<Float> productSortList = new ArrayList<Float>();
	for (Float product : productUIList) {
	    productSortList.add(product);
	}

	// sort the new array as ascending order
	Collections.sort(productSortList);

	// print the list after execute sort action
	for (Float product : productSortList) {
	    System.out.println("Product price after sort: " + product);
	}

	return productSortList.equals(productUIList);
    }


    public ProductDetailPageObject clickToAddToCartOnItem(String productName) {
	waitForElementVisible(driver, ProductCategoryPageUI.PRODUCT_ADD_TO_CART, productName);
	clickToElement(driver, ProductCategoryPageUI.PRODUCT_ADD_TO_CART, productName);
	return PageGeneratorManager.getProductDetail(driver);
    }

}
