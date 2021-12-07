package az.kapitalbank.loan.message.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEvent {
    String fullname;
    String pincode;
    String phoneNumber;
    String cif;
    String pan;
    String workplace;
    String address;
}
