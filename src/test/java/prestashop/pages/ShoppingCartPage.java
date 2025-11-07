package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ShoppingCartPage extends BasePage {

    private static final String PROCEED_TO_CHECKOUT_BUTTON =
            "div.cart-detailed-actions a.btn.btn-primary";

    public ShoppingCartPage(Page page) {
        super(page);
    }

    public Locator getProceedToCheckoutButton() {
        return frame.locator(PROCEED_TO_CHECKOUT_BUTTON);
    }
}
