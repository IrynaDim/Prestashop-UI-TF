package prestashop.util;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

@Slf4j
public final class FailureArtifacts {

    private FailureArtifacts() {
    }

    public static void attach(Page page) {
        if (page == null) {
            log.warn("Page is null, skipping failure artifacts attachment");
            return;
        }

        try {
            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(
                            page.screenshot(new Page.ScreenshotOptions().setFullPage(true))
                    ),
                    "png"
            );

            Allure.addAttachment("Page URL", page.url());

        } catch (Exception e) {
            log.error("Failed to attach failure artifacts", e);
        }
    }
}
