package prestashop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductBuyingPage extends BasePage {

    // --- Locators ---
    private static final String CUSTOMIZATION_FIELD = "#field-textField1";
    private static final String SAVE_CUSTOMIZATION_BUTTON = "button[name='submitCustomizedData']";
    private static final String CUSTOMIZATION_MESSAGE = "h6.customization-message";
    private static final String COLOR_OPTION = "label[aria-label='%s']";
    private static final String SIZE_DROPDOWN = "select.form-control.form-control-select";
    private static final String ADD_TO_CART_BUTTON = "button.add-to-cart";
    private static final String PAPER_TYPE_DROPDOWN = "select.form-control-select";
    private static final String QUANTITY_FIELD = "#quantity_wanted";

    public ProductBuyingPage(Page page) {
        super(page);
    }

    // --- Elements ---
    public Locator getCustomizationField() {
        return frame.locator(CUSTOMIZATION_FIELD);
    }

    public Locator getSaveCustomizationButton() {
        return frame.locator(SAVE_CUSTOMIZATION_BUTTON);
    }

    public Locator getCustomizationMessage() {
        return frame.locator(CUSTOMIZATION_MESSAGE);
    }

    public Locator getColorOption(String colorName) {
        return frame.locator(String.format(COLOR_OPTION, colorName));
    }

    public Locator getSizeDropdown() {
        return frame.locator(SIZE_DROPDOWN);
    }

    public Locator getPaperTypeDropdown() {
        return frame.locator(PAPER_TYPE_DROPDOWN);
    }

    public Locator getQuantityField() {
        return frame.locator(QUANTITY_FIELD);
    }

    public Locator getAddToCartButton() {
        return frame.locator(ADD_TO_CART_BUTTON);
    }
}
