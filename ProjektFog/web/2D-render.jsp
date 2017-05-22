<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Fog Carport - 2D render</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <style>
            body {
                margin: auto;
                padding: 0;
                width: 60%;
            }
        </style>

    </head>

    <body>
        <a href="userpanel.jsp">Tilbage til brugerpanel</a>
        <p>Pris: ${price}</p>
        <p>Top:</p>
        <div id="topView"></div>
        <p>Side:</p>
        <div id="sideView"></div>
        <p> </p>
        <!-- spacer to see bottom of sideview-->
        <input type="hidden" 
               <c:choose>
                   <c:when test="${json != null}">
                       value='${json}'
                   </c:when>
                   <c:otherwise>
                       <c:redirect url= "userpanel.jsp"/>
                   </c:otherwise>
               </c:choose>
            id="json" name="json">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/svg.js/2.6.1/svg.js"></script>
        <script src="js/2D/svgmaker.js"></script>

        <script src="js/CarportObjectCalc.js"></script>
        <script src="js/2DMain.js"></script>
    </body>

</html>