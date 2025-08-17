package comupnormaltests;

import com.upnormal.driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
/**
 * BaseTest - lightweight base class for tests.
 * Use this to provide common setup/teardown for WebDriver instances.
 * Keep setup minimal so tests remain readable and easy to adjust.
 */
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try {
            driver = WebDriverFactory.createDriver();
        } catch (Exception e) {
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while closing driver: " + e.getMessage());
            }
        }
    }
}