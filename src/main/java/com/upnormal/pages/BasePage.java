package com.upnormal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * BasePage centralizes WebDriver and explicit wait usage.
 * Prefer explicit waits here rather than implicit waits to reduce flakiness across pages.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
