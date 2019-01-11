package by.sheremchuk.gym.entity;

import by.sheremchuk.gym.entity.enums.GenderEnum;
import by.sheremchuk.gym.entity.enums.StatusEnum;

import java.util.Date;
import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String middleName;
    private String phoneNumber;
    private GenderEnum gender;
    private StatusEnum status;
    private String comments;
    private Date birthday;
    private String cardNumber;
    private String fio;

    public Client() {
    }

    public Client(int id,
                  String name,
                  String surname,
                  String middleName,
                  String phoneNumber,
                  GenderEnum gender,
                  StatusEnum status,
                  String comments,
                  Date birthday,
                  String cardNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.status = status;
        this.comments = comments;
        this.birthday = birthday;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cartNumber) {
        this.cardNumber = cartNumber;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                cardNumber == client.cardNumber &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(middleName, client.middleName) &&
                Objects.equals(phoneNumber, client.phoneNumber) &&
                gender == client.gender &&
                status == client.status &&
                Objects.equals(comments, client.comments) &&
                Objects.equals(birthday, client.birthday) &&
                Objects.equals(fio, client.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, middleName, phoneNumber, gender, status, comments, birthday, cardNumber, fio);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                ", birthday=" + birthday +
                ", cardNumber=" + cardNumber +
                ", fio='" + fio + '\'' +
                '}';
    }
}
