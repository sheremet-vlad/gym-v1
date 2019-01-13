<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="b-popup" id="popupAddSubscriptionClient">
    <div class="b-popup-content" style="height: 140px;">
        <h2>Назначить абонемент</h2>
        <form method="post">
            <select id="editSelect" size="1" name="subscriptionAddToClientName">
                <option value="-1">Выберите абонемент</option>
                <c:forEach var="subscription" items="${subscriptions}">
                    <option value="${subscription.subcriptionId}">
                            ${subscription.name}
                    </option>
                </c:forEach>
            </select>
            <br><br>
            <input type="hidden" id="client-id-to-add-subscription" name="current-button-id"/>
            <p>
                <button class="button-edit" name="command" value="addSubscriptionClient" >Назначить</button>
                <a href="javascript:PopUpHideAddSubscriptionClient()">Back</a>
            </p>
        </form>

    </div>
</div>
