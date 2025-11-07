package prestashop.config;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;
import prestashop.config.PlaywrightFactory;

import java.io.ByteArrayInputStream;

@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test failed: {}#{}", result.getTestClass().getName(), result.getMethod().getMethodName());
        Page page = PlaywrightFactory.getPage();
        if (page != null) {
            try {
                byte[] bytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
                Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(bytes), "png");
                Allure.addAttachment("Page URL", page.url());
                log.info("Screenshot attached to Allure");
            } catch (Exception e) {
                log.error("Error capturing screenshot", e);
            }
        }
    }
}
