<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
        <link rel="stylesheet" href="css/main.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="main-div">
            <div class="main-header">
                <img src="img/fog.png">
                <h1>Design en Carport</h1>
            </div>
            <div class="main-content">
                <c:choose>
                    <c:when test="${adminLoggedIn == true}">
                        <c:redirect url="index.jsp"/>
                    </c:when>
                    <c:when test="${loggedIn == true}">
                        <c:redirect url="index.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <p>${errorCode}</p>
                        <form action="login">
                            <p>username:</p>
                            <input type="text" name="username" placeholder="Username">
                            <p>password:</p>
                            <input type="password" name="password" placeholder="Password">
                            <br>
                            <br>
                            <input type="submit">
                        </form>
                        <a href="logout">logout</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
