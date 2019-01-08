<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="b-popup" id="popupAddSubscriptionClient">
    <div class="b-popup-content">
        <h2>Назначить абонемент</h2>
        <form method="post">
            <select id="editSelect" size="1" name="currentSubscriptionName">
                <option value="-1">Выберите абонемент</option>
                <c:forEach var="subscription" items="${subscriptions}">
                    <option value="${subscription.subcriptionId}" ${currentSubscription.subcriptionId == subscription.subcriptionId ? 'selected="selected"' : ''}>
                            ${subscription.name}
                    </option>
                </c:forEach>
            </select>
            <p>
                <button class="button-edit" name="command" value="addSubscriptionClient" >Назначить</button>
                <a href="javascript:PopUpHideAddSubscriptionClient()">Back</a>
            </p>
        </form>

    </div>
</div>
