<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="clients" type="java.util.ArrayList" scope="request"/>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Главная страница</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/mainPage.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/style.css"/>

        <script src="${pageContext.servletContext.contextPath}/styles/jquery.js"></script>
        <script>
            $(function() {
                $("a[rel]").overlay(function() {
                    var wrap = this.getContent().find("div.wrap");
                    if (wrap.is(":empty")) {
                        wrap.load(this.getTrigger().attr("href"));
                    }
                });
            });

        </script>

    </head>
    <body>
        <form class="form-wrapper">
            <input name="surnameOrCardNumber" id="search" placeholder="Введите фамилию или номер карты" required="" type="text">
            <input name="command" value="Find" id="submit" type="submit">
        </form>




        <a href="${pageContext.servletContext.contextPath}/styles/addNewClient.html" rel="#overlay">
            <input value="Добавить нового клиента" class="addNewClient" id="addNewClient" type="submit">
        </a>
        <div class="overlay" id="overlay">
            <div class="wrap"></div>
        </div>

        <div class="table-wrapper">
            <table class="mainTable">
                <thead>
                <tr class="mainTableTR">
                    <th class="mainTableTH">№</th>
                    <th class="mainTableTH">ФИО</th>
                    <th class="mainTableTH">№ Карты</th>
                    <th class="mainTableTH">Инф. об абон.</th>
                    <th class="mainTableTH">Пришёл</th>
                    <th class="mainTableTH">Ушёл</th>
                    <th class="mainTableTH">Инф-ция</th>
                    <th class="mainTableTH">Доб. абонемент</th>
                    <th class="mainTableTH">Статус</th>
                </tr>
                </thead>

                <c:forEach var="client" items="${clients}">
                    <tbody>

                    <tr class="mainTableTR">
                        <td class="mainTableTD">${client.id}</td>
                        <td class="mainTableTD">${client.fio}</td>
                        <td class="mainTableTD">${client.cardNumber}</td>
                        <td class="mainTableTD">10.11.2018-28.11.2018(15)</td>
                        <td class="mainTableTD">
                            <div class="content">
                                <button class="buttonInTable" id="come">+</button>
                            </div>
                        </td>
                        <td class="mainTableTD">
                            <div class="content">
                                <button class="buttonInTable" id="out">-</button>
                            </div>
                        <td class="mainTableTD">
                            <div class="content">
                                <button class="buttonInTable" id="clientInfo">...</button>
                            </div>
                        <td class="mainTableTD">
                            <div class="content">
                                <button class="buttonInTable" id="abonementAdd">Добавить</button>
                            </div>
                        </td>
                        <td class="mainTableTD">${client.status}</td>
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
            <input type="submit" name="command" value="New Abonement" class="btnFooter"/>
            <input type="submit" name="command" value="Edit Abonement" class="btnFooter"/>
        </div>
    </body>
</html>
