package comupnormaltests;

import com.upnormal.pages.*;
import com.upnormal.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookingTest extends BaseTest {

    @DataProvider(name = "bookingDataFromExcel")
    public Object[][] bookingDataFromExcel() {
        String excelPath = "src/test/resources/testdata.xlsx";
        ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");
        return excel.getData();
    }

    @Test(dataProvider = "bookingDataFromExcel")
    public void bookingFlowTest(String location, String checkInDate, String checkOutDate) {
        HomePage home = new HomePage(driver);
        HotelDetailsPage hotelPage = new HotelDetailsPage(driver);

        // Step 1: Open Booking.com and search
        home.open();
        home.setLocation(location);

        home.searchtDate();
        home.selectSpecificDate();
        //  verify dates displayed in UI match input
        home.verifyDisplayedDates(checkInDate, checkOutDate);
        home.clickSearch();
        //  Assertion 2: Verify the first search result contains the expected hotel name
        verifyHotelName("Helnan Royal Palestine Hotel - Montazah Gardens");

        // Step 2: Work with hotel details
        hotelPage.clickSeeAvailability();
        hotelPage.switchWindow();
        hotelPage.selectRoomAndAmount();
        hotelPage.clickReserve();

    }
    private void verifyHotelName(String expectedHotelName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        By hotelNameLocator = By.xpath("//div[contains(@class,'b87c397a13')][contains(text(),'Helnan Royal Palestine Hotel')]");

        String actualHotelName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(hotelNameLocator)
        ).getText();

        Assert.assertEquals(actualHotelName.trim(), expectedHotelName,
                "Hotel name does not match expected value!");
    }
}
