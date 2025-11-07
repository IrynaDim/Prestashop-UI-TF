package prestashop.model.dto;

import lombok.Builder;
import lombok.Data;
import prestashop.model.enums.DeliveryCountry;

@Data
@Builder
public class AddressInfo {
    private String street;
    private String zip;
    private String city;
    private DeliveryCountry country;
    private String phone;
}
