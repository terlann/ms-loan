package az.kapitalbank.loan.utils;

import az.kapitalbank.loan.constants.LeadSource;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, LeadSource> {

    @Override
    public LeadSource convert(String source) {
        return LeadSource.valueOf(source.toUpperCase());
    }
}
