package prestashop.steps;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.model.dto.AddressInfo;
import prestashop.model.dto.PersonalInfo;
import prestashop.model.enums.PaymentMethods;
import prestashop.model.enums.ShippingMethods;
import prestashop.pages.CompleteOrderPage;

@Slf4j
public class CompleteOrderSteps extends BaseSteps {

    private final CompleteOrderPage orderPage;

    public CompleteOrderSteps(Page page) {
        super(page);
        this.orderPage = new CompleteOrderPage(page);
    }

    // --- PERSONAL INFO ---
    public CompleteOrderSteps fillPersonalInformation(PersonalInfo info) {
        step("Fill personal information for '{} {}'", info.getFirstName(), info.getLastName());

        click(orderPage.getGenderRadio(info.getGender()));
        orderPage.getFirstNameInput().fill(info.getFirstName());
        orderPage.getLastNameInput().fill(info.getLastName());
        orderPage.getRegistrationEmailInput().fill(info.getEmail());
        orderPage.getBirthdateInput().fill(info.getBirthDate());
        orderPage.getRegistrationPasswordInput().fill(info.getPassword());

        if (info.isAgreeDataPrivacy()) {
            click(orderPage.getDataPrivacyCheckbox());
        }
        if (info.isAgreeTerms()) {
            click(orderPage.getTermsCheckbox());
        }

        click(orderPage.getContinueButton());
        return this;
    }

    // --- ADDRESS ---
    public CompleteOrderSteps fillAddress(AddressInfo address) {
        step("Fill address: {}, {}, {}", address.getStreet(), address.getZip(), address.getCity());

        orderPage.getAddressInput().waitFor();

        orderPage.getAddressInput().fill(address.getStreet());
        orderPage.getZipInput().fill(address.getZip());
        orderPage.getCityInput().fill(address.getCity());
        orderPage.getCountryDropdown().selectOption(address.getCountry().getName());

        click(orderPage.getConfirmAddressButton());
        return this;
    }

    // --- SHIPPING ---
    public CompleteOrderSteps chooseShippingOption(ShippingMethods option) {
        step("Choose shipping option '{}'", option.getText());

        click(orderPage.getShippingOption(option.getText()));
        click(orderPage.getConfirmDeliveryButton());
        return this;
    }

    // --- PAYMENT ---
    public CompleteOrderSteps selectPaymentMethod(PaymentMethods method) {
        step("Select payment method '{}'", method.getText());

        click(orderPage.getPaymentOption(method.getText()));
        click(orderPage.getPaymentTermsCheckbox());
        return this;
    }

    public void placeOrder() {
        step("Place order");
        click(orderPage.getPlaceOrderButton());
    }

    // --- VALIDATION HELPERS ---
    public String getSubtotalText() {
        return text(orderPage.getSubtotalValue());
    }

    public String getShippingText() {
        return text(orderPage.getShippingValue());
    }

    public String getTotalText() {
        return text(orderPage.getTotalValue());
    }
}
