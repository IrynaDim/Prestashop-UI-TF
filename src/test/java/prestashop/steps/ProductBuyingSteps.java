package prestashop.steps;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.model.ProductOptions;
import prestashop.pages.ProductBuyingPage;

@Slf4j
public class ProductBuyingSteps extends BaseSteps {

    private final ProductBuyingPage buyingPage;

    public ProductBuyingSteps(Page page) {
        super(page);
        this.buyingPage = new ProductBuyingPage(page);
    }

    public ProductBuyingSteps customizeProduct(ProductOptions options) {
        step("Configure product options before adding to cart");

        if (options.getColor() != null) {
            click(buyingPage.getColorOption(options.getColor().getLabel()));
        }

        if (options.getSize() != null) {
            buyingPage.getSizeDropdown().selectOption(options.getSize().name());
        }

        if (options.getType() != null) {
            buyingPage.getPaperTypeDropdown().selectOption(options.getType());
        }

        if (options.getQuantity() != null) {
            buyingPage.getQuantityField().fill(options.getQuantity().toString());
        }

        if (options.getCustomText() != null) {
            buyingPage.getCustomizationField().fill(options.getCustomText());
            click(buyingPage.getSaveCustomizationButton());
            waitUntilVisible(buyingPage.getCustomizationMessage(), 7000);
        }
        return this;
    }

    public CartPopupSteps addProductToCart() {
        step("Add product to cart");
        click(buyingPage.getAddToCartButton());
        return new CartPopupSteps(page);
    }
}
