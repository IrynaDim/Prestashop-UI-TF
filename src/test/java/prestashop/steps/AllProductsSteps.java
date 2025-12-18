package prestashop.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.model.dto.ProductInfo;
import prestashop.pages.AllProductsPage;
import prestashop.util.PriceUtil;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AllProductsSteps extends BaseSteps {
    private final AllProductsPage productsPage;

    public AllProductsSteps(Page page) {
        super(page);
        this.productsPage = new AllProductsPage(page);
    }

    public List<ProductInfo> getProductsBySorting(String sortBy) {
        log.info("[STEP] Sort products by option '{}'", sortBy);

        productsPage.getSortByButton().click();
        productsPage.selectSortOption(sortBy);
        Locator firstProductBefore = productsPage.getProductItems().first();
        String firstTitleBefore = firstProductBefore.textContent();

        page.waitForCondition(() -> {
            String firstTitleAfter =
                    productsPage.getProductItems().first().textContent();
            return !firstTitleAfter.equals(firstTitleBefore);
        });

        Locator products = productsPage.getProductItems();
        products.first().waitFor();

        return collectProducts(products);
    }

    private List<ProductInfo> collectProducts(Locator productsLocator) {
        int count = productsLocator.count();
        List<ProductInfo> products = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Locator item = productsLocator.nth(i);
            String title = productsPage.getProductTitle(item).textContent().trim();

            boolean hasDiscount = productsPage.getProductRegularPrice(item).count() > 0;
            String rawPrice = hasDiscount
                    ? productsPage.getProductRegularPrice(item).textContent()
                    : productsPage.getProductPrice(item).textContent();

            double price = PriceUtil.parsePrice(rawPrice);
            products.add(new ProductInfo(title, price));
        }
        return products;
    }

}
