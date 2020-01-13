<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="bucketItems" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<h1>Bucket</h1>
<table border="3" bgcolor="silver">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>DELETE FROM BUCKET</th>
    </tr>
    <c:forEach var = "item" items="${bucketItems}">
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
                <a href="${pageContext.request.contextPath}/deleteItemFromBucket?itemId=${item.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<button>
    <a href="/internet_shop_war_exploded/checkoutOrder">Checkout </a>
</button>
<br>
<a href="/internet_shop_war_exploded/index">Back to Main Menu</a>
</body>
</html>
