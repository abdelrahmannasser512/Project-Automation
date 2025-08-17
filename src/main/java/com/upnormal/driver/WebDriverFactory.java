package com.upnormal.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverFactory {
    private static final Logger logger = Logger.getLogger(WebDriverFactory.class.getName());

    // Configuration flags (could be moved to properties file)
    private static final boolean USE_WEBDRIVER_MANAGER = true;
    private static final String CHROMEDRIVER_PATH = "C:\\Users\\abdel\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
    private static final int IMPLICIT_WAIT_SECONDS = 10;

    public static WebDriver createDriver() {
        try {
            // 1. Driver Setup
            if (USE_WEBDRIVER_MANAGER) {
                logger.info("Using WebDriverManager for automatic driver setup");
                WebDriverManager.chromedriver().setup();
            } else {
                logger.info("Using local chromedriver at: " + CHROMEDRIVER_PATH);
                System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
            }

            // 2. Chrome Options Configuration
            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    "--start-maximized",
                    "--disable-notifications",
                    "--remote-allow-origins=*",
                    "--no-sandbox",
                    "--disable-dev-shm-usage"
            );

            // 3. Driver Instantiation
            WebDriver driver = new ChromeDriver(options);

            // 4. Global Timeout Configuration
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));

            return driver;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to create WebDriver instance", e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }
}