package by.sheremchuk.gym.web;

import by.sheremchuk.gym.command.Command;
import by.sheremchuk.gym.command.CommandFactory;
import by.sheremchuk.gym.dao.Dao;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.entity.enums.StatusEnum;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MainPageServlet extends HttpServlet {
    private final static String CLEAR_PARAMETERS_ATTRIBUTE = "isClearParameter";

    private final static String COMMAND_PARAMETER = "command";
    private List<Client> clientList;
    private CommandFactory commandFactory;
    @Override
    public void init() throws ServletException{
        super.init();
        commandFactory = CommandFactory.getInstance();
        ClientService clientService = ClientService.getInstance();

        try {
            clientList = clientService.findAllClients().orElse(new ArrayList<>());
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/WEB-INF/pages/mainPage.jsp";
        request.setAttribute("clients", clientList);
        request.setAttribute(CLEAR_PARAMETERS_ATTRIBUTE, false);

        response.setCharacterEncoding("UTF-8");

        String command = request.getParameter(COMMAND_PARAMETER);
        if (command == null) {
            command = "";
        }
        Command action = commandFactory.create(command);

        try {
            action.execute(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
            page = "/WEB-INF/pages/errorPage.jsp";
        }

        boolean isClearParameter = (boolean) request.getAttribute(CLEAR_PARAMETERS_ATTRIBUTE);

        if(isClearParameter) {
            redirect(response);
        } else {
            forward(request, response, page);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect(getServletContext().getContextPath() + "/main-page");
    }
}
