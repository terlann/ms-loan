package az.kapitalbank.loan.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum ErrorCodes {


    ERROR_002("E102", "source can not be empty or null"),
    ERROR_003("E103", "source can not find"),
    BAD_REQUEST("E104", "Request is not valid");

    String code;
    String message;
}
