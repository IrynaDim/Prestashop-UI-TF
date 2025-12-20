package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ShoppingCartPage extends BasePage {

    private static final String PROCEED_TO_CHECKOUT_BUTTON =
            "div.cart-detailed-actions a.btn.btn-primary";

    private static final String EMPTY_CART_MESSAGE = ".no-items";

    public ShoppingCartPage(Page page) {
        super(page);
    }

    public Locator getProceedToCheckoutButton() {
        return frame.locator(PROCEED_TO_CHECKOUT_BUTTON);
    }

    public boolean isCartEmptyMessage() {
        return frame.locator(EMPTY_CART_MESSAGE).isVisible();
    }
}
