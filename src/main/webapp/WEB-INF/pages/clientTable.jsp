<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="table-wrapper ">
    <form method="post">
        <table class="table table-striped" id="info-table">
            <thead>
            <tr>
                <th>№</th>
                <th>ФИО</th>
                <th>Карта</th>
                <th>Тек. абон.</th>
                <th>Инф. об абон.</th>
                <th>+</th>
                <th>-</th>
                <th>Инф-ция</th>
                <th>Доб. абон.</th>
                <th>Статус</th>
            </tr>
            </thead>
            <c:forEach var="client" items="${clients}" varStatus="index">
                <tbody>
                <tr>
                    <td>${client.id}</td>
                    <td>${client.fio}</td>
                    <td>${client.cardNumber}</td>
                    <td>${client.card == null ? "--" : client.card.subscriptionName}</td>
                    <td>${client.card == null ? "--" : client.card.startDate != null ?
                        client.card.cardInfo : "не активен"}</td>
                    <td>
                        <button class="buttonInTable" name="command" value="startTraining|${client.id}">+</button>
                    </td>
                    <td>
                        <input value="-" class="buttonInTable" id="out" type="submit">
                    <td>
                            <jsp:include page="clientInfo.jsp"/>
                    <td>
                        <a class="link-in-table" href="javascript:PopUpShowAddSubscriptionClient(${client.id})">
                            Добавить
                        </a>
                    </td>
                    <td>${client.status}</td>
                </tr>
                </tbody>
            </c:forEach>

        </table>
    </form>
</div>