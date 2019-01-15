package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Card;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.CardService;
import by.sheremchuk.gym.web.MainPageServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.sheremchuk.gym.variable.AttributeName.CLEAR_PARAMETERS_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.CLIENTS_LIST_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.ERROR_MESSAGE_ATTRIBUTE;

public class AddSubscriptionToClientCommand extends MainPageServlet implements Command {
    private final static String SUBSCRIPTION_NAME_ATTRIBUTE = "subscriptionAddToClientName";
    private final static String CLIENT_ID_ATTRIBUTE = "current-button-id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String currentSubscription = request.getParameter(SUBSCRIPTION_NAME_ATTRIBUTE);
        String stringClientId = request.getParameter(CLIENT_ID_ATTRIBUTE);

        CardService cardService = CardService.getInstance();

        try {
            int clientId = Integer.parseInt(stringClientId);
            Card card = cardService.addSubscriptionToClient(clientId, currentSubscription);

            List<Client> clients = (List<Client>) request.getAttribute(CLIENTS_LIST_ATTRIBUTE);

            Client client = clients.get(clientId - 1);
            if (client.getCard() == null) {
                client.setCard(card);
            }
            System.out.println(clients.get(clientId - 1).getCard());
            request.setAttribute(CLIENTS_LIST_ATTRIBUTE, clients);
            request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, true);
        } catch (ServiceException e) {
            e.printStackTrace();
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
        }
    }
}
