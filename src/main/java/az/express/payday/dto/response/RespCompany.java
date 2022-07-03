package az.express.payday.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class RespCompany {

    private Long userId;
    private String username;
    private String email;
    private Double balance;
    private String token;
}
