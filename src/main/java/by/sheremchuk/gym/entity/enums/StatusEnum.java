package by.sheremchuk.gym.entity.enums;

public enum StatusEnum {
    IN("in"),
    OUT("out");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
