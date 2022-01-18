package az.kapitalbank.loan.exception.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class ExceptionModel {
    String code;
    String message;
}
