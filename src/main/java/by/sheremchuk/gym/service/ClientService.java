package by.sheremchuk.gym.service;

import by.sheremchuk.gym.dao.ClientDao;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.entity.enums.StatusEnum;
import by.sheremchuk.gym.exception.DaoException;
import by.sheremchuk.gym.exception.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private static final String REGEX_ALL_DIGITS = "^-?\\d+$";

    private static volatile ClientService instance;
    private final static Object lock = new Object();

    private ClientService() {

    }

    public static ClientService getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ClientService();
                }
            }
        }

        return instance;
    }

    public List<Client> findAllClients() throws ServiceException {
        ClientDao clientDao = ClientDao.getInstance();
        Optional<List<Client>> optionalClients = clientDao.findAllClient();

        List<Client> clients = optionalClients.orElseGet(ArrayList::new);

        CardService cardService = CardService.getInstance();
        cardService.loadCardToClients(clients);

        return clients;
    }

    public Optional<List<Client>> findClientByCardNumberOrSurname(String query) throws ServiceException {
        ClientDao clientDao = ClientDao.getInstance();
        if (checkString(query.trim())) {
            return clientDao.findClientByCardNumber(Integer.parseInt(query));
        } else {
            return clientDao.findClientBySurname(query);
        }
    }

    private boolean checkString(String string) {
        if (string == null) {
            return false;
        }
        return string.matches(REGEX_ALL_DIGITS);
    }

    public Optional<List<Client>> findClientsWithBirthdayToday() throws DaoException {
        ClientDao clientDao = ClientDao.getInstance();

        return clientDao.findClientByBirthdayToday();
    }
    public Optional<Client> addClient(String name,
                                      String surname,
                                      String middleName,
                                      Date birthday,
                                      GenderEnum gender,
                                      String comments,
                                      String phone,
                                      String cartNumber,
                                      int id) throws ServiceException {
        Client client = new Client();

        client.setName(name);
        client.setSurname(surname);
        client.setMiddleName(middleName);
        client.setBirthday(birthday);
        client.setGender(gender);
        client.setComments(comments);
        client.setPhoneNumber(phone);
        client.setCardNumber(cartNumber);
        client.setFio(surname + " " + name + " " + middleName);
        client.setStatus(StatusEnum.OUT);
        client.setId(id);

        ClientDao clientDao = ClientDao.getInstance();
        return clientDao.addClient(client);
    }

}
