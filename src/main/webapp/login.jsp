<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container" style="width: 30%;">
    <form action="/login" method="POST">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" placeholder="login" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="password" required>
        <input type="submit"/>
        <a href="/register.jsp">Register</a>
    </form>
</div>
</body>
</html>