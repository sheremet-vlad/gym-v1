<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="table-wrapper ">
    <table class="table table-striped" id="info-table">
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