package prestashop.tests.e2e;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import prestashop.constants.TestGroup;
import prestashop.factory.TestDataFactory;
import prestashop.flows.CheckoutFlowSteps;
import prestashop.model.ProductOptions;
import prestashop.model.dto.ProductItem;
import prestashop.model.enums.*;
import prestashop.steps.*;
import prestashop.tests.BaseTest;

public class AddProductsToCartTest extends BaseTest {

    @Test(groups = TestGroup.E2E)
    @Issue("E2E-02")
    public void shouldCompleteCheckoutSuccessfully() {
        CheckoutFlowSteps checkoutFlow = new CheckoutFlowSteps(page);

        checkoutFlow.addProductsToCart(
                ProductItem.builder()
                        .category(ProductCategory.MUG)
                        .product(ProductCategory.MUG_PRODUCTS.CUSTOMIZABLE_MUG)
                        .options(ProductOptions.builder()
                                .customText("Best mug ever")
                                .build())
                        .build(),

                ProductItem.builder()
                        .category(ProductCategory.T_SHIRT)
                        .product(ProductCategory.T_SHIRT_PRODUCTS.HUMMINGBIRD_TSHIRT)
                        .options(ProductOptions.builder()
                                .color(Colors.BLACK)
                                .size(Sizes.M)
                                .build())
                        .build()
        );

        new CompleteOrderSteps(page)
                .fillPersonalInformation(TestDataFactory.defaultUser())
                .fillAddress(TestDataFactory.defaultAddress())
                .chooseShippingOption(ShippingMethods.MY_CARRIER)
                .selectPaymentMethod(PaymentMethods.CASH_ON_DELIVERY)
                .placeOrder();

        String message = new OrderConfirmationSteps(page).getConfirmationMessage();
        Assert.assertTrue(
                message.toLowerCase().contains("your order is confirmed"),
                "Order confirmation message should be displayed"
        );
    }
}