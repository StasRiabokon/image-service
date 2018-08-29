<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<html>
<head>
    <title>Private room</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="mystyles.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 5%; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        }

        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        textarea {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        textarea:focus {
            background-color: #ddd;
            outline: none;
        }
    </style>
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
        <img class="mySlides" src="personal-images?id=<%=imageIds.get(i)%>&login=<%=login%>"
             style="margin:auto;width:100%; max-width:600px;">
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
                    <form action="/sendMailAttachServlet" method="post" enctype="multipart/form-data">
                        <span class="close">&times;</span><br>
                        <input type="hidden" name="login" value="<%=login%>"/>
                        <label for="email">Recipient address: </label>
                        <input type="text" id="email" name="recipient" placeholder="Email" required>
                        <label for="subject">Subject: </label>
                        <input type="text" id="subject" name="subject" placeholder="Subject">
                        <label for="content">Content: </label><br>
                        <textarea id="content" class="text-area" name="content"></textarea><br>
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
                     src="personal-images?id=<%=imageIds.get(i)%>&login=<%=login%>"
                     style="width:350px;height:200px"
                     onclick="currentDiv(<%=i+1%>)">
                <a class="btn" href="personal-images?id=<%=imageIds.get(i)%>&login=<%=login%>"
                   download="image<%=imageIds.get(i)%>">Download</a>
                <a class="del" href="delete-image?id=<%=imageIds.get(i)%>&login=<%=login%>">Delete</a>

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

<script src="myscript.js"></script>
<script>
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

</body>
</html>