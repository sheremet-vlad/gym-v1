package by.sheremchuk.gym.entity;

import java.util.Date;
import java.util.Objects;

public class TrainingHistory {
    private int clientId;
    private int subscriptionId;
    private Date startDate;
    private Date endDate;

    public TrainingHistory(int clientId, int subscriptionId, Date startDate, Date endDate) {
        this.clientId = clientId;
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingHistory that = (TrainingHistory) o;
        return clientId == that.clientId &&
                subscriptionId == that.subscriptionId &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, subscriptionId, startDate, endDate);
    }

    @Override
    public String toString() {
        return "TrainingHistory{" +
                "clientId=" + clientId +
                ", subscriptionId=" + subscriptionId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
