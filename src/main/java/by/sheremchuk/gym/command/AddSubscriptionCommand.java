package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.SubscriptionService;
import by.sheremchuk.gym.util.ParameterChecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static by.sheremchuk.gym.variable.AttributeName.CLEAR_PARAMETERS_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.ERROR_MESSAGE_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.SUBSCRIPTIONS_LIST_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.UNLIM_TRAINING_COUNT;

public class AddSubscriptionCommand implements Command {
    private final static String NAME_ATTRIBUTE = "subNameAdd";
    private final static String PERIOD_ATTRIBUTE = "subPeriodAdd";
    private final static String GUESTS_VISIT_ATTRIBUTE = "subGuestsVisitsAdd";
    private final static String TRAINING_COUNT_ATTRIBUTE = "subTrainingCountAdd";
    private final static String UNLIM_ATTRIBUTE = "subTrainingUnlimAdd";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ParameterChecker parameterChecker = ParameterChecker.getInstance();

        String name = request.getParameter(NAME_ATTRIBUTE);
        String periodString = request.getParameter(PERIOD_ATTRIBUTE);
        String guestVisitsString = request.getParameter(GUESTS_VISIT_ATTRIBUTE);
        String trainingCountString = request.getParameter(TRAINING_COUNT_ATTRIBUTE);
        String isUnlimString = request.getParameter(UNLIM_ATTRIBUTE);

        try {
            if (parameterChecker.isSubscriptionParameterNotNull(name, periodString, guestVisitsString, trainingCountString, isUnlimString)) {
                throw new IllegalArgumentException("one of the field is null");
            }

            int period = Integer.parseInt(periodString);
            int guestVisits = Integer.parseInt(guestVisitsString);
            int trainingCount;
            if (isUnlimString != null) {
                trainingCount = UNLIM_TRAINING_COUNT;
            } else {
                trainingCount = Integer.parseInt(trainingCountString);
            }

            if (parameterChecker.isSubscriptionParameterNotValid(period, guestVisits, trainingCount)) {
                throw new IllegalArgumentException("Один из параметров не валидный");
            }

            SubscriptionService service = SubscriptionService.getInstance();
            Optional<Subscription> optionalSubscription = service.addSubscription(name, period, guestVisits, trainingCount);

            if (optionalSubscription.isPresent()) {
                request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, true);

                List subscriptionList = (List) request.getAttribute(SUBSCRIPTIONS_LIST_ATTRIBUTE);
                subscriptionList.add(optionalSubscription.get());
                request.setAttribute(SUBSCRIPTIONS_LIST_ATTRIBUTE, subscriptionList);

            } else {
                request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "ошибка, проверьте заполненные поля и попробуйте снова");
            }
        } catch (IllegalArgumentException | ServiceException e) {
            e.printStackTrace();
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
        }
    }
}
