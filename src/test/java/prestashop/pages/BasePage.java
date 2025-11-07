package prestashop.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class BasePage {
    protected final Page page;
    protected final FrameLocator frame;

    protected BasePage(Page page) {
        this.page = page;
        this.frame = page.frameLocator("iframe[name='framelive']");
    }

    public void click(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        locator.click();
    }

    public void waitUntilHidden(Locator locator, double timeoutMs) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(timeoutMs));
    }
}
