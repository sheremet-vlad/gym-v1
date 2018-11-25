<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="clients" type="java.util.ArrayList" scope="request"/>

<html>
<head>
    <title>Main page</title>
</head>
<body>
    <table>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>
                    ${client.id}
                </td>
                <td>
                    ${client.name}
                </td>
                <td>
                    ${client.surname}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
