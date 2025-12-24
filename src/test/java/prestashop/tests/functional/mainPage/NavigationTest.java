package prestashop.tests.functional.mainPage;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import prestashop.constants.TestGroup;
import prestashop.model.enums.MenuCategoryItems;
import prestashop.tests.BaseTest;

import java.util.List;

public class NavigationTest extends BaseTest {

    @Test(
            groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG},
            testName = "Navigation: CLOTHES submenu is displayed",
            description = "Verify that the CLOTHES menu contains at least one subcategory"
    )
    @Issue("SMK-01")
    public void shouldDisplaySubMenuUnderClothes() {
        List<String> clothesSubMenu = headerSteps
                .getSubMenuItems(MenuCategoryItems.CLOTHES);

        Assert.assertFalse(
                clothesSubMenu.isEmpty(),
                "Submenu under CLOTHES should not be empty"
        );
    }

    @Test(
            groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG},
            testName = "Navigation: ACCESSORIES submenu is displayed",
            description = "Verify that the ACCESSORIES menu contains at least one subcategory"
    )
    @Issue("SMK-02")
    public void shouldDisplaySubMenuUnderAccessories() {
        List<String> subMenu = headerSteps
                .getSubMenuItems(MenuCategoryItems.ACCESSORIES);

        Assert.assertFalse(
                subMenu.isEmpty(),
                "Submenu under ACCESSORIES should not be empty"
        );
    }

    @Test(
            groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG},
            testName = "Navigation: ART category has no subcategories",
            description = "Verify that the ART menu does not contain any subcategories"
    )
    @Issue("SMK-03")
    public void shouldNotDisplaySubMenuUnderArt() {
        List<String> subMenu = headerSteps
                .getSubMenuItems(MenuCategoryItems.ART);

        Assert.assertTrue(
                subMenu.isEmpty(),
                "ART should not have subcategories"
        );
    }

    @Test(
            groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION, TestGroup.CATALOG},
            testName = "Navigation: Languages dropdown is displayed and not empty",
            description = "Verify that the language selector dropdown is visible and contains at least one language"
    )
    @Issue("SMK-05")
    public void shouldDisplayLanguagesDropdownAndNotBeEmpty() {
        List<String> languages = headerSteps
                .getLanguages();

        Assert.assertFalse(
                languages.isEmpty(),
                "Languages dropdown should not be empty"
        );
    }
}
