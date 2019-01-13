package by.sheremchuk.gym.entity;

import java.util.Date;
import java.util.Objects;

public class Card {
    private int clientID;
    private int subscriptionId;
    private Date startDate;
    private Date endDate;
    private int trainingCount;
    private int guestCount;

    public Card() {

    }

    public Card(int clientID, int subscriptionId, Date startDate, Date endDate, int trainingCount, int guestCount) {
        this.clientID = clientID;
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainingCount = trainingCount;
        this.guestCount = guestCount;
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
                Objects.equals(endDate, card.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, subscriptionId, startDate, endDate, trainingCount, guestCount);
    }

    @Override
    public String toString() {
        return "Card{" +
                "clientID=" + clientID +
                ", subscriptionId=" + subscriptionId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", trainingCount=" + trainingCount +
                ", guestCount=" + guestCount +
                '}';
    }
}
