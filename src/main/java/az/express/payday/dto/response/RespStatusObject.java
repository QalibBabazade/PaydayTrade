package az.express.payday.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespStatusObject {

    @JsonProperty(value = "status")
    private RespStatus respStatus;
}
