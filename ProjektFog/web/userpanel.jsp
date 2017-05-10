<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Johannes Fog</title>
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/index.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">FogCarport</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="userpanel.jsp">Bruger Panel</a></li>
                    <li><a href="carport.jsp">Design din carport</a></li>
                    <li><a href="#">???</a></li>
                    <li><a href="logout">Logout</a></li>
                </ul>
            </div>
        </nav>
        
        <p>Hej ${username}</p>
        <p>Din gemte carport er herunder, vil du kontaktes af fog?</p>

        <table>
            <tr>
                <td>        
                    <form> <!-- Testing form -->
                        <input type="hidden" value="${carport}">
                        <input type="button" value="2D tegning" class="btn btn-info"> 
                    </form>
                </td>

                <td>
                    <form> <!-- Testing form -->
                        <input type="hidden" value="${carport}">
                        <input type="button" value="3D tegning" class="btn btn-info"> 
                    </form>
                </td>
                <td>
                    <form action="userpanel" method="post">
                        <input type="hidden" value="true" name="addOrder">
                        <input type="hidden" value='${carport}' name="carport">
                        <input type="submit" value="Kontakt mig" class="btn btn-info"> 
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
