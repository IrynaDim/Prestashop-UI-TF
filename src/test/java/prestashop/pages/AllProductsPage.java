package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AllProductsPage extends BasePage {

    // --- Locators ---
    private static final String PRODUCT_TITLE = "h2.h3.product-title > a";
    private static final String PRODUCT_PRICE = ".price";
    private static final String PRODUCT_REGULAR_PRICE = ".regular-price";
    private static final String SORT_BY_DROPDOWN = ".btn-unstyle.select-title";
    private static final String SORT_OPTION = ".select-list.js-search-link";
    private static final String PRODUCT_ITEM = "article.product-miniature";

    public AllProductsPage(Page page) {
        super(page);
    }

    // --- Elements ---
    public void clickSortByDropSection() {
        frame.locator(SORT_BY_DROPDOWN).click();
    }

    public Locator getSortByDropSection() {
        return frame.locator(SORT_BY_DROPDOWN);
    }

    public Locator getSortOptionByName(String text) {
        return frame.locator(SORT_OPTION)
                .filter(new Locator.FilterOptions().setHasText(text))
                .first();
    }

    public Locator getProductItems() {
        return frame.locator(PRODUCT_ITEM);
    }

    public Locator getProductTitle(Locator productItem) {
        return productItem.locator(PRODUCT_TITLE);
    }

    public Locator getProductPrice(Locator productItem) {
        return productItem.locator(PRODUCT_PRICE);
    }

    public Locator getProductRegularPrice(Locator productItem) {
        return productItem.locator(PRODUCT_REGULAR_PRICE);
    }
}
