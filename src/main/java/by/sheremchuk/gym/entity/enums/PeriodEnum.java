package by.sheremchuk.gym.entity.enums;

public enum  PeriodEnum {
    MORNING("1"),
    DAY("2"),
    EVENING("3");

    private String value;

    PeriodEnum( String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
