<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<html>
<head>
    <title></title>
    <style>
        div.gallery {
            margin: 5px;
            border: 1px solid #ccc;
            float: left;
            width: 180px;
        }

        div.gallery:hover {
            border: 1px solid #777;
        }

        div.gallery img {
            width: 100%;
            height: auto;
        }

        div.desc {
            padding: 15px;
            text-align: center;
        }
    </style>
</head>
<body bgcolor="white">
<%
    List<Integer> imageIds = (List<Integer>) session.getAttribute("count");
    for (int i = 0; i < imageIds.size(); i++) {%>
        <div class="gallery">
            <a target="_blank" href="images?id=<%=imageIds.get(i)%>">
                <img src="images?id=<%=imageIds.get(i)%>" width="600" height="400">
            </a>
            <div class="desc">Add a description of the image here</div>
        </div>
<%
    }

%>


</body>
</html>