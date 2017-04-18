<%-- 
    Document   : signup
    Created on : Apr 18, 2017, 11:20:05 AM
    Author     : T430
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="createuser">
            <p>username:</p>
            <input type="text" name="username" placeholder="Username">
            <p>password:</p>
            <input type="password" name="password" placeholder="Password">
            <p>email</p>
            <input type="mail" name="email" placeholder="email">
            <br>
            <br>
            <input type="submit">
        </form>
    </body>
</html>
