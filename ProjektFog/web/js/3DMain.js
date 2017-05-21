var canvas = new THREECanvas(); //uses three.js
var datGui = new DatGui(); //uses dat.gui

var loader = new Loader(canvas); //uses three.js
var objectMaker = new MeshMaker(loader); //uses three.js

var objectCalc = new CarportObjectCalc(objectMaker);

function update() {
    objectCalc.calcCarport(datGui.getObjects());
}

function sendJson() {
    var jsonData = JSON.stringify(datGui.getObjects());
    var http = new XMLHttpRequest();
    var url = 'DataReciever';
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    http.send('json=' + encodeURIComponent(jsonData));
    //redirect to 2D
    setTimeout(function() { window.location.href = 'Get2D'; }, 1000);
}

function getJson() {
    var json = document.getElementById("json").value;
    if (json.length > 0) {
        datGui.setObjects(JSON.parse(json));
    }
}

getJson();
update();