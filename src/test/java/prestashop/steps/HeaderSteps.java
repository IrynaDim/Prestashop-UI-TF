package prestashop.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.model.enums.MenuCategoryItems;
import prestashop.pages.SearchResultsPage;
import prestashop.pages.components.HeaderPage;

import java.util.List;
import java.util.Optional;

@Slf4j
public class HeaderSteps extends BaseSteps {

    private final HeaderPage headerPage;

    public HeaderSteps(Page page) {
        super(page);
        this.headerPage = new HeaderPage(page);
    }

    public List<String> getSubMenuItems(MenuCategoryItems categoryName) {
        step("Hover category '{}' and read submenu items", categoryName);
        Locator category = headerPage.getMenuCategory(categoryName.name());
        hover(category);
        return headerPage.getSubMenuItems(categoryName.name()).allTextContents();
    }

    public SearchResultsPage searchProduct(String name) {
        step("Search product '{}'", name);
        Locator field = headerPage.getSearchField();
        field.fill(name);
        field.press("Enter");
        return new SearchResultsPage(page);
    }

    public List<String> getLanguages() {
        step("Open language dropdown and read available languages");
        headerPage.openLanguageDropdown();
        return allTexts(headerPage.getLanguages());
    }

    public Optional<String> getLoggedUserName() {
        step("Get logged in user name from header");
        Locator userName = headerPage.getLoggedUserName();
        if (userName.isVisible()) {
            return Optional.of(userName.innerText().trim());
        }
        return Optional.empty();
    }
}
