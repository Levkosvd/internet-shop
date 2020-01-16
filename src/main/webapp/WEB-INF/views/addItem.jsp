<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddItemInLIst</title>
</head>
<body>
<form action="/internet_shop_war_exploded/addItem" method="post">
    <div class="container">
        <h1>Add Item in List</h1>
        <p>Please fill in this form to add an item.</p>
        <hr>
        <label for="name"><b>Name</b></label>
        <input type="text" placeholder="Enter name of item" name="name" required>
        <hr>
        <label for="price"><b>Price</b></label>
        <input type="text" placeholder="Enter price" name="price" required>
        <hr>
        <button type="submit" class="addItemBtn">Add Item</button>
    </div>
</form>
<br>
<a href="/internet_shop_war_exploded/servlet/index">Back to Main Menu </a>
</body>
</html>
