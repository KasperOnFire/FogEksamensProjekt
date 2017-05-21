var svgmaker = new SvgMaker();
var objectCalc = new CarportObjectCalc(svgmaker);

var jsonObject;

function update() {
    objectCalc.calcCarport(jsonObject);
}

function getJson() {
    var json = document.getElementById("json").value;
    if (json.length > 0) {
        jsonObject = JSON.parse(json);
    }
}

getJson();
update();