package by.sheremchuk.gym.service;

import by.sheremchuk.gym.dao.CardDao;
import by.sheremchuk.gym.dao.ClientDao;
import by.sheremchuk.gym.dao.SubscriptionDao;
import by.sheremchuk.gym.entity.Card;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardService {
    private static volatile CardService instance;
    private final static Object lock = new Object();

    private CardService() {

    }

    public static CardService getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new CardService();
                }
            }
        }

        return instance;
    }

    public Optional<Card> addSubscriptionToClient(int clientId, String subscriptionName) throws ServiceException {
        SubscriptionDao subscriptionDao = SubscriptionDao.getInstance();
        Optional<List<Subscription>> optionalSubscriptions = subscriptionDao.findSubscriptionByName(subscriptionName);
        List<Subscription> subscriptions = optionalSubscriptions.orElseGet(ArrayList::new);

        Subscription subscription = null;
        if (subscriptions.size() == 1) {
           subscription = subscriptions.get(0);
        } else {
            throw new ServiceException("ошибка записей в базе данных");
        }

        ClientDao clientDao = ClientDao.getInstance();
        Optional<List<Client>> optionalClients = clientDao.findClientById(clientId);
        List<Client> clients = optionalClients.orElseGet(ArrayList::new);

        Client client = null;
        if (clients.size() == 1) {
           client = clients.get(0);
        } else {
            throw new ServiceException("ошибка записей в базе данных");
        }

        Card card = new Card();
        card.setClientID(client.getId());
        card.setSubscriptionId(subscription.getSubcriptionId());
        card.setGuestCount(subscription.getGuestVisit());
        card.setTrainingCount(subscription.getTrainingCount());

        CardDao cardDao = CardDao.getInstance();
        Optional<Card> optionalCard = cardDao.addSubscriptionToClient(card);

        if (!optionalCard.isPresent()) {
            throw new ServiceException("ошибка при добавлении абонемента");
        }


    }
}
