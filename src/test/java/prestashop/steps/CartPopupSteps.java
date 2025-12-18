package prestashop.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import prestashop.pages.CartPopupPage;

public class CartPopupSteps extends BaseSteps {
    private final CartPopupPage popupPage;

    public CartPopupSteps(Page page) {
        super(page);
        this.popupPage = new CartPopupPage(page);
    }

    public CartPopupSteps clickContinueShopping() {
        step("Click 'Continue Shopping' button on cart popup");
        if (popupPage.isCartEmptyMessage()) {
            throw new IllegalStateException(
                    "Cart is empty after clicking 'Add to cart'. " +
                            "Product was not added — probable demo site lag."
            );
        }
        Locator btn = popupPage.getContinueShoppingButton();
        btn.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));

        btn.click();
        return this;
    }

    public ShoppingCartSteps clickProceedToCheckout() {
        step("Click 'Proceed to checkout' button on cart popup");
        click(popupPage.getProceedToCheckoutButton());
        return new ShoppingCartSteps(page);
    }
}
