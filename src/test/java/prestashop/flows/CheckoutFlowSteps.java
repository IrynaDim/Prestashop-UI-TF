package prestashop.flows;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import prestashop.model.dto.ProductItem;
import prestashop.steps.*;

import java.lang.reflect.Method;

public class CheckoutFlowSteps extends BaseSteps {

    private final HeaderSteps headerSteps;
    private final SearchResultsSteps searchSteps;
    private final ProductBuyingSteps buyingSteps;
    private final CartPopupSteps cartPopupSteps;
    private final ShoppingCartSteps shoppingCartSteps;

    public CheckoutFlowSteps(Page page) {
        super(page);
        this.headerSteps = new HeaderSteps(page);
        this.searchSteps = new SearchResultsSteps(page);
        this.buyingSteps = new ProductBuyingSteps(page);
        this.cartPopupSteps = new CartPopupSteps(page);
        this.shoppingCartSteps = new ShoppingCartSteps(page);
    }

    @Step("Add multiple products to cart")
    public void addProductsToCart(ProductItem... items) {
        for (int i = 0; i < items.length; i++) {
            ProductItem item = items[i];

            if (item.getCategory() != null) {
                headerSteps.searchProduct(item.getCategory().getName());
            } else {
                headerSteps.searchProduct(getProductName(item.getProduct()));
            }
            searchSteps.clickOnProductByTitle(getProductName(item.getProduct()));

            if (item.getOptions() != null) {
                buyingSteps.customizeProduct(item.getOptions());
            }
            buyingSteps.addProductToCart();

            if (i < items.length - 1) {
                cartPopupSteps.clickContinueShopping();
            } else {
                cartPopupSteps.clickProceedToCheckout();
                shoppingCartSteps.clickProceedToCheckout();
            }
        }
    }

    private String getProductName(Enum<?> productEnum) {
        try {
            Method method = productEnum.getClass().getMethod("getName");
            return (String) method.invoke(productEnum);
        } catch (Exception e) {
            return productEnum.name();
        }
    }
}
