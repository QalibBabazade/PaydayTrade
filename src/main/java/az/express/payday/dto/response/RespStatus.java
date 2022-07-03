package az.express.payday.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespStatus {

    private Integer code;
    private String message;

    public static final Integer STATUS_CODE = 1;
    public static final String STATUS_MESSAGE = "SUCCESS!";

    public static RespStatus getSuccessMessage() {

        return new RespStatus(STATUS_CODE, STATUS_MESSAGE);
    }

}
