package prestashop.constants;

public enum Resolution {
    HD(1280, 720),
    FULL_HD(1920, 1080),
    WQHD(2560, 1440),
    UHD(3840, 2160);

    private final int width;
    private final int height;

    Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Resolution from(String value) {
        if (value == null) return FULL_HD;
        try {
            return Resolution.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.printf("⚠ Unknown resolution '%s'. Using default: FULL_HD%n", value);
            return FULL_HD;
        }
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
