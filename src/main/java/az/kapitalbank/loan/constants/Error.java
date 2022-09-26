package az.kapitalbank.loan.constants;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Error {

    SOURCE_NOT_ACTIVE_EXCEPTION("E100", "Source not active", NOT_FOUND),
    SOURCE_NOT_FOUND_EXCEPTION("E101", "Source not found", NOT_FOUND),
    LEAD_NOT_FOUND("E102", "Lead not found", NOT_FOUND),
    INVALID_REQUEST("E103", "Request is not valid", BAD_REQUEST);

    String code;
    String message;
    HttpStatus status;
}
