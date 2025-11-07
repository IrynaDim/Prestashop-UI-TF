package prestashop.pages.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.pages.BasePage;

@Slf4j
public class FooterPage extends BasePage {

    private static final String SUBSCRIBE_BUTTON = "input[name='submitNewsletter']:visible";
    private static final String NEWSLETTER_LABEL = "#block-newsletter-label";

    public FooterPage(Page page) {
        super(page);
    }

    public Locator getSubscribeButton() {
        return frame.locator(SUBSCRIBE_BUTTON);
    }

    public Locator getNewsletterLabel() {
        return frame.locator(NEWSLETTER_LABEL);
    }

    public String getSubscribeButtonTextTransform() {
        Object value = getSubscribeButton()
                .evaluate("el => window.getComputedStyle(el).textTransform");
        return value == null ? "" : value.toString();
    }
}
