package by.sheremchuk.gym.service;

import by.sheremchuk.gym.dao.CardDao;
import by.sheremchuk.gym.dao.ClientDao;
import by.sheremchuk.gym.dao.SubscriptionDao;
import by.sheremchuk.gym.entity.Card;
import by.sheremchuk.gym.entity.Client;
import by.sheremchuk.gym.entity.Subscription;
import by.sheremchuk.gym.entity.enums.CardStatusEnum;
import by.sheremchuk.gym.exception.DaoException;
import by.sheremchuk.gym.exception.ServiceException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CardService {
    private final static String DATE_REGEX_TO_CARD = "dd:MM:yyyy";

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

    public Card addSubscriptionToClient(int clientId, String subscriptionName) throws ServiceException {
        SubscriptionDao subscriptionDao = SubscriptionDao.getInstance();
        Optional<List<Subscription>> optionalSubscriptions = subscriptionDao.findSubscriptionByName(subscriptionName);
        List<Subscription> subscriptions = optionalSubscriptions.orElseGet(ArrayList::new);

        Subscription subscription;
        if (subscriptions.size() == 1) {
            subscription = subscriptions.get(0);
        } else {
            throw new ServiceException("ошибка записей абонемента в базе данных");
        }

        ClientDao clientDao = ClientDao.getInstance();
        Optional<List<Client>> optionalClients = clientDao.findClientById(clientId);
        List<Client> clients = optionalClients.orElseGet(ArrayList::new);

        Client client;
        if (clients.size() == 1) {
            client = clients.get(0);
        } else {
            throw new ServiceException("ошибка записей клиента в базе данных");
        }

        Card card = new Card();
        card.setClientID(client.getId());
        card.setSubscriptionId(subscription.getSubcriptionId());
        card.setGuestCount(subscription.getGuestVisit());
        card.setTrainingCount(subscription.getTrainingCount());
        card.setSubscriptionName(subscription.getName());
        card.setStatus(CardStatusEnum.NOT_ACTIVE);

        CardDao cardDao = CardDao.getInstance();
        Optional<Card> optionalCard = cardDao.addSubscriptionToClient(card);

        if (!optionalCard.isPresent()) {
            throw new ServiceException("ошибка при добавлении абонемента");
        }

        int id = cardDao.findIdForCard(card);
        if (id == -1) {
            throw new ServiceException("ошибка при подсчете id");
        }

        card.setId(id);
        return optionalCard.get();
    }

    public void startTraining(Client client) throws ServiceException {
        Card card = client.getCard();

        if (card.getStatus().equals(CardStatusEnum.NOT_ACTIVE)) {
            activeCard(card);
        }
    }

    private void activeCard(Card card) throws DaoException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_REGEX_TO_CARD);
        card.setStatus(CardStatusEnum.ACTIVE);
        Date currentDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        SubscriptionDao subscriptionDao = SubscriptionDao.getInstance();
        List<Subscription> subscriptions = subscriptionDao.findSubscriptionByName(card.getSubscriptionName()).orElseThrow(DaoException::new);
        Subscription subscription = subscriptions.get(0);

        c.add(Calendar.DATE, subscription.getDayCount());

        card.setStartDate(currentDate);
        card.setEndDate(c.getTime());

        String cardInfo = simpleDateFormat.format(card.getStartDate()) + " - " +
                simpleDateFormat.format(card.getEndDate()) +
                "(" + card.getTrainingCount() + ")";
        card.setCardInfo(cardInfo);

        CardDao cardDao = CardDao.getInstance();
        cardDao.updateActivationCardByIndex(card);
    }

    public void loadCardToClients(List<Client> clients) throws ServiceException {
        CardDao cardDao = CardDao.getInstance();
        cardDao.loadCardsToClients(clients);
    }
}
