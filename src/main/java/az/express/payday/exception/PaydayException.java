package az.express.payday.exception;

public class PaydayException extends RuntimeException {

    private Integer code;

    public PaydayException() {

    }

    public PaydayException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
