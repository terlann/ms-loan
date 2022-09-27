package az.kapitalbank.loan.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum LeadStatus {
    WAITING,
    SENDING,
    SUCCESS,
    DELETED,
    OPTIMUS_REJECTED,
    OPTIMUS_ERROR,
    OPTIMUS_ACCEPTED,
    ERROR;
}
