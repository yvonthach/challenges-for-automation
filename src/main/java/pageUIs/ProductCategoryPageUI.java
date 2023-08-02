package pageUIs;

public class ProductCategoryPageUI {

    public static final String TSHIRT_HEADING_TEXT = "xpath=//div[@id='maincontainer']//h1/span[text()='T-shirts']";
    public static final String SORT_BY_CONTAINER_DROPDOWN = "xpath=//select[@name='sort']";
    public static final String PRODUCT_PRICE_TEXT = "xpath=//div[@class='thumbnail']//div[@class='oneprice']";
    public static final String PRODUCT_ADD_TO_CART = "xpath=//a[@class='prdocutname' and text()='%s']//ancestor::div[@class='fixed_wrapper']/following-sibling::div[@class='thumbnail']//a[@title='Add to Cart']";

}
