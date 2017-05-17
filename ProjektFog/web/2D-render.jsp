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
    <p>top:</p>
    <div id="topView"></div>
    <p>side:</p>
    <div id="sideView"></div>
    <p> </p>
    <!-- spacer to see bottom of sideview-->
    <input type="hidden" value='{
               "guiCarport":{"width":500,"depth":500,"height":230},
               "guiRoof":{"gableRoof":false,"overhang":{"sides":20,"front":20,"back":20}},
               "guiShed":{"shed":false,"depth":300,"doorPlacement":0,"side":"Foran","rotateDoor":false}}' id="json" name="json">


    <script src="https://cdnjs.cloudflare.com/ajax/libs/dat-gui/0.6.4/dat.gui.min.js"></script>
    <script src="js/gui.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/svg.js/2.6.1/svg.js"></script>
    <script src="js/2D/svgmaker.js"></script>

    <script src="js/CarportObjectCalc.js"></script>
    <script src="js/2DMain.js"></script>
</body>

</html>