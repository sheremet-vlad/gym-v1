package by.sheremchuk.gym.entity.enums;

public enum GenderEnum {
    MAN("m"),
    WOMAN("w");

    private String value;

    GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
