<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div>
    <a href="javascript:PopUpShowEditSubscription()">
        <button class="btnFooter" id="editSubscription">Редактировать абонемент</button>
    </a>
</div>

<div class="b-popup b-popup-edit-subscription" id="popupEditSubscription">
    <div class="b-popup-content b-popup-content-subscription">
        <h2>Добавление нового абонемента</h2>

        <form method="post">
            <select size="1" name="currentSubscription">
                <c:forEach var="subscription" items="${subscriptions}">
                    <option value="${subscription.subcriptionId}">${subscription.name}</option>
                </c:forEach>
            </select>
            <p>Название</p>
            <input class="input" name="subNameEdit" type="text" value="${param.subNameEdit}"/>
            <p>Длительность (дней)</p>
            <input class="input" name="subPeriodEdit" type="number" value="${param.subPeriodEdit}"/>
            <p>Количество гостевых визитов</p>
            <input class="input" name="subGuestsVisitsEdit" type="number" value="${param.subGuestsVisitsEdit}"/>

            <div class="training-count-block">
                <p class="training-count">  Количество посещений<br>
                    <input class="input" name="subTrainingCountEdit" type="number" value="${param.subTrainingCountEdit}"/></p>
                <p class="training-count radio-training-count">
                    <label>
                        <input class="input" name="subTrainingUnlimEdit" type="checkbox" value="${param.subTrainingUnlimEdit}" />Безлим
                    </label>
                </p>
            </div>

            <p>
                <button class="button-edit" name="command" value="editSubscriptionCommand" >Добавить</button>
                <a href="javascript:PopUpHideEditSubscription()">Back</a>
            </p>
        </form>

    </div>
</div>
