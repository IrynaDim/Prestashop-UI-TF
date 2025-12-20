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
import prestashop.steps.CompleteOrderSteps;
import prestashop.steps.OrderConfirmationSteps;
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

        OrderConfirmationSteps confirmation = new OrderConfirmationSteps(page);

        String confirmationMessage = confirmation.getConfirmationMessage();
        int productsCount = confirmation.getProductsCount();

        boolean hasMug = confirmation.isProductPresent("Customizable mug");
        boolean hasTShirt = confirmation.isProductPresent("Hummingbird printed t-shirt");

        String customizationText = confirmation.getCustomizationText();

        double subtotal = confirmation.getSubtotal();
        double shipping = confirmation.getShippingPrice();
        double total = confirmation.getTotalPrice();

        String paymentMethod = confirmation.getPaymentMethod();
        String shippingMethod = confirmation.getShippingMethod();
        String orderReference = confirmation.getOrderReference();

        Assert.assertTrue(
                confirmationMessage.toLowerCase().contains("your order is confirmed"),
                "Order confirmation message is missing or incorrect. Actual: " + confirmationMessage
        );

        Assert.assertEquals(
                productsCount,
                2,
                "Expected 2 products in order, but found " + productsCount
        );

        Assert.assertTrue(
                hasMug,
                "Customizable mug is not present in order items"
        );

        Assert.assertTrue(
                hasTShirt,
                "Hummingbird printed t-shirt is not present in order items"
        );

        Assert.assertEquals(
                customizationText,
                "Best mug ever",
                "Product customization text is incorrect. Expected: 'Best mug ever', but was: " + customizationText
        );

        Assert.assertEquals(
                subtotal,
                39.62,
                0.01,
                "Subtotal price is incorrect. Expected 39.62, but was " + subtotal
        );

        Assert.assertEquals(
                shipping,
                8.40,
                0.01,
                "Shipping price is incorrect. Expected 8.40, but was " + shipping
        );

        Assert.assertEquals(
                total,
                48.02,
                0.01,
                "Total price is incorrect. Expected 48.02, but was " + total
        );

        Assert.assertTrue(
                paymentMethod.contains("Cash on delivery"),
                "Payment method is incorrect. Expected contains 'Cash on delivery', but was: " + paymentMethod
        );

        Assert.assertTrue(
                shippingMethod.contains("My carrier"),
                "Shipping method is incorrect. Expected contains 'My carrier', but was: " + shippingMethod
        );

        Assert.assertFalse(
                orderReference.isBlank(),
                "Order reference should not be empty"
        );
    }
}