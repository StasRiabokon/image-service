<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container" style="width: 30%;">
    <form name="form" action="/register" method="POST" onsubmit="return checkForm();">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" placeholder="login" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="password" required>

        <label for="confirm_password">Confirm password:</label>
        <input type="password" id="confirm_password" name="password2" placeholder="confirm password" required>

        <input type="submit"/>

        <a href="/login.jsp">Log in</a>
    </form>
</div>

<script src="js/scripts.js"></script>

<%
    Boolean exists = (Boolean) session.getAttribute("exists");
    if (exists!=null && exists) {
%>
<script>
    alert("User already exists");
</script>

<%
    }
    session.setAttribute("exists", false);
%>
</body>
</html>