<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
    .mySlides {
        display: none
    }

    .demo {
        cursor: pointer
    }

    .download {
        position: relative;
        width: 100%;
        max-width: 400px;
    }

    .download img {
        width: 100%;
        height: auto;
    }

    .download .btn {
        text-decoration: none;
        position: absolute;
        top: 12%;
        left:15%;
        transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        background-color: #555;
        color: white;
        font-size: 16px;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        text-align: center;
    }

    .download .btn:hover {
        background-color: black;
    }
</style>
<body>

<div class="w3-container">
    <h2>Gallery</h2>
    <p>This gallery contains images of each user</p>
</div>

<div class="w3-content" style="max-width:1200px">
    <%
        List<Integer> imageIds = (List<Integer>) session.getAttribute("count");
        for (int i = 0; i < imageIds.size(); i++) {
    %>
    <img class="mySlides" src="images?id=<%=imageIds.get(i)%>" style="width:100%">
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


<script>
    var slideIndex = 1;
    showDivs(slideIndex);

    function plusDivs(n) {
        showDivs(slideIndex += n);
    }

    function currentDiv(n) {
        showDivs(slideIndex = n);
    }

    function showDivs(n) {
        var i;
        var x = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("demo");
        if (n > x.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = x.length
        }
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
        }
        x[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " w3-opacity-off";
    }
</script>

</body>
</html>

<%--<html>--%>
<%--<head>--%>
<%--<title></title>--%>
<%--<style>--%>
<%--div.gallery {--%>
<%--margin: 5px;--%>
<%--border: 1px solid #ccc;--%>
<%--float: left;--%>
<%--width: 180px;--%>
<%--}--%>

<%--div.gallery:hover {--%>
<%--border: 1px solid #777;--%>
<%--}--%>

<%--div.gallery img {--%>
<%--width: 100%;--%>
<%--height: auto;--%>
<%--}--%>

<%--div.desc {--%>
<%--padding: 15px;--%>
<%--text-align: center;--%>
<%--}--%>
<%--</style>--%>
<%--</head>--%>
<%--<body bgcolor="white">--%>
<%--<%--%>
<%--List<Integer> imageIds = (List<Integer>) session.getAttribute("count");--%>
<%--for (int i = 0; i < imageIds.size(); i++) {%>--%>
<%--<div class="gallery">--%>
<%--<a target="_blank" href="images?id=<%=imageIds.get(i)%>">--%>
<%--<img src="images?id=<%=imageIds.get(i)%>" width="600" height="400">--%>
<%--</a>--%>
<%--<div class="desc">Add a description of the image here</div>--%>
<%--</div>--%>
<%--<%--%>
<%--}--%>

<%--%>--%>


<%--</body>--%>
<%--</html>--%>