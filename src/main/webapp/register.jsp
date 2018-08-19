<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
    <script>
        var password = document.getElementById("password")
            , confirm_password = document.getElementById("confirm_password");

        function validatePassword(){
            if(password.value != confirm_password.value) {
                confirm_password.setCustomValidity("Passwords Don't Match");
            } else {
                confirm_password.setCustomValidity('');
            }
        }

        password.onchange = validatePassword;
        confirm_password.onkeyup = validatePassword;
    </script>
</head>
<body>
<div class="container" style="width: 30%;">
    <form action="/register" method="POST">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" placeholder="login" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="password" required>

        <label for="confirm_password">Confirm password:</label>
        <input type="password" id="confirm_password" name="confirm_password" placeholder="confirm password" required>

        <input type="submit"/>
        <a href="/login.jsp">Log in</a>
    </form>
</div>
</body>
</html>