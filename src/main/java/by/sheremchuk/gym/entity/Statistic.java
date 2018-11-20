package by.sheremchuk.gym.entity;

import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.entity.enums.PeriodEnum;

import java.util.Date;
import java.util.Objects;

public class Statistic {
    private int statisticId;
    private Date date;
    private PeriodEnum period;
    private GenderEnum genderEnum;

    public Statistic(int statisticId, Date date, PeriodEnum period, GenderEnum genderEnum) {
        this.statisticId = statisticId;
        this.date = date;
        this.period = period;
        this.genderEnum = genderEnum;
    }

    public int getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PeriodEnum getPeriod() {
        return period;
    }

    public void setPeriod(PeriodEnum period) {
        this.period = period;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return statisticId == statistic.statisticId &&
                Objects.equals(date, statistic.date) &&
                period == statistic.period &&
                genderEnum == statistic.genderEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statisticId, date, period, genderEnum);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "statisticId=" + statisticId +
                ", date=" + date +
                ", period=" + period +
                ", genderEnum=" + genderEnum +
                '}';
    }
}
