package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OrderConfirmationPage extends BasePage {

    // --- Locators ---
    private static final String CONFIRMATION_MESSAGE = "h3.h1.card-title";

    public OrderConfirmationPage(Page page) {
        super(page);
    }

    public Locator getConfirmationMessage() {
        return frame.locator(CONFIRMATION_MESSAGE);
    }
}
