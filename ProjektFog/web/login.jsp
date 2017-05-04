<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
    </body>
</html>
