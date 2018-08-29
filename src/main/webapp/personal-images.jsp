<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Private room</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<div class="w3-container">
    <h2>Gallery</h2>
    <p>This gallery contains images of each user</p>
    <a class="back" href="/user-room.jsp">Back</a>
</div>
<% String login = (String) session.getAttribute("login");
    List<Integer> imageIds = (List<Integer>) session.getAttribute("personal_count");
    if (imageIds.size() != 0) {
%>
<div class="w3-content" style="max-width:1200px;">
    <div class="inline">
        <%
            for (int i = 0; i < imageIds.size(); i++) {
        %>
        <img class="mySlides" src="personal?id=<%=imageIds.get(i)%>&login=<%=login%>"
             style="margin:0px auto 25px;width:100%; max-width:600px;">
        <% }%>
    </div>
    <div class="inline">
        <form action="<c:url value="/download-zip" />" method="post">
            <input type="hidden" name="login" value="<%=login%>"/>
            <input class="zip" type="submit" name="bDownload" value="Download Zip file"/>
        </form>

        <input type="submit" class="zip" name="bDownload" value="Send zip to email" id="myBtn"/>

        <div id="myModal" class="modal">
            <div class="modal-content">
                <div class="container">
                    <form action="/send" method="post" enctype="multipart/form-data">
                        <span class="close">&times;</span><br>
                        <input type="hidden" name="login" value="<%=login%>"/>
                        <label for="email">Recipient address: </label>
                        <input type="text" id="email" name="recipient" placeholder="Email" required>
                        <label for="subject">Subject: </label>
                        <input type="text" id="subject" name="subject" placeholder="Subject">
                        <label for="content">Content: </label><br>
                        <textarea id="content" class="text-area" name="content"></textarea><br><br>
                        <label>File: data.zip</label>
                        <input type="submit" value="Send"/>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <div class="w3-row-padding w3-section">
        <%
            for (int i = 0; i < imageIds.size(); i++) {
        %>
        <div class="w3-col s4">
            <div class="download">
                <img class="demo w3-opacity w3-hover-opacity-off"
                     src="personal?id=<%=imageIds.get(i)%>&login=<%=login%>"
                     style="width:350px;height:200px"
                     onclick="currentDiv(<%=i+1%>)">
                <a class="btn" href="personal?id=<%=imageIds.get(i)%>&login=<%=login%>"
                   download="image<%=imageIds.get(i)%>">Download</a>
                <a class="del" href="delete?id=<%=imageIds.get(i)%>&login=<%=login%>">Delete</a>

            </div>
        </div>
        <% }%>
    </div>
</div>
<%
} else {%>
<h2>You don't have any images</h2>
<% }
%>

<script src="js/scripts.js"></script>
</body>
</html>