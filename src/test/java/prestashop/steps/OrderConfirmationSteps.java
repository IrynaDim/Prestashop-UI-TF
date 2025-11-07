package prestashop.steps;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.pages.OrderConfirmationPage;

@Slf4j
public class OrderConfirmationSteps extends BaseSteps {

    private final OrderConfirmationPage confirmationPage;

    public OrderConfirmationSteps(Page page) {
        super(page);
        this.confirmationPage = new OrderConfirmationPage(page);
    }

    public String getConfirmationMessage() {
        step("Read confirmation message");
        return text(confirmationPage.getConfirmationMessage());
    }
}
