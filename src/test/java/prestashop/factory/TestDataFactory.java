package prestashop.factory;

import prestashop.model.dto.AddressInfo;
import prestashop.model.dto.PersonalInfo;
import prestashop.model.enums.DeliveryCountry;

public class TestDataFactory {

    public static PersonalInfo defaultUser() {
        return PersonalInfo.builder()
                .gender("Mrs")
                .firstName("Anna")
                .lastName("Smith")
                .email("anna@example.com")
                .birthDate("01/01/1990")
                .password("test123456!#@")
                .agreeDataPrivacy(true)
                .agreeTerms(true)
                .build();
    }

    public static AddressInfo defaultAddress() {
        return AddressInfo.builder()
                .street("Main Street 12")
                .zip("10000")
                .city("Paris")
                .country(DeliveryCountry.FRANCE)
                .phone("+441234567890")
                .build();
    }
}
