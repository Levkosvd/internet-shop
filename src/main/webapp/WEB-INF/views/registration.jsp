<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/internet_shop_war_exploded/registration" method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter login" name="login" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <label for="password_repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>

        <label for="user_first_name"><b>Name</b></label>
        <input type="text" placeholder="Enter first name" name="user_first_name" required>

        <label for="user_surname"><b>Surname</b></label>
        <input type="text" placeholder="Enter surname" name="user_surname" required>

        <label for="balance"><b>Balance -$-</b></label>
        <input type="text" placeholder="Enter balance" name="balance" required>
        <hr>
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="#">Sign in</a>.</p>
    </div>
</form>
<br>
<a href="/internet_shop_war_exploded/servlet/index">Back to Main Menu </a>
</body>
</html>
