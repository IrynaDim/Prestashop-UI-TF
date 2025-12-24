package prestashop.flows;

import com.microsoft.playwright.Page;
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

    public void addProductsToCart(ProductItem... items) {

        for (int i = 0; i < items.length; i++) {
            ProductItem item = items[i];
            String productName = getProductName(item.getProduct());

            io.qameta.allure.Allure.step("Add product to cart: " + productName, () -> {

                if (item.getCategory() != null) {
                    headerSteps.searchProduct(item.getCategory().getName());
                } else {
                    headerSteps.searchProduct(productName);
                }
                searchSteps.clickOnProductByTitle(productName);

                if (item.getOptions() != null) {
                    buyingSteps.customizeProduct(item.getOptions());
                }

                buyingSteps.addProductToCart();
            });

            if (i < items.length - 1) {
                cartPopupSteps.clickContinueShopping();
            } else {
                io.qameta.allure.Allure.step("Proceed to checkout", () -> {
                    cartPopupSteps.clickProceedToCheckout();
                    shoppingCartSteps.clickProceedToCheckout();
                });
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
