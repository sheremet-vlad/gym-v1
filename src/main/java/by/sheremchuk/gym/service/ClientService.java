package by.sheremchuk.gym.service;

import by.sheremchuk.gym.dao.ClientDao;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ClientService {

    public Optional<List<Client>> findAllClients() throws ServiceException {
        ClientDao clientDao = new ClientDao();
        return clientDao.findAllClient();
    }

}
