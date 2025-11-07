package prestashop.config;

import prestashop.util.YamlConfig;

public final class EnvConfig {

    private EnvConfig() {}

    public static String baseUrl() {
        return YamlConfig.getString("prestashop.baseUrl");
    }
}
