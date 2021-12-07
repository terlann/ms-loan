package az.kapitalbank.loan.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveLeadResponseDto {
    @JsonProperty("lead_id")
    String leadId;
}
