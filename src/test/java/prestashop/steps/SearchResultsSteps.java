package prestashop.steps;

import com.microsoft.playwright.Page;
import prestashop.pages.SearchResultsPage;

public class SearchResultsSteps extends BaseSteps {

    private final SearchResultsPage searchResultsPage;

    public SearchResultsSteps(Page page) {
        super(page);
        this.searchResultsPage = new SearchResultsPage(page);
    }

    public ProductBuyingSteps clickOnProductByTitle(String title) {
        step("Click on product with title '{}'", title);
        click(searchResultsPage.getProductByTitle(title));
        return new ProductBuyingSteps(page);
    }
}
