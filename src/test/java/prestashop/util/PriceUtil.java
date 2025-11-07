package prestashop.util;

public class PriceUtil {
    public static double parsePrice(String rawPrice) {
        return Double.parseDouble(rawPrice.replaceAll("[^0-9.,]", "").replace(",", "."));
    }
}
