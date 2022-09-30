package az.kapitalbank.loan.constants;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

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

    SOURCE_NOT_ACTIVE_EXCEPTION("E102", "Source not active", BAD_REQUEST),
    SOURCE_NOT_FOUND_EXCEPTION("E103", "Source not found", BAD_REQUEST),
    INVALID_REQUEST("E104", "Request is not valid", BAD_REQUEST);

    String code;
    String message;
    HttpStatus status;
}
