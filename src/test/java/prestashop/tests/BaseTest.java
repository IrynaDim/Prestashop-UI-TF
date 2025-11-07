package prestashop.tests;

import com.microsoft.playwright.Page;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import prestashop.config.PlaywrightFactory;
import prestashop.steps.FooterSteps;
import prestashop.steps.HeaderSteps;
import prestashop.steps.MainPageSteps;
import prestashop.config.TestListener;

import java.lang.reflect.Method;

@Slf4j
@Listeners({AllureTestNg.class, TestListener.class})
public abstract class BaseTest {
    protected Page page;
    protected MainPageSteps mainPageSteps;
    protected HeaderSteps headerSteps;
    protected FooterSteps footerSteps;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        log.info("--------------------------------------------------");
        log.info("=== Test START: {}#{} [Thread:{}] ===",
                getClass().getSimpleName(), method.getName(), Thread.currentThread().getId());
        page = PlaywrightFactory.createPage();
        mainPageSteps = new MainPageSteps(page);
        headerSteps = new HeaderSteps(page);
        footerSteps = new FooterSteps(page);
        openDefaultPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        log.info("=== Test FINISH: {}#{}",
                getClass().getSimpleName(),
                result.getMethod().getMethodName());
        PlaywrightFactory.close();
    }

    protected void openDefaultPage() {
        mainPageSteps.openMainPage();
    }
}
