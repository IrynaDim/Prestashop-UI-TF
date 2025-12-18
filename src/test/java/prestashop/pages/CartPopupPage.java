package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPopupPage extends BasePage {

    // --- Locators ---
    private static final String CONTINUE_SHOPPING_BUTTON = "div.cart-content-btn button.btn-secondary";
    private static final String PROCEED_TO_CHECKOUT_BUTTON = "div.cart-content-btn a.btn-primary";
    private static final String EMPTY_CART_MESSAGE = ".no-items";

    public CartPopupPage(Page page) {
        super(page);
    }

    // --- Elements ---
    public Locator getContinueShoppingButton() {
        return frame.locator(CONTINUE_SHOPPING_BUTTON);
    }

    public Locator getProceedToCheckoutButton() {
        return frame.locator(PROCEED_TO_CHECKOUT_BUTTON);
    }

    public boolean isCartEmptyMessage() {
        return page.locator(EMPTY_CART_MESSAGE).isVisible();
    }
}
