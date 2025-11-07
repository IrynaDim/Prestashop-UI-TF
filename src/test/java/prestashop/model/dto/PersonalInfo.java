package prestashop.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalInfo {
    private String gender;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String password;
    private boolean agreeDataPrivacy;
    private boolean agreeTerms;
}
