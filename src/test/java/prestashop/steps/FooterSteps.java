package prestashop.steps;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import prestashop.pages.components.FooterPage;

@Slf4j
public class FooterSteps extends BaseSteps {

    private final FooterPage footerPage;

    public FooterSteps(Page page) {
        super(page);
        this.footerPage = new FooterPage(page);
    }

    public String getSubscribeButtonValue() {
        step("Get text value of the 'Subscribe' button");
        return attribute(footerPage.getSubscribeButton(), "value");
    }

    public String getSubscribeButtonTextTransform() {
        step("Get CSS text-transform property of the 'Subscribe' button");
        return footerPage.getSubscribeButtonTextTransform();
    }

    public String getNewsletterLabelText() {
        step("Read text of the newsletter label");
        return text(footerPage.getNewsletterLabel());
    }
}
