package prestashop.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
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
        Locator subtotal = page.subtotalPrice();
        subtotal.scrollIntoViewIfNeeded();
        return PriceUtil.parsePrice(text(subtotal));
    }

    public double getShippingPrice() {
        step("Read shipping price");
        Locator shippingPrice = page.shippingPrice();
        shippingPrice.scrollIntoViewIfNeeded();
        return PriceUtil.parsePrice(text(shippingPrice));
    }

    public double getTotalPrice() {
        step("Read total price");
        Locator total = page.totalPrice();
        total.scrollIntoViewIfNeeded();
        return PriceUtil.parsePrice(text(total));
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

        Locator modal = page.customizationModal();
        wait(modal);

        String value = page.customizationValue().textContent().trim();
        page.customizationModalClose().click();

        return value;
    }

    public String getOrderReference() {
        step("Read order reference");
        Locator orderReference = page.orderReference();
        orderReference.scrollIntoViewIfNeeded();
        return text(orderReference)
                .replace("Order reference:", "")
                .trim();
    }

    public String getPaymentMethod() {
        step("Read payment method");
        Locator paymentMethod = page.paymentMethod();
        paymentMethod.scrollIntoViewIfNeeded();
        return text(paymentMethod);
    }

    public String getShippingMethod() {
        step("Read shipping method");
        Locator shippingMethod = page.shippingMethod();
        shippingMethod.scrollIntoViewIfNeeded();
        return text(shippingMethod);
    }
}
