<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Taget:</p>
        <img src="data:image/png;base64, ${b64_Roof}" alt="Image not found" />
        <br>
        <p>Siden:</p>
        <img src="data:image/png;base64, ${b64_Side}" alt="Image not found" />
        <br>
        <p>Fronten:</p>
        <img src="data:image/png;base64, ${b64_Front}" alt="Image not found" />
        </body>
</html>
