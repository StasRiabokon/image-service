<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<title>Gallery</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-container">
    <h2>Gallery</h2>
    <p>This gallery contains images of each user</p>
    <a class="back"  href="/user-room.jsp">Back</a>

</div>

<div class="w3-content" style="max-width:1200px">
    <%
        List<Integer> imageIds = (List<Integer>) session.getAttribute("count");
        for (int i = 0; i < imageIds.size(); i++) {
    %>
    <img class="mySlides" src="images?id=<%=imageIds.get(i)%>" style="margin:50px auto;width:100%; max-width:600px;">
    <% }%>

    <div class="w3-row-padding w3-section">
        <%
            for (int i = 0; i < imageIds.size(); i++) {
        %>

        <div class="w3-col s4">
            <div class="download">
                <img class="demo w3-opacity w3-hover-opacity-off" src="images?id=<%=imageIds.get(i)%>"
                     style="width:350px;height:200px"
                     onclick="currentDiv(<%=i+1%>)">
                <a class="btn" href="images?id=<%=imageIds.get(i)%>" download="image<%=imageIds.get(i)%>">Download</a>

            </div>
        </div>

        <% }%>
    </div>
</div>


<script src="js/scripts.js"></script>

</body>
</html>
