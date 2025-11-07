package prestashop.pages.components;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.slf4j.Slf4j;
import prestashop.pages.BasePage;

@Slf4j
public class HeaderPage extends BasePage {

    private static final String LANGUAGE_BUTTON = "button[data-toggle='dropdown'][aria-label='Language dropdown']";
    private static final String LANGUAGES_LIST = "ul[aria-labelledby='language-selector-label'] li";
    private static final String SEARCH_FIELD = ".ui-autocomplete-input";
    private static final String MENU_ITEM = "a.dropdown-item";
    private static final String SUBMENU_ITEMS = "ul.top-menu > li.category > a.dropdown-item.dropdown-submenu";
    private static final String SIGN_IN_BUTTON = "div.user-info a";
    private static final String USER_INFO_NAME = "div.user-info span.hidden-sm-down";

    public HeaderPage(Page page) {
        super(page);
    }

    public void openLanguageDropdown() {
        click(frame.locator(LANGUAGE_BUTTON));
    }

    public Locator getLanguages() {
        return frame.locator(LANGUAGES_LIST);
    }

    public Locator getSearchField() {
        return frame.locator(SEARCH_FIELD);
    }

    public Locator getMenuCategory(String categoryName) {
        return frame.getByRole(
                AriaRole.LINK,
                new FrameLocator.GetByRoleOptions().setName(categoryName)
        );
    }

    public Locator getSubMenuItems(String categoryName) {
        return frame.locator(MENU_ITEM)
                .filter(new Locator.FilterOptions().setHasText(categoryName))
                .locator("..").locator(SUBMENU_ITEMS);
    }

    public Locator getSignInButton() {
        return frame.locator(SIGN_IN_BUTTON);
    }

    public Locator getLoggedUserName() {
        return frame.locator(USER_INFO_NAME);
    }
}
