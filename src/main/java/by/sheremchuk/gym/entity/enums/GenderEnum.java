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

    public static GenderEnum fromString(String parameterName) {
        if (parameterName != null) {
            for (GenderEnum gender : GenderEnum.values()) {
                if (parameterName.equalsIgnoreCase(gender.value)) {
                    return gender;
                }
            }
        }
        return null;
    }
}
