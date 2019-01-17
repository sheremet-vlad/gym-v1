package by.sheremchuk.gym.entity;

import by.sheremchuk.gym.entity.enums.CardStatusEnum;

import java.util.Date;
import java.util.Objects;

public class Card {
    private int id;
    private int clientID;
    private int subscriptionId;
    private Date startDate;
    private Date endDate;
    private int trainingCount;
    private int guestCount;
    private String subscriptionName;
    private CardStatusEnum status;
    private String cardInfo;

    public Card() {

    }

    public Card(int clientID, int subscriptionId, Date startDate, Date endDate, int trainingCount, int guestCount, String subscriptionName) {
        this.clientID = clientID;
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainingCount = trainingCount;
        this.guestCount = guestCount;
        this.subscriptionName = subscriptionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardStatusEnum getStatus() {
        return status;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    public void setStatus(CardStatusEnum status) {
        this.status = status;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTrainingCount() {
        return trainingCount;
    }

    public void setTrainingCount(int trainingCount) {
        this.trainingCount = trainingCount;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return clientID == card.clientID &&
                subscriptionId == card.subscriptionId &&
                trainingCount == card.trainingCount &&
                guestCount == card.guestCount &&
                Objects.equals(startDate, card.startDate) &&
                Objects.equals(endDate, card.endDate) &&
                Objects.equals(subscriptionName, card.subscriptionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, subscriptionId, startDate, endDate, trainingCount, guestCount, subscriptionName);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId" + id +
                "clientID=" + clientID +
                ", subscriptionId=" + subscriptionId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", trainingCount=" + trainingCount +
                ", guestCount=" + guestCount +
                ", subscriptionName=" + subscriptionName +
                ", hash=" + this.hashCode() +
                '}';
    }
}
