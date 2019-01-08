package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.sheremchuk.gym.variable.AttributeName.SUBSCRIPTIONS_LIST_ATTRIBUTE;

public class LoadSubscriptionInfoCommand implements Command {
    private static final String CURRENT_SUBSCRIPTION_ID_ATTRIBUTE = "currentSubscriptionName";
    private static final String CURRENT_SUBSCRIPTION_ATTRIBUTE = "currentSubscription";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int currentSubscriptionId = Integer.parseInt(request.getParameter(CURRENT_SUBSCRIPTION_ID_ATTRIBUTE));
        List<Subscription> subscriptions = (List) request.getAttribute(SUBSCRIPTIONS_LIST_ATTRIBUTE);

        Subscription subscription = subscriptions.stream()
                .filter(p -> currentSubscriptionId == p.getSubcriptionId())
                .findAny()
                .orElse(new Subscription());

        request.setAttribute(CURRENT_SUBSCRIPTION_ATTRIBUTE, subscription);
    }
}
