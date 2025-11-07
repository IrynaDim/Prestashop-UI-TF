package prestashop.steps;

import com.microsoft.playwright.Page;
import prestashop.pages.CartPopupPage;

public class CartPopupSteps extends BaseSteps {
    private final CartPopupPage popupPage;

    public CartPopupSteps(Page page) {
        super(page);
        this.popupPage = new CartPopupPage(page);
    }

    public CartPopupSteps clickContinueShopping() {
        step("Click 'Continue Shopping' button on cart popup");
        click(popupPage.getContinueShoppingButton());
        return this;
    }

    public ShoppingCartSteps clickProceedToCheckout() {
        step("Click 'Proceed to checkout' button on cart popup");
        click(popupPage.getProceedToCheckoutButton());
        return new ShoppingCartSteps(page);
    }
}
