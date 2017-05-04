<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Johannes Fog</title>
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/index.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="main-div">
            <div class="main-header">
                <img src="img/fog.png">
                <h1>Design en Carport</h1>
            </div>
            <div class="main-content">
                <a href="Fog_Carport.html" class="link-button">Design din carport!</a>
                <c:choose>
                    <c:when test="${loggedIn==false}">
                        <a class="link-button" href="login.jsp">Gå til login!</a>
                        <a href="signup.jsp">har du ikke en bruger? Registrer her?</a>
                    </c:when>
                    <c:otherwise>
                        <p>Logget in som: <c:out value="${currentUser}"></c:out></p>
                    </c:otherwise>
                </c:choose>
                <div class="img-display">
                    <img src="img/fog.png"/> 
                    <img src="img/fog.png"/>
                    <img src="img/fog.png"/>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>

</html>