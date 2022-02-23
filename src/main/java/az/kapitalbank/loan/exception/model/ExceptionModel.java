package az.kapitalbank.loan.exception.model;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionModel {
    String code;
    String message;
    Map<String, String> checks;

    public static ExceptionModel of(String code, String message) {
        var object = new ExceptionModel();
        object.setCode(code);
        object.setMessage(message);
        return object;
    }

    public static ExceptionModel of(String code, String message, Map<String, String> checks) {
        var object = new ExceptionModel();
        object.setCode(code);
        object.setMessage(message);
        object.setChecks(checks);
        return object;
    }
}
