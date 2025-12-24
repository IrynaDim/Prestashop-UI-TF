package prestashop.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PlaywrightFactory {

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    private PlaywrightFactory() {
    }

    public static Page createPage() {
        playwright.set(Playwright.create());

        String browserParam = System.getProperty("browser", System.getenv("BROWSER"));
        boolean showBrowser = Boolean.parseBoolean(System.getProperty("headless", System.getenv("HEADLESS")));

        log.info("Environment params: browser='{}', showBrowser={}",
                browserParam, showBrowser);

        browser.set(BrowserProvider.getBrowser(playwright.get(), browserParam, showBrowser));

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();

        context.set(browser.get().newContext(contextOptions));
        page.set(context.get().newPage());

        log.info("Playwright page created. Context & viewport initialized. Mode: {}", showBrowser ? "UI" : "HEADLESS");
        return page.get();
    }

    public static void close() {
        if (context.get() != null) {
            context.get().close();
            context.remove();
        }
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }

    public static Page getPage() {
        return page.get();
    }
}