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
    SENDING("Sending"),
    SUCCESS("success"),
    DELETED("deleted"),
    ERROR("error");

    String status;

    public String getStatus() {
        return status;
    }
}
