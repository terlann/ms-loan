package az.kapitalbank.loan.exception;

import az.kapitalbank.loan.constants.Error;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommonException extends RuntimeException {
    Error error;

    public CommonException(Error error, String message) {
        super(message);
        this.error = error;
    }
}
