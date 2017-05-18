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
                                    <th>Kunde no.</th>
                                    <th>Status</th>
                                    <th>Tegning</th>
                                    <th>3D</th>
                                    <th>Medarbejder</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- c:out her til data fra db -->
                                <c:forEach items="${ordersPending}" var="a">
                                    <tr>
                                        <td>${a.getOno()}</td>
                                        <td>${a.getUid()}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${a.getEMPNo() == empNo}">
                                                    <form action="userServlet">
                                                        <input type="hidden" name="updateStatus" value="true">
                                                        <input type="hidden" name="ono" value="${a.getOno()}">
                                                        <c:choose>
                                                            <c:when test="${a.getOstatus() == 0}">
                                                                <select name="status">
                                                                    <option value="0" selected>Ikke behandlet</option>
                                                                    <option value="1">Under behandling</option>
                                                                    <option value="2">Færdig behandlet</option>
                                                                </select>
                                                            </c:when>
                                                            <c:when test="${a.getOstatus() == 1}">
                                                                <select name="status">
                                                                    <option value="0">Ikke behandlet</option>
                                                                    <option value="1" selected>Under behandling</option>
                                                                    <option value="2">Færdig behandlet</option>
                                                                </select>
                                                            </c:when>
                                                            <c:when test="${a.getOstatus() == 2}">
                                                                <select name="status">
                                                                    <option value="0">Ikke behandlet</option>
                                                                    <option value="1">Under behandling</option>
                                                                    <option value="2" selected>Færdig behandlet</option>
                                                                </select>
                                                            </c:when>
                                                        </c:choose>
                                                        <input type="submit" value="Opdater" class="btn btn-info">
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${a.getOstatus() == 0}">
                                                            <p>Ikke behandlet</p>
                                                        </c:when>
                                                        <c:when test="${a.getOstatus() == 1}">
                                                            <p>Under behandling</p>
                                                        </c:when>
                                                        <c:when test="${a.getOstatus() == 2}">
                                                            <p>Færdig behandlet</p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p>Error #1337</p>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <form action="Get2D">
                                                <!-- Testing form -->
                                                <input type="hidden" name="json" value='${a.getCarport()}'>
                                                <input type="submit" value="2D tegning" class="btn btn-info">
                                            </form>
                                        </td>
                                        <td>
                                            <form action="Get3D">
                                                <!-- Testing form -->
                                                <input type="hidden" name="json" value='${a.getCarport()}'>
                                                <input type="submit" value="3D tegning" class="btn btn-info">
                                            </form>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${a.getEMPNo() == -1}">
                                                    <form action="userServlet">
                                                        <input type="hidden" name="claimOrder" value="${username}">
                                                        <input type="hidden" name="claimOno" value="${a.getOno()}">
                                                        <input type="submit" value="Claim" class="btn btn-info">
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <p>${a.getEMPNo()}</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
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