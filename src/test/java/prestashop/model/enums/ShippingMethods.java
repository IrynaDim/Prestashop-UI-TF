package prestashop.model.enums;

import lombok.Getter;

@Getter
public enum ShippingMethods {
    CLICK_AND_COLLECT("Click and collect"),
    MY_CARRIER("My carrier");

    private final String text;

    ShippingMethods(String text) {
        this.text = text;
    }
}
