<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="clients" type="java.util.ArrayList" scope="request"/>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Главная страница</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/mainPage.css">
    </head>
    <body>
        <form class="form-wrapper">
            <input name="surnameOrCardNumber" id="search" placeholder="Введите фамилию или номер карты" required="" type="text">
            <input name="command" value="Find" id="submit" type="submit">
        </form>
        <input value="Добавить нового клиента" class="addNewClient" id="addNewClient" type="submit">
        <div class="table-wrapper">
            <table>
                <thead>
                <tr>
                    <th>№</th>
                    <th>ФИО</th>
                    <th>№ Карты</th>
                    <th>Инф. об абон.</th>
                    <th>Пришёл</th>
                    <th>Ушёл</th>
                    <th>Инф-ция</th>
                    <th>Доб. абонемент</th>
                    <th>Статус</th>
                </tr>
                </thead>

                <c:forEach var="client" items="${clients}">
                    <tbody>

                    <tr>
                        <td>${client.id}</td>
                        <td>${client.fio}</td>
                        <td>${client.cardNumber}</td>
                        <td>10.11.2018-28.11.2018(15)</td>
                        <td>
                            <div class="content">
                                <button class="buttonInTable" id="come">+</button>
                            </div>
                        </td>
                        <td>
                            <div class="content">
                                <button class="buttonInTable" id="out">-</button>
                            </div>
                        <td>
                            <div class="content">
                                <button class="buttonInTable" id="clientInfo">...</button>
                            </div>
                        <td>
                            <div class="content">
                                <button class="buttonInTable" id="abonementAdd">Добавить</button>
                            </div>
                        </td>
                        <td>${client.status}</td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
        <div class="footer-container">
            <div class="birthdayToDay">Сегодня день рождения</div>
            <div class="containerNowInGym">
                <div style="font: normal 30px Arial, Helvetica;">Сейчас в зале: </div>
                <div style="height: 35px; width: 40px; border: 1px solid black; float: right"></div>
            </div>
            <div class="footer-table">
                <table>
                    <thead>
                    <tr>
                        <th>ФИО</th>
                        <th>№ Карты</th>
                        <th>Телефон</th>
                    </tr>
                    </thead>
                    <c:forEach var="client" items="${clients}">
                        <tbody>
                        <tr>
                            <td>${client.fio}</td>
                            <td>${client.cardNumber}</td>
                            <td>${client.phoneNumber}</td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div>

            <input type="submit" name="command" value="Statistic" class="btnFooter"/>
            <button class="btnFooter" id="newAbonement">Новый абонемент</button>
            <button class="btnFooter" id="editAbonement">Редактировать абонемент</button>
        </div>
    </body>
</html>
