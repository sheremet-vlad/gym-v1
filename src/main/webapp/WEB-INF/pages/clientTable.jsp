<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="table-wrapper ">
    <form method="post">
        <input type="hidden" name="current-client" id="current-button-id"/>
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
            <c:forEach var="client" items="${clients}" varStatus="index">
                <tbody>

                <tr>
                    <td>${client.id}</td>
                    <td>${client.fio}</td>
                    <td>${client.cardNumber}</td>
                    <td>10.11.2018-28.11.2018(15)</td>
                    <td>
                        <input value="+" class="buttonInTable" id="come" type="submit">
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