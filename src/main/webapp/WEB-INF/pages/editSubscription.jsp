<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div>
    <a href="javascript:PopUpShowEditSubscription()">
        <button class="btnFooter" id="editSubscription">Редактировать абонемент</button>
    </a>
</div>

<c:if test="${currentSubscription!= null}">
    <script type='text/javascript'>
        window.onload = function () {
            document.getElementById('editSubscription').click();
        }
    </script>
</c:if>
<<<<<<< HEAD
<div class="b-popup b-popup-edit-subscription" id="popupEditSubscription">
    <div class="b-popup-content b-popup-content-subscription" style="height: 360px">
=======

<div class="b-popup" id="popupEditSubscription">
    <div class="b-popup-content b-popup-content-edit-subscription">
>>>>>>> ed8f9b637bf396d9157a10202becca8fa74d3c30
        <h2>Добавление нового абонемента</h2>

        <form method="post">
            <input type="submit" class="hidden-button" id="buttonLoadSubscriptionInfo" name="command"
                   value="loadSubscriptionInfoInEdit"/>
            <select id="editSelect" size="1" name="currentSubscriptionName" onchange="ClickLoadInfo();">
                <option value="-1">Выберите абонемент</option>
                <c:forEach var="subscription" items="${subscriptions}">
                    <option value="${subscription.name}" ${currentSubscription.subcriptionId == subscription.subcriptionId ? 'selected="selected"' : ''}>
                            ${subscription.name}
                    </option>
                </c:forEach>
            </select>

            <p>Название</p>
            <input class="input" name="subNameEdit" type="text" value="${currentSubscription.name}"/>
            <p>Длительность (дней)</p>
            <input class="input" name="subPeriodEdit" type="number" value="${currentSubscription.dayCount}"/>
            <p>Количество гостевых визитов</p>
            <input class="input" name="subGuestsVisitsEdit" type="number" value="${currentSubscription.guestVisit}"/>
            <div class="training-count-block">
                <p class="training-count"> Количество посещений<br>
                    <input class="input" name="subTrainingCountEdit" type="number"
                           value="${currentSubscription.trainingCount != 88 ? currentSubscription.trainingCount : ''}"/>
                </p>
                <p class="training-count radio-training-count">
                    <label>
                        <input class="input" name="subTrainingUnlimEdit" type="checkbox"
                        ${currentSubscription.trainingCount == 88 ? 'checked="checked"' : ""}/>Безлим
                    </label>
                </p>
            </div>

            <p>
                <button class="button-edit" name="command" value="editSubscriptionCommand">Изменить</button>
                <a href="javascript:CloseEditSubscription()">Back</a>
            </p>
        </form>

    </div>
</div>

<script>
    function ClickLoadInfo() {
        document.getElementById("buttonLoadSubscriptionInfo").click();
    }

    function CloseEditSubscription() {
        document.getElementById("editSelect").value = "-1";
        ClickLoadInfo();
    }
</script>
