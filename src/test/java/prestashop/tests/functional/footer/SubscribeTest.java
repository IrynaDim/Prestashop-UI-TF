package prestashop.tests.functional.footer;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import prestashop.constants.TestGroup;
import prestashop.tests.BaseTest;

public class SubscribeTest extends BaseTest {

    @Test(
            groups = {TestGroup.UI_REGRESSION},
            testName = "Footer: Subscribe button text is displayed in uppercase",
            description = "Verify that the Subscribe button text is visually displayed in uppercase using CSS text-transform"
    )
    @Issue("FUNC-01")
    public void shouldDisplaySubscribeButtonTextInUppercase() {
        String raw = footerSteps.getSubscribeButtonValue();
        String transform = footerSteps.getSubscribeButtonTextTransform();

        Assert.assertEquals(raw, "Subscribe",
                "Subscribe button text should equal Subscribe ignoring case in DOM");
        Assert.assertEquals(transform, "uppercase",
                "Subscribe button must be visually uppercase via CSS");
    }

    @Test(
            groups = {TestGroup.SMOKE, TestGroup.UI_REGRESSION},
            testName = "Footer: Newsletter label text is displayed correctly",
            description = "Verify that the newsletter label text matches the expected value"
    )
    @Issue("FUNC-08")
    public void shouldDisplayCorrectNewsletterLabelText() {
        String labelText = footerSteps.getNewsletterLabelText();
        Assert.assertEquals(labelText.trim(),
                "Get our latest news and special sales",
                "Newsletter label text should match expected value");
    }
}
