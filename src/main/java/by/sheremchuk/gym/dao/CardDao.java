package by.sheremchuk.gym.dao;

import by.sheremchuk.gym.database.ConnectorDB;
import by.sheremchuk.gym.entity.Card;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.enums.CardStatusEnum;
import by.sheremchuk.gym.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class CardDao implements Dao {
    private final static String REPLACE_REGEX = "var";
    private final static String DATE_REGEX_TO_DB = "yyyy-MM-dd";
    private final static String DATE_REGEX_TO_CARD = "dd:MM:yyyy";

    private static volatile CardDao instance;
    private final static Object lock = new Object();

    private CardDao() {

    }

    public static CardDao getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new CardDao();
                }
            }
        }

        return instance;
    }

    public Optional<Card> loadCardsToClients(List<Client> clients) throws DaoException {
        try {
           Statement statement = ConnectorDB.getConnection().createStatement();

           for (Client client : clients) {
               String query = "SELECT * FROM `card` WHERE `clients_id` = 'var' and `status` != 'end' LIMIT 1";
               query = query.replaceFirst(REPLACE_REGEX, String.valueOf(client.getId()));

               ResultSet resultSet = statement.executeQuery(query);
               if (resultSet.next()) {
                   client.setCard(createCard(resultSet));
               } else {
                   client.setCard(null);
               }

           }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("ошибка в базе данных при загрузке карт");
        }

        return Optional.empty();
    }

    public Optional<Card> addSubscriptionToClient(Card card) throws DaoException {
        String query = "INSERT INTO `gym-v1`.`card` (`clients_id`, `subscriptions_id`, `guest_count`, `training_count`, `subsription_name`) " +
                "VALUES ('var', 'var', 'var', 'var', 'var')";

        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getClientID()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getSubscriptionId()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getGuestCount()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getTrainingCount()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getSubscriptionName()));

        Statement statement;
        try {
            statement = ConnectorDB.getConnection().createStatement();
            int status = statement.executeUpdate(query);
            if (status == 2) {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return Optional.of(card);
    }

    public int findIdForCard(Card card) throws DaoException {
        String query = "SELECT card_id from card where clients_id = 'var' and subscriptions_id = 'var'" +
                " and guest_count = 'var' and status = 'no_active' and subsription_name = 'var' LIMIT 1";
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getClientID()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getSubscriptionId()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getGuestCount()));
        query = query.replaceFirst(REPLACE_REGEX, card.getStatus().getValue());
        query = query.replaceFirst(REPLACE_REGEX, card.getSubscriptionName());

        int result;
        Statement statement;
        try {
            statement = ConnectorDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            result = resultSet.next() ? resultSet.getInt(1) : -1;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return result;
    }

    public void updateActivationCardByIndex(Card card) throws DaoException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_REGEX_TO_DB);

        String query = "UPDATE card SET `start_date` = 'var', `end_date` = 'var', `status` = 'var' WHERE `card_id` = 'var'";
        query = query.replaceFirst(REPLACE_REGEX, dateFormat.format(card.getStartDate()));
        query = query.replaceFirst(REPLACE_REGEX, dateFormat.format(card.getEndDate()));
        query = query.replaceFirst(REPLACE_REGEX, card.getStatus().getValue());
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getId()));
        System.out.println(card.getId());

        try {
            Statement statement = ConnectorDB.getConnection().createStatement();
            int status = statement.executeUpdate(query);
            if (status == 2) {
                throw new DaoException("ошибка при добавлении");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private Card createCard(ResultSet resultSet) throws SQLException {
        Card card = new Card();

        card.setId(resultSet.getInt("card_id"));
        card.setClientID(resultSet.getInt("clients_id"));
        card.setStatus(CardStatusEnum.fromString(resultSet.getString("status")));
        card.setTrainingCount(resultSet.getInt("training_count"));
        card.setGuestCount(resultSet.getInt("guest_count"));
        card.setSubscriptionName(resultSet.getString("subsription_name"));

        if (card.getStatus().equals(CardStatusEnum.ACTIVE)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_REGEX_TO_CARD);
            card.setStartDate(resultSet.getDate("start_date"));
            card.setEndDate(resultSet.getDate("end_date"));
            String cardInfo = simpleDateFormat.format(card.getStartDate()) + " - " +
                    simpleDateFormat.format(card.getEndDate()) +
                    "(" + card.getTrainingCount() + ")";
            card.setCardInfo(cardInfo);
        }

        return card;
    }
}
