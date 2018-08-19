<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Upload to Database Demo</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<% String login = (String) session.getAttribute("login");
%>
<h1>File Upload to Database Demo</h1>
<form method="get" action="/images" >
    <input type="submit" value="Go to gallery">
</form>
<form method="post" action="<%= "/uploadServlet?login="+login%>" enctype="multipart/form-data">
    <table border="0">
        <tr>
            <td>First Name:</td>
            <td><input type="text" name="firstName" size="50"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input type="text" name="lastName" size="50"/></td>
        </tr>
        <tr>
            <td>Portrait Photo:</td>
            <td><input type="file" name="photo" size="50"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Save">
            </td>
        </tr>
    </table>
</form>
</body>