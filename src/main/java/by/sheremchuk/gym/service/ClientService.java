package by.sheremchuk.gym.service;

import by.sheremchuk.gym.dao.ClientDao;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private static final String REGEX_ALL_DIGITS = "^-?\\d+$";

    public Optional<List<Client>> findAllClients() throws ServiceException {
        ClientDao clientDao = new ClientDao();
        return clientDao.findAllClient();
    }

    public Optional<List<Client>> findClientByCardNumberOrSurname(String query) throws ServiceException {
        ClientDao clientDao = new ClientDao();
        if (checkString(query.trim())) {
            return clientDao.findClientByCardNumber(Integer.parseInt(query));
        } else {
            return clientDao.findClientBySurname(query);
        }
    }

    private boolean checkString(String string) {
        if (string == null) return false;
        return string.matches(REGEX_ALL_DIGITS);
    }

}
