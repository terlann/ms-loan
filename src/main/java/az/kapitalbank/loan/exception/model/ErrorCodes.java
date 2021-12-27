package az.kapitalbank.loan.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {


    ERROR_002("E102","source can not be empty or null"),
    ERROR_003("E103","source can not find");

    String code;
    String message;
}
