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
                        <h1>Alle Ordrer</h1>

                        <table class="table table-bordered table-hover sortable">
                            <thead>
                                <tr>
                                    <th>Ordre no.</th>
                                    <th>Kunde</th>
                                    <th>Stykliste</th>
                                    <th>Tegning</th>
                                    <th>3D</th>
                                </tr></thead>
                            <tbody> <!-- c:out her til data fra db -->
                                <c:forEach items="${ordersPending}" var="a">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <form> <!-- Testing form -->
                                                <input type="hidden" value="">
                                                <input type="button" value="2D tegning" class="btn btn-info"> 
                                            </form>
                                        <td><a href="#" class="btn btn-info">3D Render</a></td>
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
                <c:redirect url="adminpanel.jsp"/>
            </c:otherwise>
        </c:choose>
    </body>

</html>