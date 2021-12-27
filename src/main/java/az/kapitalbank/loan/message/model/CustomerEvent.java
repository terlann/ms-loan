package az.kapitalbank.loan.message.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEvent {
    String cif;
    Integer pan;
    String pin;
    String address;
    String fullName;
    String workplace;
    String phoneNumber;
}
