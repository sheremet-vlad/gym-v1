<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="clients" type="java.util.ArrayList" scope="request"/>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Главная страница</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/mainPage.css">
        <script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
        <script>
            //----------------------------------------
            $(document).ready(function(){
                //Скрыть PopUp при загрузке страницы
                PopUpHideStatistic();
            });
            //Функция отображения PopUp
            function PopUpShowStatistic(){
                $("#popupStatistic").show();
            }
            //Функция скрытия PopUp
            function PopUpHideStatistic(){
                $("#popupStatistic").hide();
            }
            //-------------------------------------------
            $(document).ready(function(){
                //Скрыть PopUp при загрузке страницы
                PopUpHideEditAbon();
            });
            //Функция отображения PopUp
            function PopUpShowEditAbon(){
                $("#popupEditAbon").show();
            }
            //Функция скрытия PopUp
            function PopUpHideEditAbon(){
                $("#popupEditAbon").hide();
            }
            /*--------------------------------------*/
            $(document).ready(function(){
                //Скрыть PopUp при загрузке страницы
                PopUpHideNewAbon();
            });
            //Функция отображения PopUp
            function PopUpShowNewAbon(){
                $("#popupNewAbon").show();
            }
            //Функция скрытия PopUp
            function PopUpHideNewAbon(){
                $("#popupNewAbon").hide();
            }
            //----------------------------------------------
            $(document).ready(function(){
                //Скрыть PopUp при загрузке страницы
                PopUpHideaddNewClient();
            });
            //Функция отображения PopUp
            function PopUpShowaddNewClient(){
                $("#popupaddNewClient").show();
            }
            //Функция скрытия PopUp
            function PopUpHideaddNewClient(){
                $("#popupaddNewClient").hide();
            }
            //------------------------------------------------
        </script>

    </head>
    <body>
        <form class="form-wrapper">
            <input name="surnameOrCardNumber" id="search" placeholder="Введите фамилию или номер карты" required="" type="text">
            <button name="command" value="buttonFind" id="submit">Найти</button>
        </form>

        <div>
            <a href="javascript:PopUpShowaddNewClient()">
                <input value="Добавить нового клиента" class="addNewClient" id="addNewClient" type="submit">
            </a>
        </div>
        <div class="b-popup-Statistic" id="popupaddNewClient">
            <div class="b-popup-content">
                <h2>Добавление нового клиента</h2>
                <form name="MyForm" >
                    <p>Имя</p>
                    <input class="input" name="name" type="text"  />
                    <p>Фамилия</p>
                    <input class="input" name="surname" type="text" />
                    <p>Отчество</p>
                    <input class="input" name="middlename" type="text"  />
                    <p>День рождения</p>
                    <input class="input" name="birthday" type="text" />
                    <p>Пол</p>
                    <input class="input" name="gender" type="text"  />
                    <p>Контактный телефон</p>
                    <input class="input" name="phone" type="text" />
                    <p>Комментарии</p>
                    <textarea name="comments" type="text">

                    </textarea>
                    <p>
                        <input id="create" value="Добавить" type="submit" />
                        <a href="javascript:PopUpHideaddNewClient()">Back</a>
                    </p>
                </form>

            </div>
        </div>
        <div class="table-wrapper">
            <table>
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
        <div class="footer-container">
            <div class="birthdayToDay">Сегодня день рождения</div>
            <div class="containerNowInGym">
                <div style="font: normal 30px Arial, Helvetica;">Сейчас в зале: </div>
                <div style="height: 35px; width: 40px; border: 1px solid black; float: right"></div>
            </div>
            <div class="footer-table">
                <table>
                    <thead>
                    <tr>
                        <th>ФИО</th>
                        <th>№ Карты</th>
                        <th>Телефон</th>
                    </tr>
                    </thead>
                    <c:forEach var="client" items="${clients}">
                        <tbody>
                        <tr>
                            <td>${client.fio}</td>
                            <td>${client.cardNumber}</td>
                            <td>${client.phoneNumber}</td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div>

            <div class="b-container-Statistic">
                <a href="javascript:PopUpShowStatistic()">
                    <input type="submit" name="command" value="Statistic" class="btnFooter"/>
                </a>
            </div>
            <div class="b-popup-Statistic" id="popupStatistic">
                <div class="b-popup-content">
                    Text in Popup
                    <a href="javascript:PopUpHideStatistic()">Back</a>
                </div>
            </div>

            <div class="b-container-Statistic">
                <a href="javascript:PopUpShowEditAbon()">
                    <button class="btnFooter" id="newAbonement">Новый абонемент</button>
                </a>
            </div>
            <div class="b-popup-Statistic" id="popupNewAbon">
                <div class="b-popup-content">
                    Text in Popup
                    <a href="javascript:PopUpHideEditAbon()">Back</a>
                </div>
            </div>

            <div>
                <a href="javascript:PopUpShowEditAbon()">
                    <button class="btnFooter" id="editAbonement">Редактировать абонемент</button>
                </a>
            </div>
            <div class="b-popup-Statistic" id="popupEditAbon">
                <div class="b-popup-content">
                    Text in Popup
                    <a href="javascript:PopUpHideEditAbon()">Back</a>
                </div>
            </div>



        </div>
    </body>
</html>
