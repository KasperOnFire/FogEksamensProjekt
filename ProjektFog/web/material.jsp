<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Johannes Fog</title>
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/manage.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="js/sorttable.js"></script>
        <script src="js/custom.js"></script>
    </head>

    <body>
        <c:choose>
            <c:when test="${adminLoggedIn == true}">
                <div class="main-div">
                    <div class="main-header">
                        <img src="img/fog.png">
                        <h1>Design en Carport</h1>
                    </div>
                    <div class="main-content">
                        <h1>Material</h1>
                        <a href="adminpanel.jsp" class="btn">Tilbage til Adminpanelet</a>
                        <table class="table table-bordered table-hover sortable">
                            <thead>
                                <tr>
                                    <th>mno</th>
                                    <th>type</th>
                                    <th>price</th>
                                    <th>name</th>
                                    <th>qoh</th>
                                    <th>size</th>   
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${materials}" var="a">
                                    <tr>
                                        <td>${a.getMno()}</td>
                                        <td>${a.getType()}</td>
                                        <td>
                                            <form action="userServlet" method="post">
                                                <input type="hidden" name="updatePrice">
                                                <input type="hidden" name="mno" value="${a.getMno()}">
                                                <input type="number" value="${a.getPrice()}" name="price">
                                                <input type="submit" value="opdater">
                                            </form>
                                        </td>
                                        <td>${a.getName()}</td>
                                        <td>${a.getQoh()}</td>
                                        <td>${a.getSize()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            </c:when>
            <c:otherwise>
                <c:redirect url="adminpanel.jsp" />
            </c:otherwise>
        </c:choose>
    </body>

</html>