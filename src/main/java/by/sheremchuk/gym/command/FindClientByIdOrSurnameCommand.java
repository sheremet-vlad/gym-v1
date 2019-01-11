package by.sheremchuk.gym.command;

import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class FindClientByIdOrSurnameCommand implements Command {

    private static final String QUERY_PARAMETER = "surnameOrCardNumber";
    private static final String FOUND_CLIENTS_ATTRIBUTE = "surnameOrCardNumber";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String query = String.valueOf(request.getParameter(QUERY_PARAMETER));
        ClientService service = ClientService.getInstance();
        Optional<List<Client>> optionalClients = service.findClientByCardNumberOrSurname(query);
        if (optionalClients.isPresent()) {
            List<Client> clients = optionalClients.get();
            request.setAttribute(FOUND_CLIENTS_ATTRIBUTE, clients);
        }
    }

}
