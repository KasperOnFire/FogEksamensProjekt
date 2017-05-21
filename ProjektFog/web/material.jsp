<%-- 
    Document   : material
    Created on : May 19, 2017, 8:56:19 AM
    Author     : T430
--%>

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
        <h1>Material</h1>
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
                            <form action="userServlet">
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
    </body>
</html>
