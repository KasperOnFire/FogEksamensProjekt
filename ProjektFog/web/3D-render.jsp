<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Fog Carport - webgl</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
        <style>
            body {
                margin: 0px;
                background-color: #000000;
                overflow: hidden;
            }
        </style>
    </head>

    <body>
        <input type="hidden" 
               <c:choose>
                   <c:when test="${json != null}">
                       value='${json}'
                   </c:when>
                   <c:otherwise>
                       value=''
                   </c:otherwise>
               </c:choose>
               id="json" name="json">

        <script src="https://ajax.googleapis.com/ajax/libs/threejs/r84/three.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/dat-gui/0.6.4/dat.gui.min.js"></script>
        <script src="js/libs/OrbitControls.js"></script>

        <script src="js/gui.js"></script>
        <script src="js/3D/canvas.js"></script>

        <script src="js/3D/MeshMaker.js"></script>
        <script src="js/CarportObjectCalc.js"></script>
        <script src="js/3DMain.js"></script>
    </body>


</html>