package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OrderConfirmationPage extends BasePage {

    // confirmation
    private static final String CONFIRMATION_MESSAGE = "h3.h1.card-title";

    // prices
    private static final String SUBTOTAL_PRICE =
            "tr:has(td:text('Subtotal')) td:nth-child(2)";
    private static final String SHIPPING_PRICE =
            "tr:has(td:text('Shipping and handling')) td:nth-child(2)";
    private static final String TOTAL_PRICE =
            "tr.total-value.font-weight-bold td:nth-child(2)";

    // products
    private static final String ORDER_ITEMS =
            ".order-confirmation-table .order-line";
    private static final String PRODUCT_NAME =
            ".details span";

    // кнопка кастомизации
    private static final String CUSTOMIZATION_LINK =
            ".order-confirmation-table .customizations a";

    // модалка
    private static final String CUSTOMIZATION_MODAL =
            "#product-customizations-modal-1";

    // значение кастомизации
    private static final String CUSTOMIZATION_VALUE =
            "#product-customizations-modal-1 .product-customization-line .value";

    // кнопка закрытия
    private static final String MODAL_CLOSE_BUTTON =
            "#product-customizations-modal-1 button.close";

    // order details
    private static final String ORDER_REFERENCE =
            "#order-reference-value";
    private static final String PAYMENT_METHOD =
            "#order-details ul li:text('Payment method')";
    private static final String SHIPPING_METHOD =
            "#order-details ul li:text('Shipping method')";

    public OrderConfirmationPage(Page page) {
        super(page);
    }

    public Locator confirmationMessage() {
        return frame.locator(CONFIRMATION_MESSAGE);
    }

    public Locator subtotalPrice() {
        return frame.locator(SUBTOTAL_PRICE);
    }

    public Locator shippingPrice() {
        return frame.locator(SHIPPING_PRICE);
    }

    public Locator totalPrice() {
        return frame.locator(TOTAL_PRICE);
    }

    public Locator orderItems() {
        return frame.locator(ORDER_ITEMS);
    }

    public Locator productNames() {
        return frame.locator(PRODUCT_NAME);
    }

    public Locator orderReference() {
        return frame.locator(ORDER_REFERENCE);
    }

    public Locator paymentMethod() {
        return frame.locator(PAYMENT_METHOD);
    }

    public Locator shippingMethod() {
        return frame.locator(SHIPPING_METHOD);
    }

    public Locator customizationLink() {
        return frame.locator(CUSTOMIZATION_LINK);
    }

    public Locator customizationModal() {
        return frame.locator(CUSTOMIZATION_MODAL);
    }

    public Locator customizationValue() {
        return frame.locator(CUSTOMIZATION_VALUE);
    }

    public Locator customizationModalClose() {
        return frame.locator(MODAL_CLOSE_BUTTON);
    }
}

