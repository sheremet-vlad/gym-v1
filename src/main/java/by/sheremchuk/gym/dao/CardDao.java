package by.sheremchuk.gym.dao;

import by.sheremchuk.gym.database.ConnectorDB;
import by.sheremchuk.gym.entity.Card;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.DaoException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class CardDao implements Dao {
    private final static String REPLACE_REGEX = "var";

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

    public Optional<Card> addSubscriptionToClient(Card card) throws DaoException{
        String query = "INSERT INTO `gym-v1`.`card` (`clients_id`, `subscriptions_id`, `guest_count`, `training_count`) " +
                "VALUES ('var', 'var', 'var', 'var', 'var', 'var', 'var')";

        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getClientID()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getSubscriptionId()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getGuestCount()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(card.getTrainingCount()));

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
}
