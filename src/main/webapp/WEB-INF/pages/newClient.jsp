<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<div>
    <a href="javascript:PopUpShowAddNewClient()">
        <input value="Добавить нового клиента" class="addNewClient" id="addNewClient" type="submit">
    </a>
</div>

<div class="b-popup" id="popupAddNewClient">
    <div class="b-popup-content">
        <h2>Добавление нового клиента</h2>
        <form name="MyForm" method="post">
            <p>Имя</p>
            <input class="input" name="nameAdd" type="text" value="${param.nameAdd}"/>
            <p>Фамилия</p>
            <input class="input" name="surnameAdd" type="text" value="${param.surnameAdd}"/>
            <p>Отчество</p>
            <input class="input" name="middleNameAdd" type="text" value="${param.middleNameAdd}" />
            <p>Номер карты</p>
            <input class="input" name="cartAdd" type="text" value="${param.cartAdd}" />
            <p>День рождения</p>
            <input class="input" name="birthdayAdd" type="date" value="${param.birthdayAdd}"/>
            <p>Пол</p>
            <select size="1" name="genderAdd">
                <option value="manAdd">Man</option>
                <option value="womanAdd">Woman</option>
            </select>
            <p>Контактный телефон</p>
            <input class="input" name="phoneAdd" maxlength="11" type="text" value="${param.phoneAdd}"/>
            <p>Комментарии</p>
            <label>
                <textarea name="commentsAdd" type="text">${param.commentsAdd}</textarea>
            </label>
            <p>
                <button class="button-add" name="command" value="addClient" >Добавить</button>
                <a href="javascript:PopUpHideAddNewClient()">Back</a>
            </p>
        </form>

    </div>
</div>
