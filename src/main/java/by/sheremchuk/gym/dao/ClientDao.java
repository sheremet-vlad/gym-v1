package by.sheremchuk.gym.dao;

import by.sheremchuk.gym.database.ConnectorDB;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.entity.enums.StatusEnum;
import by.sheremchuk.gym.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao {
    private final static String REPLACE_REGEX = "var";

    private final static String DATE_REGEX = "yyyy-MM-dd";

    private static volatile ClientDao instance;
    private final static Object lock = new Object();

    private ClientDao() {

    }

    public static ClientDao getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ClientDao();
                }
            }
        }

        return instance;
    }

    public Optional<List<Client>> findClientByCardNumber(int cardNumber)  throws DaoException{
        String queryFIndClientByCardNumber = "SELECT * FROM clients WHERE `cart_Number`='" + cardNumber + "'";
        return getClient(queryFIndClientByCardNumber);
    }

    public Optional<List<Client>> findClientBySurname(String surname)  throws DaoException{
        String queryFindClientBySurname = "SELECT * FROM clients WHERE surname = '" + surname + "'";
        return getClient(queryFindClientBySurname);
    }

    public Optional<List<Client>> findClientById(int id) throws DaoException {
        String queryFIndClientById = "SELECT * FROM clients WHERE clients_id = 'var'";
        queryFIndClientById = queryFIndClientById.replaceFirst(REPLACE_REGEX, String.valueOf(id));
        return getClient(queryFIndClientById);
    }
    public Optional<List<Client>> findAllClient()  throws DaoException{
        String queryFindAllClients = "SELECT * FROM clients";
        return getClient(queryFindAllClients);
    }

    public Optional<Client> addClient(Client client) throws DaoException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_REGEX);

        String queryAddClient = "INSERT INTO `gym-v1`.`clients` (`name`, `surname`, `middle_name`, `phone_number`, `gender`, `birthday`, `cart_number`) VALUES ('var', 'var', 'var', 'var', 'var', 'var', 'var')";
        queryAddClient = queryAddClient.replaceFirst(REPLACE_REGEX, client.getName());
        queryAddClient = queryAddClient.replaceFirst(REPLACE_REGEX, client.getSurname());
        queryAddClient = queryAddClient.replaceFirst(REPLACE_REGEX, client.getMiddleName());
        queryAddClient = queryAddClient.replaceFirst(REPLACE_REGEX, client.getPhoneNumber());
        queryAddClient = queryAddClient.replaceFirst(REPLACE_REGEX, client.getGender().getValue());
        queryAddClient = queryAddClient.replaceFirst(REPLACE_REGEX, dateFormat.format(client.getBirthday()));
        queryAddClient = queryAddClient.replaceFirst(REPLACE_REGEX, client.getSurname());

        Statement statement;
        try {
            statement = ConnectorDB.getConnection().createStatement();
            int status = statement.executeUpdate(queryAddClient);
            if (status == 2) {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return Optional.of(client);
    }

    private Optional<List<Client>> getClient(String query) throws DaoException {
        List<Client> clients = new ArrayList<>();
        Statement statement;
        try {
            statement = ConnectorDB.getConnection().createStatement();
            ResultSet request = statement.executeQuery(query);
            while (request.next()) {
                clients.add(createClient(request));
            }
        }
        catch (SQLException | NullPointerException e) {
            throw new DaoException(e.getMessage());
        }

        return Optional.of(clients);
    }

    public Optional<List<Client>> findClientByBirthdayToday() throws DaoException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_REGEX);
        String query = "SELECT * FROM clients WHERE MONTH(birthday) = MONTH('var') and DAY(birthday) = DAY('var')";
        query = query.replaceAll(REPLACE_REGEX, simpleDateFormat.format(new Date()));

        return getClient(query);
    }

    private Client createClient(ResultSet request) throws SQLException{
        Client client = new Client();

        client.setId(request.getInt("clients_id"));
        client.setName(request.getString("name"));
        client.setSurname(request.getString("surname"));
        client.setMiddleName(request.getString("middle_name"));
        client.setPhoneNumber(request.getString("phone_number"));
        client.setGender(GenderEnum.fromString(request.getString("gender")));
        client.setStatus(StatusEnum.fromString(request.getString("status")));
        client.setComments(request.getString("comments"));
        client.setBirthday(request.getDate("birthday"));
        client.setCardNumber(request.getString("cart_number"));
        client.setFio(client.getSurname() + " " + client.getName() + " " + client.getMiddleName());

        return client;
    }
}
