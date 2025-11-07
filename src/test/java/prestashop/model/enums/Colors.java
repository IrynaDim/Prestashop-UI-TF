package prestashop.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Colors {
    BLACK("Black"),
    WHITE("White");

    private final String label;
}
