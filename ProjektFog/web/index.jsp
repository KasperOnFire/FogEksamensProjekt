<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>Alle mål i cm</p>
        <form action="info">
            <input type="number" name="height" placeholder="Højde" step="0.5" min="100">
            <br>
            <br>
            <input type="number" name="length" placeholder="længde" step="0.5" min="100">
            <br>
            <br>
            <input type="submit">
        </form>
    </body>
</html>
