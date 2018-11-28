package by.sheremchuk.gym.web;

import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.entity.enums.StatusEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> tempList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            Client client = new Client(i,"Igor" + i, "Yaremchuk" + i, "Romanich" + i,
                    "80291111111", GenderEnum.MAN, StatusEnum.OUT, "", new Date(), 11);

            client.setFio(client.getSurname() + " "
                    + client.getName() + " "
                    + client.getMiddleName());

            tempList.add(client);

        }

        request.setAttribute("clients", tempList);
        request.getRequestDispatcher("/WEB-INF/pages/mainPage.jsp").forward(request, response);
    }
}
