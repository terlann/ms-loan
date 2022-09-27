package az.kapitalbank.loan.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum LeadStatus {
    WAITING("waiting"),
    SENDING("sending"),
    SUCCESS("success"),
    DELETED("deleted"),
    OPTIMUS_REJECTED("optimus_rejected"),
    OPTIMUS_ERROR("optimus_error"),
    OPTIMUS_ACCEPTED("optimus_accepted"),
    ERROR("error");

    String status;
}
