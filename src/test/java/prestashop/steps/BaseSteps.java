package prestashop.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.util.List;

@Slf4j
public abstract class BaseSteps {
    protected final Page page;

    protected BaseSteps(Page page) {
        this.page = page;
    }

    protected void click(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        locator.click();
    }

    protected void hover(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        locator.hover();
    }

    protected String text(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        return locator.textContent().trim();
    }

    protected String attribute(Locator locator, String name) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        return locator.getAttribute(name);
    }

    protected List<String> allTexts(Locator locator) {
        locator.first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        return locator.allTextContents();
    }

    protected void waitUntilVisible(Locator locator, double timeoutMs) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutMs));
    }

    protected void step(String message, Object... args) {
        String formatted = (args == null || args.length == 0)
                ? message
                : MessageFormatter.arrayFormat(message, args).getMessage();

        Allure.step(formatted, () -> log.info("[STEP] {}", formatted));
    }
}