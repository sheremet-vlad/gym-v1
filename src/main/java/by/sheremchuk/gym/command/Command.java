package by.sheremchuk.gym.command;


import by.sheremchuk.gym.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
