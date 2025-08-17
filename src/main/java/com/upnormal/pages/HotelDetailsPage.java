package com.upnormal.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelDetailsPage extends BasePage {
    // ================= Locators =================
    private final By seeAvailabilityBtn = By.cssSelector("#bodyconstraint-inner > div > div > div.f75d3e1279 > div.c39d0c9226 > div.df80b34405 > div.d3ef0e3593.c7bdc96a10 > div.cca574b93c > div:nth-child(15) > div.aa97d6032f > div.ad8fb705c9 > div > div.fa91e1e5db.c10aa2082a > div.adb6a5b835 > div > div.d755458b0f > a > span.ca2ca5203b");
    private final By selectBeds = By.cssSelector("#hprt-table > tbody > tr.js-rt-block-row.e2e-hprt-table-row.hprt-table-cheapest-block.hprt-table-cheapest-block-fix.js-hprt-table-cheapest-block.hprt-table-last-row > th > div > div.hprt-roomtype-bed > div.bed-types-wrapper.bed-types-v2 > label:nth-child(3) > div > input[type=radio]");
    private final By reserveButton = By.cssSelector("#hprt-form > div.hprt-container > div.hprt-table-cell.-last.hprt-reservation-summary-col.js-hprt-reservation-summary.hprt-block--room-selected > div.hprt-block.reserve-block-js > div.hprt-reservation-cta > button > span.bui-button__text.js-reservation-button__text");
    private final By roomDropdown = By.xpath("//tr[@data-block-id='1614813_199737183_2_2_0']//select[contains(@class,'hprt-nos-select')]");
    private final By valueOne=By.cssSelector("#hprt_nos_select_1614813_199737183_2_2_0 > option:nth-child(2)");
    // ================= Constructor =================
    public HotelDetailsPage(WebDriver driver) {
        super(driver);
    }

    // ================= Actions =================
    public void clickSeeAvailability() {
        wait.until(ExpectedConditions.elementToBeClickable(seeAvailabilityBtn)).click();

        }



    public void switchWindow(){
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }


    }}
    public void selectRoomAndAmount() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(selectBeds)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(roomDropdown)).click();

        wait.until(ExpectedConditions.elementToBeClickable(valueOne)).click();
    }

    public void clickReserve() {
        wait.until(ExpectedConditions.elementToBeClickable(reserveButton)).click();
    }}