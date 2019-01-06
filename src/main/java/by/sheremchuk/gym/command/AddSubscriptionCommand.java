package by.sheremchuk.gym.command;

import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSubscriptionCommand implements Command {
    private final static String NAME_ATTRIBUTE = "subNameAdd";
    private final static String PERIOD_ATTRIBUTE = "subPeriodAdd";
    private final static String GUESTS_VISIT_ATTRIBUTE = "subGuestsVisitsAdd";
    private final static String TRAINING_COUNT_ATTRIBUTE = "subTrainingCountAdd";
    private final static String UNLIM_ATTRIBUTE = "subTrainingUnlimAdd";
    private final static String ERROR_MESSAGE_ATTRIBUTE = "error";
    private final static String CLEAR_PARAMETERS_ATTRIBUTE = "isClearParameter";

    private final static int UNLIM_TRAINIG_COUNT = 88;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_ATTRIBUTE);
        String periodString = request.getParameter(PERIOD_ATTRIBUTE);
        String guestVisitsString = request.getParameter(GUESTS_VISIT_ATTRIBUTE);
        String trainingCountString = request.getParameter(TRAINING_COUNT_ATTRIBUTE);
        String isUnlimString = request.getParameter(UNLIM_ATTRIBUTE);

        try {
            if (isParameterNotNull(name, periodString, guestVisitsString, trainingCountString, isUnlimString)) {
                throw new IllegalArgumentException("one of the field is null");
            }

            int period = Integer.parseInt(periodString);
            int guestVisits = Integer.parseInt(guestVisitsString);
            int trainingCount;
            if (isUnlimString != null) {
                trainingCount = UNLIM_TRAINIG_COUNT;
            } else {
                trainingCount = Integer.parseInt(trainingCountString);
            }

            if (isParameterNotValid(period, guestVisits, trainingCount)) {
                throw new IllegalArgumentException("Один из параметров не валидный");
            }

            boolean result;
            SubscriptionService service = SubscriptionService.getInstance();
            result = service.addSubscription(name, period, guestVisits, trainingCount);

            if (result) {
                request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, true);
            } else {
                request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "ошибка, проверьте заполненные поля и попробуйте снова");
            }

        } catch (IllegalArgumentException | ServiceException e) {
            e.printStackTrace();
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
        }
    }

    private boolean isParameterNotNull(String name,
                                        String periodString,
                                        String guestVisitsString,
                                        String trainingCountString,
                                        String isUnlimString) {
        return name == null
                || periodString == null
                || guestVisitsString == null
                || (trainingCountString == null && isUnlimString == null);
    }


    private boolean isParameterNotValid(int period,
                                        int guestVisits,
                                        int trainingCount) {
        return period <= 0
                || guestVisits < 0
                || trainingCount <= 0;
    }
}
