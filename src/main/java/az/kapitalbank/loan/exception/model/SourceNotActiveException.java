package az.kapitalbank.loan.exception.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SourceNotActiveException extends RuntimeException {
    static String MESSAGE = "it cannot find this source code. %s";


    public SourceNotActiveException(String sourceCode) {
        super(String.format(MESSAGE, sourceCode));
    }
}
