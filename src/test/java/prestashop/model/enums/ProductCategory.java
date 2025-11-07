package prestashop.model.enums;

import lombok.Getter;

@Getter
public enum ProductCategory {
    MUG("Mug", MUG_PRODUCTS.class),
    T_SHIRT("T-Shirt", T_SHIRT_PRODUCTS.class);

    private final String displayName;
    private final Class<? extends Enum<?>> subProducts;

    ProductCategory(String displayName, Class<? extends Enum<?>> subProducts) {
        this.displayName = displayName;
        this.subProducts = subProducts;
    }

    public String getName() {
        return displayName;
    }

    // ---------- SUB ENUMS ----------
    public enum MUG_PRODUCTS {
        CUSTOMIZABLE_MUG("Customizable Mug"),
        PERSONALIZED_MUG("Personalized Mug");

        private final String displayName;

        MUG_PRODUCTS(String displayName) {
            this.displayName = displayName;
        }

        public String getName() {
            return displayName;
        }
    }

    public enum T_SHIRT_PRODUCTS {
        HUMMINGBIRD_TSHIRT("Hummingbird Printed T-Shirt"),
        BASIC_TSHIRT("Basic T-Shirt");

        private final String displayName;

        T_SHIRT_PRODUCTS(String displayName) {
            this.displayName = displayName;
        }

        public String getName() {
            return displayName;
        }
    }
}
