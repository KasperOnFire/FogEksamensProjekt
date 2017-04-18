<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>${errorCode}</p>
        <form action="login">
            <p>username:</p>
            <input type="text" name="username" placeholder="Username">
            <p>password:</p>
            <input type="password" name="password" placeholder="Password">
            <br>
            <br>
            <input type="submit">
        </form>
        <a href="logout">logout</a>
    </body>
</html>
