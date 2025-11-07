package prestashop.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.model.dto.ProductInfo;
import prestashop.pages.MainPage;
import prestashop.config.EnvConfig;
import prestashop.util.PriceUtil;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainPageSteps extends BaseSteps {

    private final MainPage mainPage;

    public MainPageSteps(Page page) {
        super(page);
        this.mainPage = new MainPage(page);
    }

    // --- General actions ---
    public MainPageSteps openMainPage() {
        step("Open main page");
        page.navigate(EnvConfig.baseUrl());
        mainPage.waitForPageLoaded();
        return this;
    }

    // --- Popular products ---
    public ProductBuyingSteps clickProduct(String title) {
        step("Click product '{}'", title);
        click(mainPage.getProductByTitle(title));
        return new ProductBuyingSteps(page);
    }

    public List<ProductInfo> getPopularProductsInfo() {
        step("Read product titles and prices from 'Popular Products' section");

        Locator products = mainPage.getPopularProducts();
        products.first().waitFor();

        int count = products.count();
        List<ProductInfo> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Locator item = products.nth(i);
            String title = text(mainPage.getProductTitle(item));
            String rawPrice = text(mainPage.getProductPrice(item));
            double price = PriceUtil.parsePrice(rawPrice);
            result.add(new ProductInfo(title, price));
        }

        return result;
    }

    // --- Navigation buttons ---
    public AllProductsSteps goToAllProductsPage() {
        step("Navigate to All Products page");
        click(mainPage.getAllProductsButton().first());
        return new AllProductsSteps(page);
    }
}
