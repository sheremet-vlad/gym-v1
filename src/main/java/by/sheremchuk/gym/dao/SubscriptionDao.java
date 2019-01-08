package by.sheremchuk.gym.dao;

import by.sheremchuk.gym.database.ConnectorDB;
import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionDao implements Dao{
    private final static String REPLACE_REGEX = "var";

    private static volatile SubscriptionDao instance;
    private final static Object lock = new Object();

    private SubscriptionDao() {

    }

    public static SubscriptionDao getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SubscriptionDao();
                }
            }
        }

        return instance;
    }

    public Optional<Subscription> addSubscription (Subscription subscription) throws DaoException {
        String queryAddClient = "INSERT INTO `gym-v1`.`subscriptions` (`name`, `guest_visits`, `day_count`, `training_count`) VALUES ('var', 'var', 'var', 'var')";
        queryAddClient = addParameterToQuery(queryAddClient, subscription);

        try {
            Statement statement = ConnectorDB.getConnection().createStatement();
            int status = statement.executeUpdate(queryAddClient);
            if (status == 2) {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return Optional.of(subscription);
    }

    public Optional<List<Subscription>> findSubscriptionByName(String name) throws DaoException{
        String queryFindSubscription = "SELECT * FROM subscriptions WHERE `name`='var'";
        System.out.println(queryFindSubscription);
        System.out.println(name);
        queryFindSubscription = queryFindSubscription.replaceFirst(REPLACE_REGEX, name);

        Optional<List<Subscription>> optionalSubscriptions = getSubscriptions(queryFindSubscription);
        List<Subscription> subscriptions = optionalSubscriptions.orElseGet(ArrayList::new);

        return Optional.of(subscriptions);
    }

    public Optional<List<Subscription>> findAllSubscriptions() throws DaoException {
        String query = "SELECT * FROM subscriptions";

        return getSubscriptions(query);
    }

    private Optional<List<Subscription>> getSubscriptions(String query) throws DaoException{
        List<Subscription> subscriptions = new ArrayList<>();
        Statement statement;

        try {
            statement = ConnectorDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                subscriptions.add(createSubscription(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("ошибка в базе данных при проверке абонемента");
        }

        return Optional.of(subscriptions);
    }

    public boolean updateSubscriptionByIndex(Subscription subscription, int currentId) throws DaoException {
        String query = "UPDATE subscriptions SET `name` = 'var', `guest_visits` = 'var', `day_count` = 'var', `training_count` = 'var' WHERE `subscriptions_id` = 'var'";
        query = addParameterToQuery(query, subscription);
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(currentId));

        try {
            Statement statement = ConnectorDB.getConnection().createStatement();
            int status = statement.executeUpdate(query);
            if (status == 2) {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

        return true;
    }

    private Subscription createSubscription(ResultSet resultSet) throws SQLException {
        Subscription subscription = new Subscription();

        subscription.setName(resultSet.getString("name"));
        subscription.setTrainingCount(resultSet.getInt("training_count"));
        subscription.setGuestVisit(resultSet.getInt("guest_visits"));
        subscription.setDayCount(resultSet.getInt("day_count"));
        subscription.setSubcriptionId(resultSet.getInt("subscriptions_id"));

        return subscription;
    }

    private String addParameterToQuery(String query, Subscription subscription) {
        query = query.replaceFirst(REPLACE_REGEX, subscription.getName());
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(subscription.getGuestVisit()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(subscription.getDayCount()));
        query = query.replaceFirst(REPLACE_REGEX, String.valueOf(subscription.getTrainingCount()));

        return query;
    }
}
