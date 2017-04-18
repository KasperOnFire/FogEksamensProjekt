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
        <div class="main-div">
            <div class="main-header">
                <img src="img/fog.png">
                <h1>Design en Carport</h>
            </div>
            <div class="main-contet">
                <h1>Header</h1>
                <p>Alle mål i cm</p>
                <form action="ImageServlet" class="form-group">
                    <input type="number" name="height" placeholder="Højde" step="1" min="100" required="">
                    <br>
                    <input type="number" name="depth" placeholder="Længde" step="1" min="100" required="">
                    <br>
                    <input type="number" name="width" placeholder="Bredde" step="1" min="100" required="">
                    <br>
                    <input type="submit">
                </form>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    </body>

</html>