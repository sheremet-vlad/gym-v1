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

    public static StatusEnum fromString(String parameterName) {
        if (parameterName != null) {
            for (StatusEnum status : StatusEnum.values()) {
                if (parameterName.equalsIgnoreCase(status.value)) {
                    return status;
                }
            }
        }
        return null;
    }
}
