<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="itemList" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<html>
<head>
    <title>All Items</title>
</head>
<body>
<h1>List all Item</h1>
<table border="3" bgcolor="silver">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>ADD TO BUCKET</th>
    </tr>
    <c:forEach var = "item" items="${itemList}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/addItemToBucket?itemId=${item.id}">ADD</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/internet_shop_war_exploded/addItem">Add Item to List</a>
<br>
<a href="/internet_shop_war_exploded/index">Back to Main Menu</a>
</body>
</html>
