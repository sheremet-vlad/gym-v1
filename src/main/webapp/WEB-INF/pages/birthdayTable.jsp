<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

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
