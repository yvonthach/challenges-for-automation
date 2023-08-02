package commons;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private WebDriver driver;
    
    protected final Log log;

    protected BaseTest() {
	log = LogFactory.getLog(getClass());
    }


    protected WebDriver getBrowserDrivers(String browserName, String appURL) {
	System.out.println("Run on " + browserName);
	if (browserName.equals("firefox")) {
	    WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
	} else if (browserName.equals("chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	}
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get(appURL);
	return driver;

    }

}
