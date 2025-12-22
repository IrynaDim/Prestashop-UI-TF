package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CompleteOrderPage extends BasePage {

    // --- Locators ---
    private static final String GENDER_RADIO = "label[for='field-id_gender-%s']";
    private static final String FIRST_NAME_INPUT = "#field-firstname";
    private static final String LAST_NAME_INPUT = "#field-lastname";
    private static final String REGISTER_EMAIL_INPUT = "form#customer-form input[name='email']";
    private static final String BIRTHDATE_INPUT = "#field-birthday";
    private static final String REGISTER_PASSWORD_INPUT = "form#customer-form input[name='password']";
    private static final String DATA_PRIVACY_CHECKBOX = "input[name='customer_privacy']";
    private static final String TERMS_CHECKBOX = "input[name='psgdpr']";
    private static final String CONTINUE_BUTTON = "form#customer-form button[type='submit']";

    private static final String ADDRESS_INPUT = "#field-address1";
    private static final String ZIP_INPUT = "#field-postcode";
    private static final String CITY_INPUT = "#field-city";
    private static final String COUNTRY_DROPDOWN = "#field-id_country";
    private static final String CONFIRM_ADDRESS_BUTTON = "button[name='confirm-addresses']";

    private static final String SHIPPING_OPTION = ".delivery-option";
    private static final String CONFIRM_DELIVERY_BUTTON = "button[name='confirmDeliveryOption']";

    private static final String PAYMENT_TERMS_CHECKBOX = "input[name^='conditions_to_approve']";
    private static final String PLACE_ORDER_BUTTON = "#payment-confirmation button.btn.btn-primary.center-block";

    private static final String SUBTOTAL_VALUE = "#cart-subtotal-products .value";
    private static final String SHIPPING_VALUE = "#cart-subtotal-shipping .value";
    private static final String TOTAL_VALUE = ".cart-summary-line.cart-total .value";

    private static final String PAYMENT_SECTION = "#checkout-payment-step";

    public CompleteOrderPage(Page page) {
        super(page);
    }

    // --- Personal info section ---
    public Locator getGenderRadio(String gender) {
        String index = gender.equalsIgnoreCase("Mrs") ? "2" : "1";
        return frame.locator(String.format(GENDER_RADIO, index));
    }

    public Locator getFirstNameInput() {
        return frame.locator(FIRST_NAME_INPUT);
    }

    public Locator getLastNameInput() {
        return frame.locator(LAST_NAME_INPUT);
    }

    public Locator getRegistrationEmailInput() {
        return frame.locator(REGISTER_EMAIL_INPUT);
    }

    public Locator getBirthdateInput() {
        return frame.locator(BIRTHDATE_INPUT);
    }

    public Locator getRegistrationPasswordInput() {
        return frame.locator(REGISTER_PASSWORD_INPUT);
    }

    public Locator getDataPrivacyCheckbox() {
        return frame.locator(DATA_PRIVACY_CHECKBOX);
    }

    public Locator getTermsCheckbox() {
        return frame.locator(TERMS_CHECKBOX);
    }

    public Locator getContinueButton() {
        return frame.locator(CONTINUE_BUTTON);
    }

    // --- Address section ---
    public Locator getAddressInput() {
        return frame.locator(ADDRESS_INPUT);
    }

    public Locator getZipInput() {
        return frame.locator(ZIP_INPUT);
    }

    public Locator getCityInput() {
        return frame.locator(CITY_INPUT);
    }

    public Locator getCountryDropdown() {
        return frame.locator(COUNTRY_DROPDOWN);
    }

    public Locator getConfirmAddressButton() {
        return frame.locator(CONFIRM_ADDRESS_BUTTON);
    }

    // --- Shipping section ---
    public Locator getShippingOption(String optionText) {
        return frame.locator(SHIPPING_OPTION)
                .filter(new Locator.FilterOptions().setHasText(optionText))
                .locator("input[type='radio']");
    }

    public Locator getConfirmDeliveryButton() {
        return frame.locator(CONFIRM_DELIVERY_BUTTON);
    }

    // --- Payment section ---
    public Locator getPaymentOption(String optionText) {
        return frame.locator("label")
                .filter(new Locator.FilterOptions().setHasText(optionText));
    }

    public Locator getPaymentTermsCheckbox() {
        return frame.locator(PAYMENT_TERMS_CHECKBOX);
    }

    public Locator getPlaceOrderButton() {
        return frame.locator(PLACE_ORDER_BUTTON);
    }

    // --- Summary section ---
    public Locator getSubtotalValue() {
        return frame.locator(SUBTOTAL_VALUE);
    }

    public Locator getShippingValue() {
        return frame.locator(SHIPPING_VALUE);
    }

    public Locator getTotalValue() {
        return frame.locator(TOTAL_VALUE);
    }
}
