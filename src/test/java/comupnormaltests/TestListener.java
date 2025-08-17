package comupnormaltests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * TestListener - captures screenshot on failure and saves to target/screenshots.
 * Simple, pragmatic listener that helps debugging failed runs.
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (instance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) instance).driver;
            if (driver != null) {
                try {
                    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    String name = "screenshot-" + UUID.randomUUID() + ".png";
                    Files.createDirectories(Paths.get("target/screenshots"));
                    Files.copy(src.toPath(), Paths.get("target/screenshots/" + name));
                } catch (IOException ignored) {}
            }
        }
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
