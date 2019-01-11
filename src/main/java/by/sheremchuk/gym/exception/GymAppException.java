package by.sheremchuk.gym.exception;

public class GymAppException extends Exception {

    public GymAppException() {
    }

    public GymAppException(String message) {
        super(message);
    }

    public GymAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public GymAppException(Throwable cause) {
        super(cause);
    }

    public GymAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
