package by.sheremchuk.gym.dao;

import by.sheremchuk.gym.database.ConnectorDB;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.entity.enums.StatusEnum;
import by.sheremchuk.gym.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao implements Dao {

    public Optional<List<Client>> findClientByCardNumber(int cardNumber)  throws DaoException{
        String queryFIndClientByCardNumber = "SELECT * FROM clients WHERE `cart_Number`='" + cardNumber + "'";
        return getClient(queryFIndClientByCardNumber);
    }

    public Optional<List<Client>> findClientBySurname(String surname)  throws DaoException{
        String queryFindClientBySurname = "SELECT * FROM clients WHERE surname = '" + surname + "'";
        return getClient(queryFindClientBySurname);
    }
    public Optional<List<Client>> findAllClient()  throws DaoException{
        String queryFindAllClients = "SELECT * FROM clients";
        return getClient(queryFindAllClients);
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
        client.setCardNumber(request.getInt("cart_number"));
        client.setFio(client.getSurname() + " " + client.getName() + " " + client.getMiddleName());

        return client;
    }
}
