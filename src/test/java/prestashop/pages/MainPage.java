package prestashop.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainPage extends BasePage {

    // --- Locators ---
    private static final String ALL_PRODUCTS_BUTTON = "section#content .all-product-link";
    private static final String POPULAR_PRODUCTS = "div.products.row article.product-miniature";
    private static final String PRODUCT_TITLE = ".product-title";
    private static final String PRODUCT_PRICE = ".price";
    private static final String PRODUCT_BY_TITLE = "h2.h3.product-title > a";
    private static final String PAGE_LOADING_MESSAGE = "div#loadingMessage";

    public MainPage(Page page) {
        super(page);
    }

    // --- Waits ---
    public void waitForPageLoaded() {
        waitUntilHidden(page.locator(PAGE_LOADING_MESSAGE), 20000);
        frame.locator("div#content-wrapper").waitFor();
    }

    // --- Elements ---
    public Locator getPopularProducts() {
        return frame.locator(POPULAR_PRODUCTS);
    }

    public Locator getProductTitle(Locator productItem) {
        return productItem.locator(PRODUCT_TITLE);
    }

    public Locator getProductPrice(Locator productItem) {
        return productItem.locator(PRODUCT_PRICE);
    }

    public Locator getProductByTitle(String title) {
        return frame.getByRole(
                AriaRole.LINK,
                new FrameLocator.GetByRoleOptions().setName(title)
        );
    }

    public Locator getAllProductsButton() {
        return frame.locator(ALL_PRODUCTS_BUTTON);
    }
}