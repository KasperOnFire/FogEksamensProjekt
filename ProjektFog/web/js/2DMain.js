//just for test
var datGui = new DatGui(); //uses dat.gui

var jsonObject;

var svgmaker = new SvgMaker();

objectCalc = new CarportObjectCalc(svgmaker);

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