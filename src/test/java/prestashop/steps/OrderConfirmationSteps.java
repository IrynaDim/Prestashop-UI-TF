package prestashop.steps;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.pages.OrderConfirmationPage;
import prestashop.util.PriceUtil;

@Slf4j
public class OrderConfirmationSteps extends BaseSteps {

    private final OrderConfirmationPage page;

    public OrderConfirmationSteps(Page pwPage) {
        super(pwPage);
        this.page = new OrderConfirmationPage(pwPage);
    }

    public String getConfirmationMessage() {
        step("Read confirmation message");
        return text(page.confirmationMessage());
    }

    public double getSubtotal() {
        step("Read subtotal price");
        return PriceUtil.parsePrice(text(page.subtotalPrice()));
    }

    public double getShippingPrice() {
        step("Read shipping price");
        return PriceUtil.parsePrice(text(page.shippingPrice()));
    }

    public double getTotalPrice() {
        step("Read total price");
        return PriceUtil.parsePrice(text(page.totalPrice()));
    }

    public int getProductsCount() {
        step("Read products count");
        return page.orderItems().count();
    }

    public boolean isProductPresent(String productName) {
        step("Check product present: " + productName);
        return page.productNames()
                .allTextContents()
                .stream()
                .anyMatch(t -> t.contains(productName));
    }

    public String getCustomizationText() {
        step("Open product customization modal");
        page.customizationLink().first().click();
        page.customizationModal().waitFor();
        String value = text(page.customizationValue()).trim();
        page.customizationModalClose().click();
        return value;
    }

    public String getOrderReference() {
        step("Read order reference");
        return text(page.orderReference())
                .replace("Order reference:", "")
                .trim();
    }

    public String getPaymentMethod() {
        step("Read payment method");
        return text(page.paymentMethod());
    }

    public String getShippingMethod() {
        step("Read shipping method");
        return text(page.shippingMethod());
    }
}
