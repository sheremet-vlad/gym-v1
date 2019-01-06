<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="b-container-newSubscription">
    <a href="javascript:PopUpShowNewSubscription()">
        <button class="btnFooter" id="newSubscription">Новый абонемент</button>
    </a>
</div>

<div class="b-popup b-popup-add-subscription" id="popupNewSubscription">
    <div class="b-popup-content b-popup-content-subscription">
        <h2>Добавление нового абонемента</h2>

        <form method="post">
            <p>Название</p>
            <input class="input" name="subNameAdd" type="text" value="${param.subNameAdd}"/>
            <p>Длительность (дней)</p>
            <input class="input" name="subPeriodAdd" type="number" value="${param.subPeriodAdd}"/>
            <p>Количество гостевых визитов</p>
            <input class="input" name="subGuestsVisitsAdd" type="number" value="${param.subGuestsVisitsAdd}"/>

            <div class="training-count-block">
                <p class="training-count">  Количество посещений<br>
                    <input class="input" name="subTrainingCountAdd" type="number" value="${param.subTrainingCountAdd}"/></p>
                <p class="training-count radio-training-count">
                    <label>
                        <input class="input" name="subTrainingUnlimAdd" type="checkbox" value="${param.subTrainingUnlimAdd}" />Безлим
                    </label>
                </p>
            </div>

            <p>
                <button class="button-add" name="command" value="addSubscription" >Добавить</button>
                <a href="javascript:PopUpHideNewSubscription()">Back</a>
            </p>
        </form>

    </div>
</div>
