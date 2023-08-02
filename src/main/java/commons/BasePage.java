package commons;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private long longTimeout = 10;

    protected static BasePage getBasePage() {
	return new BasePage();
    }

    protected String getPageTitle(WebDriver driver) {
	return driver.getCurrentUrl();
    }

    protected void hoverMouseToElement(WebDriver driver, String locatorType) {
	Actions action = new Actions(driver);
	action.moveToElement(getWebElement(driver, locatorType)).perform();

    }

    protected WebElement getWebElement(WebDriver driver, String locatorType) {
	return driver.findElement(getByLocator(locatorType));
    }

    private By getByLocator(String locatorType) {
	By by = null;
	if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
	    by = By.xpath(locatorType.substring(6));
	} else {
	    throw new RuntimeException("Locator type is not supported");
	}
	return by;
    }

    protected void waitForElementVisible(WebDriver driver, String locator) {
	WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
	WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
	explicitWait.until(
		ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    private String getDynamicXpath(String locatorType, String... values) {
	// System.out.println("Locator Type Before = " + locatorType);
	if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
	    locatorType = String.format(locatorType, (Object[]) values);
	}

	return locatorType;
    }

    protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
	try {
	    return getWebElement(driver, locatorType).isDisplayed();
	} catch (NoSuchElementException e) {
	    return false;
	}

    }

    protected void clickToElement(WebDriver driver, String locator) {
	getWebElement(driver, locator).click();
    }
    protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
	getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();

    }

    public void sleep(long timeInSecond) {
	try {
	    Thread.sleep(timeInSecond * 1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    protected void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String textItem) {
	Select select = new Select(getWebElement(driver, locatorType));
	select.selectByVisibleText(textItem);
    }

    protected List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
	return driver.findElements(getByLocator(locatorType));
    }
    
    protected String getElementText(WebDriver driver, String locatorType) {
	return getWebElement(driver, locatorType).getText();

    }

}
