package by.sheremchuk.gym.command;


import by.sheremchuk.gym.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindClientCommand implements Command {

    private static final String QUERY_PARAMETER = "query";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String query = String.valueOf(request.getAttribute(QUERY_PARAMETER));

    }

}
