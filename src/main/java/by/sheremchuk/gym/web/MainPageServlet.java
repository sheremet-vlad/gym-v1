package by.sheremchuk.gym.web;

import by.sheremchuk.gym.command.Command;
import by.sheremchuk.gym.command.CommandFactory;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;
import by.sheremchuk.gym.service.ClientService;
import by.sheremchuk.gym.service.SubscriptionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.sheremchuk.gym.variable.AttributeName.SUBSCRIPTIONS_LIST_ATTRIBUTE;

public class MainPageServlet extends HttpServlet {
    private final static String CLEAR_PARAMETERS_ATTRIBUTE = "isClearParameter";

    private final static String COMMAND_PARAMETER = "command";
    private List<Client> clientList;
    private List<Subscription> subscriptionList;
    private CommandFactory commandFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        commandFactory = CommandFactory.getInstance();
        ClientService clientService = ClientService.getInstance();
        SubscriptionService subscriptionService = SubscriptionService.getInstance();

        try {
            clientList = clientService.findAllClients().orElse(new ArrayList<>());
            subscriptionList = subscriptionService.findAllSubscriptions().orElse(new ArrayList<>());
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/WEB-INF/pages/mainPage.jsp";
        request.setAttribute("clients", clientList);
        request.setAttribute(SUBSCRIPTIONS_LIST_ATTRIBUTE, subscriptionList);
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
        if (isClearParameter) {
            redirectPage(response);
        } else {
            forwardPage(request, response, page);
        }

    }

    private void forwardPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void redirectPage(HttpServletResponse response) throws IOException {
        response.sendRedirect(getServletContext().getContextPath() + "/main-page");
    }
}
