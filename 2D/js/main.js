//just for test
datGui = new DatGui(); //uses dat.gui

var svgmaker = new SvgMaker();

objectCalc = new CarportObjectCalc(svgmaker);

function update() {
    objectCalc.calcCarport(datGui.getObjects());
}

update();

function sendJson() {
    var jsonData = JSON.stringify(datGui.getObjects());
    console.log(jsonData);
    var http = new XMLHttpRequest();
    var url = 'DataReciever';
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    http.send('json=' + encodeURIComponent(jsonData));
}
/*
function update() {
    var obj = datGui.getObjects();
    svgmaker.paint(obj.guiCarport.width / 100 + 1,
        obj.guiCarport.height / 100 + 0.5,
        obj.guiCarport.depth / 100 + obj.guiShed.depth / 100 + 1
    );
    
    svgmaker.makeGeometry(
        {
            x: 1,
            y: 1,
            z: 1
        },
        svgmaker.wood,
        {
            x: 0,
            y: 0,
            z: 0
        }
    );

    
    var geometry = svgmaker.PrismGeometry([
        new Vector2(-1, -1), //left
        new Vector2(-1, 1), //left  
        new Vector2(1, 1), //rigth  
        new Vector2(1, -1)], //rigth  
        4,
        'side',
        svgmaker.wood,
        new Position(0, 0, 0)
    );
    
}




/*
svgmaker.makeGeometry(
    {
        x: 1,
        y: 1,
        z: 1
    },
    svgmaker.wood,
    {
        x: 0,
        y: 0,
        z: 0
    }
);
*/