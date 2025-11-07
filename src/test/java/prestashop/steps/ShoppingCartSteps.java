package prestashop.steps;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.pages.ShoppingCartPage;

@Slf4j
public class ShoppingCartSteps extends BaseSteps {

    private final ShoppingCartPage shoppingCartPage;

    public ShoppingCartSteps(Page page) {
        super(page);
        this.shoppingCartPage = new ShoppingCartPage(page);
    }

    public CompleteOrderSteps clickProceedToCheckout() {
        step("Click 'Proceed to checkout' button on shopping cart page");
        click(shoppingCartPage.getProceedToCheckoutButton());
        return new CompleteOrderSteps(page);
    }
}
