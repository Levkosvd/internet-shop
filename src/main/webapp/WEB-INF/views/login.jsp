
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Hello login!
${errorMessage}
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="container">
        <h1>Sign in</h1>
        <p>Please fill in this form to sign in account.</p>
        <hr>
        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter login" name="login" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Don't have an account? <a href="${pageContext.request.contextPath}/registration">Sign up</a>.</p>
    </div>
</form>
</body>
</html>
