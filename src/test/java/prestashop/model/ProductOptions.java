package prestashop.model;

import lombok.Builder;
import lombok.Getter;
import prestashop.model.enums.Colors;
import prestashop.model.enums.Sizes;

@Getter
@Builder
public class ProductOptions {
    private Colors color;
    private Sizes size;
    private String type;
    private Integer quantity;
    private String customText;
}
