<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="orderList" scope="request" type="java.util.List<internetshop.model.Order>"/>
<html>
<head>
    <title>AllOrdersOfUser</title>
</head>
<body>
<h1>All orders of User</h1>
<table border="3" bgcolor="silver">
    <tr>
        <th>Order ID</th>
        <th>Items</th>
        <th>DELETE ORDER</th>
    </tr>
    <c:forEach var = "order" items="${orderList}">
        <tr>
            <td>
                <c:out value="${order.id}" />
            </td>
            <td>
                <c:out value="${order.items}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteOrders?id=${order.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="${pageContext.request.contextPath}/servlet/index">Back to Main Menu </a>
</body>
</html>
