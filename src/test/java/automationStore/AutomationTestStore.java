package automationStore;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.ProductCategoryPageObject;
import pageObjects.ProductDetailPageObject;

public class AutomationTestStore extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    ProductCategoryPageObject productCategory;
    ProductDetailPageObject productDetail;

    @BeforeClass
    @Parameters({ "browser", "url" })
    public void beforeClass(String browserName, String appURL) {
	driver = getBrowserDrivers(browserName, appURL);
	homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Test
    public void TestCase01() {
	log.info("TestCase01 - Step 1: Verify Home page is open");
	Assert.assertTrue(homePage.isHomePageTitleDisplayed());

	log.info("TestCase01 - Step 2: Hover on Apparel & Accessories Menu");
	homePage.hoverOnApparelAndAccessoriesMenu();

	log.info("TestCase01 - Step 2-1: Verify Shoes category is displayed");
	Assert.assertTrue(homePage.isShoeSubcategoriesDisplayed());

	log.info("TestCase01 - Step 2-2: Verify T-shirt category is displayed");
	Assert.assertTrue(homePage.isTShirtSubcategoriesDisplayed());

	log.info("TestCase01 - Step 2-2: Click on T-Shirts categories");
	productCategory = homePage.clickToTShirtsCategories();

	log.info("TestCase01 - Step 3: Verify T-Shirts page is displayed");
	Assert.assertTrue(productCategory.isTShirtsPageDisplayed());

    }

    @Test
    public void TestCase02_Verify_Sort() {
	log.info("TestCase02 - Step 4: Select  Sort by Price  Low > High on T-Shirt page");
	productCategory.selectSortItemInSortByDropdown("Price Low > High");
	
	productCategory.sleep(3);

	log.info("TestCase02 - Step 5: Verify that all items  were sorted by Price Low > High ");
	Assert.assertTrue(productCategory.isProductPriceSortByAscendingPrice());
	productCategory.sleep(5);}
    
    @Test
    public void TestCase03_Verify_Add_To_Cart() {
	
	log.info("TestCase03 - Step 6: Click to add to Card an Item on T-shirts");
	productDetail = productCategory.clickToAddToCartOnItem("Casual 3/4 Sleeve Baseball T-Shirt");
	log.info("TestCase03 - Step 6-1: Verify the item detailed  information");
	Assert.assertTrue(productDetail.isItemDetailDisplayed("Casual 3/4 Sleeve Baseball T-Shirt"));

    }

    @AfterClass
    public void afterClass() {
	driver.quit();
    }

}
