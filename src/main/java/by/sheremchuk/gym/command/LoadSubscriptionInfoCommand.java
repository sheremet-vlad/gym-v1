package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.sheremchuk.gym.variable.AttributeName.CLEAR_PARAMETERS_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.CURRENT_SUBSCRIPTION_NAME_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.SUBSCRIPTIONS_LIST_ATTRIBUTE;

public class LoadSubscriptionInfoCommand implements Command {
    private static final String CURRENT_SUBSCRIPTION_ATTRIBUTE = "currentSubscription";
    private static final String DEFAULT_OPTION = "-1";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String currentSubscriptionName = request.getParameter(CURRENT_SUBSCRIPTION_NAME_ATTRIBUTE);
        List<Subscription> subscriptions = (List) request.getAttribute(SUBSCRIPTIONS_LIST_ATTRIBUTE);

        Subscription subscription = subscriptions.stream()
                .filter(p -> currentSubscriptionName.equals(p.getName()))
                .findAny()
                .orElse(null);
        System.out.println(currentSubscriptionName);
        if (currentSubscriptionName.equals(DEFAULT_OPTION)) {
            System.out.println("SSss");
            request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, true);
        }
        request.setAttribute(CURRENT_SUBSCRIPTION_ATTRIBUTE, subscription);
    }
}
