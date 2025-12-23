package prestashop.tests.functional.mainPage;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import prestashop.constants.TestGroup;
import prestashop.model.enums.MenuCategoryItems;
import prestashop.tests.BaseTest;

import java.util.List;

public class NavigationTest extends BaseTest {

    @Test(groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG})
    @Issue("SMK-01")
    public void shouldDisplaySubMenuUnderClothes() {
        List<String> clothesSubMenu = headerSteps
                .getSubMenuItems(MenuCategoryItems.CLOTHES);
        Assert.assertFalse(clothesSubMenu.isEmpty(), "Submenu under CLOTHES should not be empty");
    }

    @Test(groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG})
    @Issue("SMK-02")
    public void shouldDisplaySubMenuUnderAccessories() {
        List<String> subMenu = headerSteps
                .getSubMenuItems(MenuCategoryItems.ACCESSORIES);
        Assert.assertFalse(subMenu.isEmpty(), "Submenu under ACCESSORIES should not be empty");
    }

    @Test(groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG})
    @Issue("SMK-03")
    public void shouldNotDisplaySubMenuUnderArt() {
        List<String> subMenu = headerSteps
                .getSubMenuItems(MenuCategoryItems.ART);
        Assert.assertTrue(subMenu.isEmpty(), "ART should not have subcategories");
    }

    @Test(groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG})
    @Issue("SMK-05")
    public void shouldDisplayLanguagesDropdownAndNotBeEmpty() {
        List<String> languages = headerSteps
                .getLanguages();

        Assert.assertFalse(languages.isEmpty(), "Languages dropdown should not be empty");
    }
}
