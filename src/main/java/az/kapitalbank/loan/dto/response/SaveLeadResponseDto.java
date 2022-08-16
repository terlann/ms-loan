package az.kapitalbank.loan.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveLeadResponseDto {
    @JsonProperty("lead_id")
    UUID leadId;
}
