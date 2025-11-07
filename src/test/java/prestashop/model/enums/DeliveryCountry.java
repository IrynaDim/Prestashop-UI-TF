package prestashop.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryCountry {
    FRANCE("France"),
    USA("United States");

    private final String name;
}

