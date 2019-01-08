package by.sheremchuk.gym.entity;

import java.util.Objects;

public class Subscription {
    private int subcriptionId;
    private String name;
    private int guestVisit;
    private int dayCount;
    private int trainingCount;

    public Subscription() {

    }

    public Subscription(int subcriptionId, String name, int guestVisit, int dayCount, int trainingCount) {
        this.subcriptionId = subcriptionId;
        this.name = name;
        this.guestVisit = guestVisit;
        this.dayCount = dayCount;
        this.trainingCount = trainingCount;
    }

    public int getSubcriptionId() {
        return subcriptionId;
    }

    public void setSubcriptionId(int subcriptionId) {
        this.subcriptionId = subcriptionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuestVisit() {
        return guestVisit;
    }

    public void setGuestVisit(int guestVisit) {
        this.guestVisit = guestVisit;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getTrainingCount() {
        return trainingCount;
    }

    public void setTrainingCount(int trainingCount) {
        this.trainingCount = trainingCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return subcriptionId == that.subcriptionId &&
                guestVisit == that.guestVisit &&
                dayCount == that.dayCount &&
                trainingCount == that.trainingCount &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subcriptionId, name, guestVisit, dayCount, trainingCount);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subcriptionId=" + subcriptionId +
                ", name='" + name + '\'' +
                ", guestVisit=" + guestVisit +
                ", dayCount=" + dayCount +
                ", trainingCount=" + trainingCount +
                '}';
    }
}
