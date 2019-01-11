<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="table-wrapper ">
    <form method="post">
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
                    <div class="content">
                        <input value="+" class="buttonInTable" id="come" type="submit">
                    </div>
                </td>
                <td>
                    <div class="content">
                        <input value="-" class="buttonInTable" id="out" type="submit">
                    </div>
                <td>
                    <jsp:include page="clientInfo.jsp"/>
                <td>
                    <div class="b-container-newSubscription">
                        <a href="javascript:PopUpShowAddSubscriptionClient(${client.id})">
                            <button class="buttonInTable" name="command" id="subscriptionAdd" value="button${client.id}" >Добавить</button>
                        </a>
                    </div>
                </td>
                <td>${client.status}</td>
            </tr>
            </tbody>
        </c:forEach>

    </table>
    </form>
    <script>
        function getClientID(clientId) {
            document.getElementById('current-client-id').value = clientId;
        }
    </script>
</div>