package prestashop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import prestashop.model.ProductOptions;
import prestashop.model.enums.ProductCategory;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
    private ProductCategory category;
    private Enum<?> product;
    private ProductOptions options;
}
