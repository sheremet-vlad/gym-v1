package by.sheremchuk.gym.command;

import by.sheremchuk.gym.dao.CardDao;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.CardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static by.sheremchuk.gym.variable.AttributeName.CLEAR_PARAMETERS_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.CLIENTS_LIST_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.COMMAND_PARAMETER;
import static by.sheremchuk.gym.variable.AttributeName.ERROR_MESSAGE_ATTRIBUTE;
import static by.sheremchuk.gym.variable.AttributeName.SEPARATOR_IN_COMMAND;

public class StartTrainingCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter(COMMAND_PARAMETER);
        String stringClientId = command.substring(command.indexOf(SEPARATOR_IN_COMMAND) + 1);

        try {
            int clientId = Integer.parseInt(stringClientId);

            List<Client> clients = (List<Client>)request.getAttribute(CLIENTS_LIST_ATTRIBUTE);

            Client client = clients.get(clientId - 1);
            if (client.getCard() != null) {
                CardService cardService = CardService.getInstance();
                cardService.startTraining(client);
            }
            request.setAttribute(CLIENTS_LIST_ATTRIBUTE, clients);
            request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, true);
        } catch (ServiceException e) {
            e.printStackTrace();
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, "ошибка");
        }
    }
}
