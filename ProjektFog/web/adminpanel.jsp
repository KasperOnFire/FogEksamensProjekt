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
                Welcome ${username}
                <a href="manage.jsp">pending orders (${ordersPending.size()})</a>
                <br>
                <a href=""></a>
                <a href="logout">logout</a>
            </c:when>
            <c:when test="${loggedIn == true}">
                <p>User and admin can't be logged in at the same time!</p>
                <a href="logout">logout</a>
            </c:when>
            <c:when test="${AdminloggedIn == null or adminLoggedIn == false}">
                <form action="login">
                    <input type="hidden" name="adminLogin" value="true">
                    <p>username:</p>
                    <input type="text" name="username" placeholder="Username">
                    <p>password:</p>
                    <input type="password" name="password" placeholder="Password">
                    <br>
                    <br>
                    <input type="submit">
                </form>
            </c:when>
        </c:choose>
    </body>
</html>
