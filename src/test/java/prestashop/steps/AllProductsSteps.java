package prestashop.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
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

        productsPage.clickSortByDropSection();

        Locator option = productsPage.getSortOptionByName(sortBy);
        option.waitFor();
        option.click();

        Locator sortDropdown = productsPage.getSortByDropSection();
        sortDropdown.waitFor();

        PlaywrightAssertions.assertThat(sortDropdown).containsText(sortBy);

        Locator productsLocator = productsPage.getProductItems();
        productsLocator.first().waitFor();

        int count = productsLocator.count();
        List<ProductInfo> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Locator item = productsLocator.nth(i);
            String rawTitle = productsPage.getProductTitle(item).textContent().trim();
            boolean hasDiscount = productsPage.getProductRegularPrice(item).count() > 0;
            String rawPrice = hasDiscount
                    ? productsPage.getProductRegularPrice(item).textContent()
                    : productsPage.getProductPrice(item).textContent();
            double price = PriceUtil.parsePrice(rawPrice);
            products.add(new ProductInfo(rawTitle, price));
        }
        return products;
    }


}
