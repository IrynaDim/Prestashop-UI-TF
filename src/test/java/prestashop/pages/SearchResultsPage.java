package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchResultsPage extends BasePage {

    // --- Locators ---
    private static final String PRODUCT_BY_TITLE = "h2.h3.product-title > a";

    public SearchResultsPage(Page page) {
        super(page);
    }

    // --- Elements ---
    public Locator getProductByTitle(String title) {
        return frame.locator(PRODUCT_BY_TITLE).filter(new Locator.FilterOptions().setHasText(title));
    }
}