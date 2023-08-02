package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    
    public static HomePageObject getHomePage(WebDriver driver) {
	return new HomePageObject(driver);
    }
    
    public static ProductCategoryPageObject getProductCategory(WebDriver driver) {
	return new ProductCategoryPageObject(driver);
    }
    
    public static ProductDetailPageObject getProductDetail(WebDriver driver) {
	return new ProductDetailPageObject(driver);
	
    }

}
