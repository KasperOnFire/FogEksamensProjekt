<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Johannes Fog</title>
        <link rel="stylesheet" href="css/fonts.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
            crossorigin="anonymous">
    </head>

    <body>
        <div class="main-div">
            <div class="main-header">
                <img class="fog-icon" src="img/fog.png">
                <h2>Design en Carport</h2>
            </div>
            <h1>Header</h1>
            <p>Alle mål i cm</p>
            <form action="info">
                <input type="number" name="height" placeholder="Højde" step="1" min="100" required="">
                <br>
                <input type="number" name="length" placeholder="længde" step="1" min="100" required="">
                <br>
                <input type="submit">
            </form>

            <form action="imageservlet">
                <input type="submit">
            </form>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    </body>

    </html>