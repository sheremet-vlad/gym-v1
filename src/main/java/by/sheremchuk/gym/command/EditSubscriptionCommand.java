package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.SubscriptionService;
import by.sheremchuk.gym.util.ParameterChecker;
import by.sheremchuk.gym.web.MainPageServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.sheremchuk.gym.variable.AttributeName.CLEAR_PARAMETERS_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.CURRENT_SUBSCRIPTION_NAME_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.EDIT_GUESTS_VISIT_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.EDIT_NAME_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.EDIT_PERIOD_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.EDIT_TRAINING_COUNT_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.SUBSCRIPTIONS_LIST_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.UNLIM_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.UNLIM_TRAINING_COUNT;

public class EditSubscriptionCommand extends MainPageServlet implements Command {
    private final static String ERROR_MESSAGE_ATTRIBUTE = "error";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ParameterChecker parameterChecker = ParameterChecker.getInstance();

        String name = request.getParameter(EDIT_NAME_ATTRIBUTE);
        String periodString = request.getParameter(EDIT_PERIOD_ATTRIBUTE);
        String guestVisitsString = request.getParameter(EDIT_GUESTS_VISIT_ATTRIBUTE);
        String trainingCountString = request.getParameter(EDIT_TRAINING_COUNT_ATTRIBUTE);
        String isUnlimString = request.getParameter(UNLIM_ATTRIBUTE);
        String currentSubscriptionName = request.getParameter(CURRENT_SUBSCRIPTION_NAME_ATTRIBUTE);

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
            Optional<Subscription> optionalSubscription = service.editSubscription(currentSubscriptionName, name, period, guestVisits, trainingCount);

            if (optionalSubscription.isPresent()) {
                Subscription subscription = optionalSubscription.get();
                List<Subscription> subscriptionList = (List<Subscription>) request.getAttribute(SUBSCRIPTIONS_LIST_ATTRIBUTE);

                subscriptionList.removeIf(p -> subscription.getSubcriptionId() == p.getSubcriptionId());
                subscriptionList.add(subscription);

                request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, true);
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
