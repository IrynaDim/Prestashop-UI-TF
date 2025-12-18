package prestashop.model.enums;

public enum SortingOptions {

    NAME_ASC("Name, A to Z"),
    NAME_DESC("Name, Z to A"),
    PRICE_ASC("Price, low to high"),
    PRICE_DESC("Price, high to low");

    private final String uiText;

    SortingOptions(String uiText) {
        this.uiText = uiText;
    }

    public String getUiText() {
        return uiText;
    }
}

