package prestashop.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public final class BrowserProvider {

    private BrowserProvider() {
    }

    public static Browser getBrowser(Playwright playwright, String browserName, boolean showBrowser) {
        BrowserOption browserType = BrowserOption.from(browserName);

        boolean headless = !showBrowser;

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setArgs(List.of("--no-sandbox", "--disable-dev-shm-usage"));

        log.info("Launching browser: {} | UI mode={} | headless={}",
                browserType, showBrowser, headless);

        try {
            return switch (browserType) {
                case FIREFOX -> playwright.firefox().launch(options);
                case WEBKIT -> playwright.webkit().launch(options);
                case CHROMIUM -> playwright.chromium().launch(options);
            };
        } catch (Exception e) {
            throw new IllegalStateException("Failed to launch browser: " + browserType, e);
        }
    }

    public enum BrowserOption {
        CHROMIUM, FIREFOX, WEBKIT;

        public static BrowserOption from(String name) {
            if (name == null || name.isBlank()) return CHROMIUM;
            for (BrowserOption b : values()) {
                if (b.name().equalsIgnoreCase(name.trim())) return b;
            }
            return CHROMIUM;
        }
    }
}
