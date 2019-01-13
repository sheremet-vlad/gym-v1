package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.web.MainPageServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.sheremchuk.gym.variable.AttributeName.CURRENT_SUBSCRIPTION_NAME_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.SUBSCRIPTIONS_LIST_ATTRIBUTE;

public class AddSubscriptionToClientCommand extends MainPageServlet implements Command  {
    private final static String SUBSCRIPTION_NAME_ATTRIBUTE = "subscriptionAddToClientName";
    private final static String CLIENT_ID_ATTRIBUTE = "current-button-id";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Subscription> list = (List<Subscription>) request.getAttribute(SUBSCRIPTIONS_LIST_ATTRIBUTE);
        String currentSubscrpion = request.getParameter(SUBSCRIPTION_NAME_ATTRIBUTE);
        String stringClientId = request.getParameter(CLIENT_ID_ATTRIBUTE);


    }
}
