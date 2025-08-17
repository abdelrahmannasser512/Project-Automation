package com.upnormal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    // ================= Locators =================
    private final By destinationInput     = By.cssSelector("[name='ss']");
    private final By searchButton         = By.cssSelector("button[type='submit']");
    private final By calendarField        = By.cssSelector("button[data-testid='searchbox-dates-container']");
    private final By dateElementEnd = By.xpath("//*[@aria-label='We 15 October 2025']");
    private  final By dateElementStart = By.xpath("//*[@aria-label='We 8 October 2025']");
    private final By cookieAcceptButton   = By.cssSelector("[data-testid='cookie-banner-accept-button']");
    private final By calendarMonthHeader  = By.cssSelector("#calendar-searchboxdatepicker > div > div.a2142b454f.fb6f2a3ebc > div > div:nth-child(1) > h3");
    private final By calenderSearchMonth= By.cssSelector("#calendar-searchboxdatepicker > div > div.a2142b454f.fb6f2a3ebc > div > div:nth-child(1) > h3");
    private final By nextMonth = By.cssSelector("button[aria-label='Next month']");


    // ================= Constructor =================
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ================= Actions =================
    public void open() {
        driver.get("https://www.booking.com");
        acceptCookies();
    }

    private void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already dismissed.");
        }
    }

    public void setLocation(String location) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(destinationInput));
        input.clear();
        input.sendKeys(location);
        selectLocationFromDropdown(location);
    }

    private void selectLocationFromDropdown(String location) {
        By locationOption = By.xpath(String.format("//li[contains(.,'%s')]", location));
        wait.until(ExpectedConditions.elementToBeClickable(locationOption)).click();
    }

    public void openCalendar() {
        wait.until(ExpectedConditions.elementToBeClickable(calendarField)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarMonthHeader));
    }

    public void searchtDate() {
        while (true) {
            try {
                WebElement monthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(calenderSearchMonth));
                String currentMonth = monthElement.getText();

                if (currentMonth.contains("October 2025")) {
                    break; // Found our target month
                }

                // Click next month button
                wait.until(ExpectedConditions.elementToBeClickable(nextMonth)).click();

                // Small delay to allow calendar animation
                try { Thread.sleep(300); } catch (InterruptedException e) {}

            } catch (Exception e) {
                throw new RuntimeException("Failed to navigate to October 2025: " + e.getMessage());
            }
        }
    }







    public void selectSpecificDate() {


        wait.until(ExpectedConditions.elementToBeClickable(dateElementStart)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dateElementEnd)).click();


    }

    public void verifyDisplayedDates(String expectedStart, String expectedEnd) {
        By startField = By.xpath("//*[@data-testid='date-display-field-start']");
        By endField   = By.xpath("//*[@data-testid='date-display-field-end']");

        String actualStart = wait.until(ExpectedConditions.visibilityOfElementLocated(startField)).getText();
        String actualEnd   = wait.until(ExpectedConditions.visibilityOfElementLocated(endField)).getText();

        if (!actualStart.equals(expectedStart)) {
            throw new AssertionError("Start date mismatch. Expected: " + expectedStart + " but found: " + actualStart);
        }

        if (!actualEnd.equals(expectedEnd)) {
            throw new AssertionError("End date mismatch. Expected: " + expectedEnd + " but found: " + actualEnd);
        }

        System.out.println(" Dates displayed correctly: " + actualStart + " → " + actualEnd);
    }


    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}
