package az.kapitalbank.loan.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestConstant {
    LEAD_ID("b0ff7788-5687-11ed-9b6a-0242ac120002"),
    PHONE_NUMBER("+994773227040"),
    LEAD_SOURCE("0014"),
    ADDRESS("Azerbaycan.Baki");
    final String value;
}
