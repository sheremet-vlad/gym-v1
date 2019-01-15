package by.sheremchuk.gym.entity.enums;

public enum CardStatusEnum {
    ACTIVE("active"),
    NOT_ACTIVE("no_active"),
    END("end");

    private String value;

    CardStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardStatusEnum fromString(String parameterName) {
        if (parameterName != null) {
            for (CardStatusEnum status : CardStatusEnum.values()) {
                if (parameterName.equalsIgnoreCase(status.value)) {
                    return status;
                }
            }
        }
        return null;
    }
}
