<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="usersList" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<h1>Hello, ${greeting}!</h1>
<hr>
<h1>All users page</h1>
<table border="3" bgcolor="silver">
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Balance</th>
        <th>DELETE USER</th>
    </tr>
    <c:forEach var = "user" items="${usersList}">
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
            <td>
                <c:out value="${user.firstName}" />
            </td>
            <td>
                <c:out value="${user.surname}" />
            </td>
            <td>
                <c:out value="${user.accountBalance}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/deleteUser?id=${user.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/internet_shop_war_exploded/registration">Register new User </a>
<br>
<a href="/internet_shop_war_exploded/index">Back to Main Menu </a>
</body>
</html>
