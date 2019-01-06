package by.sheremchuk.gym.service;

import by.sheremchuk.gym.dao.SubscriptionDao;
import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class SubscriptionService {
    private static volatile SubscriptionService instance;
    private final static Object lock = new Object();

    private SubscriptionDao subscriptionDao = SubscriptionDao.getInstance();

    private SubscriptionService() {

    }

    public static SubscriptionService getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SubscriptionService();
                }
            }
        }

        return instance;
    }

    public boolean addSubscription(String name,
                                   int period,
                                   int guestVisit,
                                   int trainingCount) throws ServiceException {
        Subscription subscription = new Subscription();

        subscription.setName(name);
        subscription.setDayCount(period);
        subscription.setGuestVisit(guestVisit);
        subscription.setTrainingCount(trainingCount);

        Optional<List<Subscription>> optionalSubscriptionsFromDB = subscriptionDao.findSubscriptionByName(subscription.getName());

        if (optionalSubscriptionsFromDB.isPresent()) {
            if (optionalSubscriptionsFromDB.get().size() != 0) {
                throw new ServiceException("уже существут абонемент с таким названием");
            }
        }

        return subscriptionDao.addSubscription(subscription);
    }

    public Optional<List<Subscription>> findAllSubscriptions() throws ServiceException {
        return subscriptionDao.findAllSubscroptions();
    }
}
