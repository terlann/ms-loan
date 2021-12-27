package az.kapitalbank.loan.message.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEvent {
    String fullName;
    String pincode;
    String phoneNumber;
    String cif;
    int pan;
    String workplace;
    String address;
}
