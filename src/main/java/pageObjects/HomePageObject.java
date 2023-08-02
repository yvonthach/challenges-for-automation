package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
	this.driver = driver;
    }

    public boolean isHomePageTitleDisplayed() {
	waitForElementVisible(driver, HomePageUI.AUTOMATION_TEST_STORE_HEADER);
	return isElementDisplayed(driver, HomePageUI.AUTOMATION_TEST_STORE_HEADER);
    }

    public void hoverOnApparelAndAccessoriesMenu() {
	waitForElementVisible(driver, HomePageUI.APPAREL_AND_ACCESSORIES_MENU);
	hoverMouseToElement(driver, HomePageUI.APPAREL_AND_ACCESSORIES_MENU);

    }


    public boolean isShoeSubcategoriesDisplayed() {
	waitForElementVisible(driver, HomePageUI.SHOES_SUBCATEGORY);
	return isElementDisplayed(driver, HomePageUI.SHOES_SUBCATEGORY);
    }

    public boolean isTShirtSubcategoriesDisplayed() {
	waitForElementVisible(driver, HomePageUI.TSHIRT_SUBCATEGORY_NAME);
	return isElementDisplayed(driver, HomePageUI.TSHIRT_SUBCATEGORY_NAME);
    }
    
    public ProductCategoryPageObject clickToTShirtsCategories() {
	waitForElementVisible(driver, HomePageUI.TSHIRT_SUBCATEGORY_NAME);
	clickToElement(driver, HomePageUI.TSHIRT_SUBCATEGORY_NAME);
	return PageGeneratorManager.getProductCategory(driver);
    }



}
