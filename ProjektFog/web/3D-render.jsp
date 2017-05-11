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
    <input type="hidden" value='{
               "guiCarport":{"width":500,"depth":500,"height":230},
               "guiRoof":{"gableRoof":false,"overhang":{"sides":20,"front":20,"back":20}},
               "guiShed":{"shed":false,"depth":300,"doorPlacement":0,"side":"Foran","rotateDoor":false}}' id="json" name="json">

    <script src="https://ajax.googleapis.com/ajax/libs/threejs/r84/three.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/dat-gui/0.6.4/dat.gui.min.js"></script>
    <script src="js/libs/OrbitControls.js"></script>

    <script src="js/gui.js"></script>
    <script src="js/3D/canvas.js"></script>


    <script src="js/3D/Loader.js"></script>
    <script src="js/3D/MeshMaker.js"></script>
    <script src="js/CarportObjectCalc.js"></script>
    <script src="js/3DMain.js"></script>
</body>


</html>