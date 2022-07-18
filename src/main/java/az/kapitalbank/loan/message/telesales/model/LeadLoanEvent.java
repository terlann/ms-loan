package az.kapitalbank.loan.message.telesales.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadLoanEvent {
    @JsonProperty("ID")
    Long id;
    @JsonProperty("STATUS")
    Integer status;
    @JsonProperty("DECLINEREASON")
    Integer declineReason;
    @JsonProperty("ORIGTIME")
    Long origTime;
    @JsonProperty("RECEIVETIME")
    Long receiveTime;
    @JsonProperty("CNSADDRESS")
    String cnsAddress;
    @JsonProperty("CHANNEL")
    String channel;
    @JsonProperty("CMDTEXT")
    String cmdText;
    @JsonProperty("ERRORMESSAGE")
    String errorMessage;
}
