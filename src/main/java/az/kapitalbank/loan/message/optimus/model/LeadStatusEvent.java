package az.kapitalbank.loan.message.optimus.model;

import az.kapitalbank.loan.constants.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadStatusEvent {
    String id;
    Status status;
}
