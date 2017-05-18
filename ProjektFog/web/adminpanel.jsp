<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="css/main.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="main-div">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Johannes Fog</a>
                    </div>
                </div>
            </nav>
            <div class="main-header">
                <img src="img/fog.png">
                <h1>Design en Carport</h1>
            </div>
            <div class="main-content">
                <c:choose>
                    <c:when test="${adminLoggedIn == true}">
                        Welcome ${username}
                        <a href="manage.jsp">pending orders (${ordersPending.size()})</a>
                        <form action="userServlet">
                            <input type="hidden" value="allOrders" name="retrieveOrders">
                            <input type="submit" value="Alle ordrer">
                        </form>
                        <form action="userServlet">
                            <input type="hidden" value="claimedOrders" name="retrieveOrders">
                            <input type="submit" value="Mine ordrer">
                        </form>
                        <form action="userServlet">
                            <input type="hidden" value="notClaimed" name="retrieveOrders">
                            <input type="submit" value="Ikke claimed ordrer">
                        </form>
                        <form action="userServlet">
                            <input type="hidden" value="finishedOrders" name="retrieveOrders">
                            <input type="submit" value="Færdige ordrer">
                        </form>
                        <form action="userServlet">
                            <input type="number" name="orderNumber">
                            <input type="hidden" value="searchOrder" name="retrieveOrders">
                            <input type="submit" value="Søg på ordrer">
                        </form>
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
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
