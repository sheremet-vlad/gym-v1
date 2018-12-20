package by.sheremchuk.gym.command;

import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindClientByIdOrSurnameCommand implements Command {

    private static final String QUERY_PARAMETER = "surnameOrCardNumber";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String query = String.valueOf(request.getParameter(QUERY_PARAMETER));
        ClientService service = new ClientService();
        service.findClientByCardNumberOrSurname(query);
    }

}
