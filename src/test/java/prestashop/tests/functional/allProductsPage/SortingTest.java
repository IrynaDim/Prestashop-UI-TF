package prestashop.tests.functional.allProductsPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import prestashop.constants.TestGroup;
import prestashop.model.dto.ProductInfo;
import prestashop.model.enums.SortField;
import prestashop.model.enums.SortingOptions;
import prestashop.steps.AllProductsSteps;
import prestashop.tests.BaseTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingTest extends BaseTest {
    AllProductsSteps allProductsSteps;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        allProductsSteps = mainPageSteps.goToAllProductsPage();
    }

    @Test(
            groups = TestGroup.CATALOG,
            dataProvider = "sortingData"
    )
    @Issue("FUNC-03")
    public void shouldSortProductsCorrectly(
            SortingOptions sortingOption,
            String allureTitle,
            Comparator<?> comparator,
            SortField field
    ) {
        Allure.getLifecycle().updateTestCase(tc -> tc.setName(allureTitle));

        List<ProductInfo> products =
                allProductsSteps.getProductsBySorting(sortingOption);

        Assert.assertFalse(products.isEmpty(), "Product list should not be empty");

        if (field == SortField.TITLE) {
            assertSorted(getTitles(products), (Comparator<String>) comparator, "Product titles are not sorted correctly"
            );
        } else {
            assertSorted(getPrices(products), (Comparator<Double>) comparator, "Product prices are not sorted correctly"
            );
        }
    }

    @DataProvider(name = "sortingData")
    public Object[][] sortingData() {
        return new Object[][]{
                {
                        SortingOptions.NAME_ASC,
                        "Sorting: Name A → Z",
                        Comparator.naturalOrder(),
                        SortField.TITLE
                },
                {
                        SortingOptions.NAME_DESC,
                        "Sorting: Name Z → A",
                        Comparator.reverseOrder(),
                        SortField.TITLE
                },
                {
                        SortingOptions.PRICE_ASC,
                        "Sorting: Price low → high",
                        Comparator.naturalOrder(),
                        SortField.PRICE
                },
                {
                        SortingOptions.PRICE_DESC,
                        "Sorting: Price high → low",
                        Comparator.reverseOrder(),
                        SortField.PRICE
                }
        };
    }

    // --- Utility methods ---

    private <T extends Comparable<T>> void assertSorted(List<T> actual, Comparator<T> comparator, String message) {
        List<T> sorted = new ArrayList<>(actual);
        sorted.sort(comparator);
        Assert.assertEquals(actual, sorted, message);
    }

    private List<String> getTitles(List<ProductInfo> products) {
        return products.stream()
                .map(ProductInfo::getTitle)
                .toList();
    }

    private List<Double> getPrices(List<ProductInfo> products) {
        return products.stream()
                .map(ProductInfo::getPrice)
                .toList();
    }
}
