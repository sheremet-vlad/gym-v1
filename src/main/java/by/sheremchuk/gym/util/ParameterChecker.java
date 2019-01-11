package by.sheremchuk.gym.util;

public class ParameterChecker {
    private static volatile ParameterChecker instance;
    private final static Object lock = new Object();

    private ParameterChecker() {

    }

    public static ParameterChecker getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ParameterChecker();
                }
            }
        }

        return instance;
    }

    public boolean isSubscriptionParameterNotNull(String name,
                                       String periodString,
                                       String guestVisitsString,
                                       String trainingCountString,
                                       String isUnlimString) {
        return name == null
                || periodString == null
                || guestVisitsString == null
                || (trainingCountString == null && isUnlimString == null);
    }


    public boolean isSubscriptionParameterNotValid(int period,
                                        int guestVisits,
                                        int trainingCount) {
        return period <= 0
                || guestVisits < 0
                || trainingCount <= 0;
    }
}
