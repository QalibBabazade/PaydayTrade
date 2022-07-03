package az.express.payday.enums;

public enum EnumAvailableStatus {

    ACTIVE(1) , DEACTIVE(0);

    private Integer value;

    private EnumAvailableStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
