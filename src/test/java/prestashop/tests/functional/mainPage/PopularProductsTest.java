package prestashop.tests.functional.mainPage;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import prestashop.constants.TestGroup;
import prestashop.model.dto.ProductInfo;
import prestashop.tests.BaseTest;

import java.util.List;

public class PopularProductsTest extends BaseTest {

    @Test(groups = {TestGroup.SMOKE, TestGroup.CATALOG})
    @Issue("SMK-04")
    public void shouldDisplayNameAndPriceForEachPopularProduct() {
        List<ProductInfo> popularProducts = mainPageSteps
                .getPopularProductsInfo();

        Assert.assertFalse(popularProducts.isEmpty(), "Popular products list should not be empty");
        Assert.assertEquals(popularProducts.size(), 8, "Popular products list should contains 8 product," +
                " but actual size is " + popularProducts.size());

        for (ProductInfo product : popularProducts) {
            Assert.assertNotNull(product.getTitle(), "Product name should be displayed");
            Assert.assertFalse(product.getTitle().isBlank(), "Product name should not be empty");

            Assert.assertNotNull(product.getPrice(), "Product price should be displayed");
            Assert.assertTrue(product.getPrice() > 0, "Product price should be greater than 0");
        }
    }

}
