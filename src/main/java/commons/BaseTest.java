package commons;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private WebDriver driver;

    protected final Log log;

    protected BaseTest() {
	log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDrivers(String browserName, String appURL) {
	System.out.println("Run on " + browserName);
	DesiredCapabilities capability = null;
	if (browserName.equals("firefox")) {
	    WebDriverManager.firefoxdriver().setup();	    
	    
	    FirefoxOptions fOption = new FirefoxOptions();
	    capability = DesiredCapabilities.firefox();
	    capability.setBrowserName("firefox");
	    capability.setPlatform(Platform.ANY);
	    fOption.merge(capability);
	    
	    driver = new FirefoxDriver(fOption);
	    
	} else if (browserName.equals("chrome")) {
	    WebDriverManager.chromedriver().setup();    
	    
	    ChromeOptions cOptions = new ChromeOptions();
	    capability = DesiredCapabilities.chrome();
	    capability.setBrowserName("chrome");
	    capability.setPlatform(Platform.ANY);
	    cOptions.merge(capability);
	    
	    driver = new ChromeDriver(cOptions);
	}
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get(appURL);
	return driver;

    }

    //Customize Hard Assert thanh nhung ham Verify(thay vi dung Soft Assert)
    protected boolean verifyTrue(boolean condition) {
	boolean pass = true;
	try {
	    Assert.assertTrue(condition);
	    log.info(" -------------------------- PASSED -------------------------- ");
	} catch (Throwable e) {
	    log.info(" -------------------------- FAILED -------------------------- ");
	    pass = false;
	    // Add lỗi vào ReportNG
	    VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
	    Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
    }

    protected boolean verifyFalse(boolean condition) {
	boolean pass = true;
	try {
	    Assert.assertFalse(condition);
	    log.info(" -------------------------- PASSED -------------------------- ");

	} catch (Throwable e) {
	    log.info(" -------------------------- FAILED -------------------------- ");
	    pass = false;
	    //add ket qua
	    VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
	    //add thong bao loi vao reportNG
	    Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
	boolean pass = true;
	try {
	    Assert.assertEquals(actual, expected);
	    log.info(" -------------------------- PASSED -------------------------- ");
	} catch (Throwable e) {

	    log.info(" -------------------------- FAILED -------------------------- ");
	    pass = false;
	    VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
	    Reporter.getCurrentTestResult().setThrowable(e);
	}
	return pass;
    }

}
