package prestashop.model.enums;

import lombok.Getter;

@Getter
public enum PaymentMethods {
    BANK_WIRE("Pay by bank wire"),
    CASH_ON_DELIVERY("Pay by Cash on Delivery"),
    CHECK("Pay by Check");

    private final String text;

    PaymentMethods(String text) {
        this.text = text;
    }
}
