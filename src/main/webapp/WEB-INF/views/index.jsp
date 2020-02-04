<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
MAIN MENU
<hr>
<button>
    <a href="${pageContext.request.contextPath}/registration">Registration </a>
</button>
<button>
    <a href="${pageContext.request.contextPath}/servlet/getAllUsers">Show all Users</a>
</button>
<button>
    <a href="${pageContext.request.contextPath}/servlet/getAllItems">Show all Items </a>
</button>
<button>
    <a href="${pageContext.request.contextPath}/servlet/bucket">Show current Bucket </a>
</button>
<button>
    <a href="${pageContext.request.contextPath}/servlet/addItem">Add Item </a>
</button>
<button>
    <a href="${pageContext.request.contextPath}/servlet/getAllOrders">Show all orders of User</a>
</button>
<button>
    <a href="${pageContext.request.contextPath}/servlet/logout">Logout</a>
</button>
</body>
</html>
