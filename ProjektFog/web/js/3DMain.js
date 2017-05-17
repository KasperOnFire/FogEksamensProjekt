canvas = new THREECanvas(); //uses three.js
datGui = new DatGui(); //uses dat.gui

loader = new Loader(canvas); //uses three.js
objectMaker = new MeshMaker(loader); //uses three.js

objectCalc = new CarportObjectCalc(objectMaker);

function update() {
    objectCalc.calcCarport(datGui.getObjects());
}

function sendJson() {
    var jsonData = JSON.stringify(datGui.getObjects());
    console.log(jsonData);
    var http = new XMLHttpRequest();
    var url = 'DataReciever';
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    http.send('json=' + encodeURIComponent(jsonData));
}

function getJson() {
    var json = document.getElementById("json").value;
    if (json.length > 0) {
        datGui.setObjects(JSON.parse(json));
    }
}

getJson();
update();