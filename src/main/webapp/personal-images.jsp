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
</style>
<body>

<div class="w3-container">
    <h2>Gallery</h2>
    <p>This gallery contains images of each user</p>
</div>

<div class="w3-content" style="max-width:1200px">
    <%
        String login = (String) session.getAttribute("login");
        List<Integer> imageIds = (List<Integer>) session.getAttribute("personal_count");
        for (int i = 0; i < imageIds.size(); i++) {
    %>
    <img class="mySlides" src="personal-images?id=<%=imageIds.get(i)%>&login=<%=login%>" style="width:100%">
    <% }%>

    <div class="w3-row-padding w3-section">
        <%
            for (int i = 0; i < imageIds.size(); i++) {
        %>
        <div class="w3-col s4">
            <img class="demo w3-opacity w3-hover-opacity-off" src="personal-images?id=<%=imageIds.get(i)%>&login=<%=login%>"
                 style="width:350px;height:200px"
                 onclick="currentDiv(<%=i+1%>)">
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