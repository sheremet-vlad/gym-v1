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

    private final String COMMAND_PARAMETER = "command";
    List<Client> clientList;
    @Override
    public void init() throws ServletException{
        super.init();
        ClientService clientService = new ClientService();
        try {
            clientList = clientService.findAllClients().orElse(new ArrayList<>());
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter(COMMAND_PARAMETER);
        if (command == null) {
            command = "";
        }
        Command action = CommandFactory.create(command);
        String page = "/WEB-INF/pages/mainPage.jsp";
        try {
            action.execute(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
            page = "/WEB-INF/pages/errorPage.jsp";
        }
        request.setAttribute("clients", clientList);
        dispatch(request, response, page);
    }


    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
