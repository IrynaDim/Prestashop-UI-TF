package prestashop.tests.functional.allProducts;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import prestashop.constants.TestGroup;
import prestashop.model.dto.ProductInfo;
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

    @Test(groups = TestGroup.FUNCTIONAL, description = "Sorting: Name, A -> Z - list is alphabetically ascending")
    @Issue("FUNC-03")
    public void shouldSortProductsByNameAscending() {
        List<ProductInfo> products = allProductsSteps.getProductsBySorting("Name, A to F");
        assertNotEmpty(products, "Product list should not be empty");

        assertSorted(getTitles(products), Comparator.naturalOrder(),
                "Product titles should be sorted alphabetically (A → Z)");
    }

    @Test(groups = TestGroup.FUNCTIONAL, description = "Sorting: Name, Z -> A - list is alphabetically descending")
    @Issue("FUNC-04")
    public void shouldSortProductsByNameDescending() {
        List<ProductInfo> products = allProductsSteps.getProductsBySorting("Name, Z to A");
        assertNotEmpty(products, "Product list should not be empty");

        assertSorted(getTitles(products), Comparator.reverseOrder(),
                "Product titles should be sorted alphabetically (Z → A)");
    }

    @Test(groups = TestGroup.FUNCTIONAL, description = "Sorting: Price, low -> high - ascending by price")
    @Issue("FUNC-05")
    public void shouldSortProductsByPriceAscending() {
        List<ProductInfo> products = allProductsSteps.getProductsBySorting("Price, low to high");
        assertNotEmpty(products, "Product list should not be empty");

        assertSorted(getPrices(products), Comparator.naturalOrder(),
                "Product prices should be sorted ascending (low → high)");
    }

    @Test(groups = TestGroup.FUNCTIONAL, description = "Sorting: Price, high -> low - descending by price")
    @Issue("FUNC-06")
    public void shouldSortProductsByPriceDescending() {
        List<ProductInfo> products = allProductsSteps.getProductsBySorting("Price, high to low");
        assertNotEmpty(products, "Product list should not be empty");

        assertSorted(getPrices(products), Comparator.reverseOrder(),
                "Product prices should be sorted descending (high → low)");
    }

    // --- Utility methods ---

    private void assertNotEmpty(List<?> list, String message) {
        Assert.assertFalse(list.isEmpty(), message);
    }

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
