<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Upload to Database</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<% String login = (String) session.getAttribute("login");
%>
<div class="container">
    <h1>Welcome, <%=login%>
    </h1>
    <form method="post" action="/images">
        <input type="submit" value="Go to gallery">
    </form>
    <form method="post" action="<%= "/personal?login="+login%>">
        <input type="submit" value="Go to personal gallery">
    </form>
    <form method="post" action="<%= "/upload?login="+login%>" enctype="multipart/form-data">
        <label>Upload image:</label>
        <input type="file" name="photo" size="50" required/>
        <input type="submit" value="Save">
    </form>
    <a href="/login.jsp">Log out</a>
    <%
        Boolean deleted = (Boolean) session.getAttribute("deleted");
        Boolean added = (Boolean) session.getAttribute("added");
        if (deleted != null && deleted) {
    %>
    <script>
        alert("Image was deleted");
    </script>

    <%
        }
        if (added != null && added) {%>
    <script>
        alert("Image was added");
    </script>
    <% }
        session.setAttribute("deleted", false);
        session.setAttribute("added", false);
    %>
</div>

</body>