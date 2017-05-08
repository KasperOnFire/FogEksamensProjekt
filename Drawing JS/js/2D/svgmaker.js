function SvgMaker() {

    var _width;
    var _height;
    var _depth;

    var drawTop, drawSide;

    drawTop = new SVG('topView');
    drawSide = new SVG('sideView');

    this.wood = {
        color: 'white'
    }
    this.plastic = {
        color: 'white'
    }
    function makeCanvas(width, height, depth) {
        drawTop.size(depth, width);
        drawSide.size(depth, height);
        _width = width;
        _height = height;
        _depth = depth;
    }

    this.paint = function (width, height, depth) {
        makeCanvas(width * 100, height * 100, depth * 100);
        drawTop.clear();
        drawSide.clear();

        //temp
        //midt  (0,0)
        /*
        var midt = drawTop.ellipse(6, 6)
            .fill('pink')
            .stroke({ width: 1 })
            .move((_depth - 6) / 2, (_width - 6) / 2);

        var midt = drawSide.ellipse(6, 6)
            .fill('pink')
            .stroke({ width: 1 })
            .move((_depth - 6) / 2, 0)
            .flip('y', _height / 2);
        */
    }

    this.done = function () {
        //work in progress needs to scale to fixed width
        drawTop.scale(1);
        drawSide.scale(1);

    }

    this.PrismGeometry = function (vertices, depth, loadside, material, position) {
        switch (loadside.valueOf()) {
            case "front":
                //top
                var pos = {
                    x: position.x * 100,
                    z: position.z * 100
                };
                var points = [];
                var pointsTemp = [];
                var _x;

                for (var i = 0; i < vertices.length; i++) {
                    _x = vertices[i].x * 100;
                    if (!pointsTemp.includes(_x)) {
                        pointsTemp.push(_x);
                    }
                }

                for (i = 0; i < pointsTemp.length; i++) {
                    points.push([0, pointsTemp[i]]);
                }

                for (i = pointsTemp.length - 1; i >= 0; i--) {
                    points.push([depth * 100, pointsTemp[i]]);
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][1] < 0) {
                        var amount = -points[i][1];
                        for (var j = 0; j < points.length; j++) {
                            points[j][1] += amount;
                        }
                        pos.x -= amount;
                    }
                }

                var poly = drawTop.polygon(points);
                poly.fill("none")
                    .stroke({ width: 1 })
                    .move((_depth - depth * 100) / 2 + pos.z, _width / 2 + pos.x)
                    .flip('y', _width / 2);

                //side
                var pos = {
                    y: position.y * 100,
                    z: position.z * 100
                };
                var points = [];
                var pointsTemp = [];
                var _y;

                for (var i = 0; i < vertices.length; i++) {
                    _y = vertices[i].y * 100;
                    if (!pointsTemp.includes(_y)) {
                        pointsTemp.push(_y);
                    }
                }

                for (i = 0; i < pointsTemp.length; i++) {
                    points.push([0, pointsTemp[i]]);
                }

                for (i = pointsTemp.length - 1; i >= 0; i--) {
                    points.push([depth * 100, pointsTemp[i]]);
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][1] < 0) {
                        var amount = -points[i][1];
                        for (var j = 0; j < points.length; j++) {
                            points[j][1] += amount;
                        }
                        pos.y -= amount;
                    }
                }

                var poly = drawSide.polygon(points);
                poly.fill(material.color)
                    .stroke({ width: 1 })
                    .move((_depth - depth * 100) / 2 + pos.z, pos.y)
                    .flip('y', _height / 2);
                break;

            case "side":
                //top
                var pos = {
                    x: position.x * 100,
                    z: position.z * 100
                };
                var points = [];
                var pointsTemp = [];
                var _x;

                for (var i = 0; i < vertices.length; i++) {
                    _x = vertices[i].x * 100;
                    if (!pointsTemp.includes(_x)) {
                        pointsTemp.push(_x);
                    }
                }

                for (i = 0; i < pointsTemp.length; i++) {
                    points.push([pointsTemp[i], 0]);
                }

                for (i = pointsTemp.length - 1; i >= 0; i--) {
                    points.push([pointsTemp[i], depth * 100]);
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][0] < 0) {
                        var amount = -points[i][0];
                        for (var j = 0; j < points.length; j++) {
                            points[j][0] += amount;
                        }
                        pos.z -= amount;
                    }
                }

                var poly = drawTop.polygon(points);
                poly.fill("none")
                    .stroke({ width: 1 })
                    .move(_depth / 2 + pos.z, (_width - depth * 100) / 2 + pos.x)
                    .flip('y', _width / 2);

                //side
                var pos = {
                    z: position.z * 100,
                    y: position.y * 100
                };
                var points = [];

                for (var i = 0; i < vertices.length; i++) {
                    points.push([vertices[i].x * 100, vertices[i].y * 100]);
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][0] < 0) {
                        var amount = -points[i][0];
                        for (var j = 0; j < points.length; j++) {
                            points[j][0] += amount;
                        }
                        pos.z -= amount;
                    }
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][1] < 0) {
                        var amount = -points[i][1];
                        for (var j = 0; j < points.length; j++) {
                            points[j][1] += amount;
                        }
                        pos.y -= amount;
                    }
                }

                var poly = drawSide.polygon(points);
                poly.fill(material.color)
                    .stroke({ width: 1 })
                    .move(_depth / 2 + pos.z, pos.y)
                    .flip('y', _height / 2)
                break;

            case "top":
                //top
                var pos = {
                    x: position.x * 100,
                    z: position.z * 100
                };
                var points = [];

                for (var i = 0; i < vertices.length; i++) {
                    points.push([vertices[i].x * 100, vertices[i].y * 100]);
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][0] < 0) {
                        var amount = -points[i][0];
                        for (var j = 0; j < points.length; j++) {
                            points[j][0] += amount;
                        }
                        pos.x -= amount;
                    }
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][1] < 0) {
                        var amount = -points[i][1];
                        for (var j = 0; j < points.length; j++) {
                            points[j][1] += amount;
                        }
                        pos.z -= amount;
                    }
                }

                var poly = drawTop.polygon(points);
                poly.fill(material.color)
                    .stroke({ width: 1 })
                    .move(_depth / 2 + pos.z, _width / 2 + pos.x)
                    .flip('y', _width / 2);

                //side
                var pos = {
                    y: position.y * 100,
                    z: position.z * 100
                };
                var points = [];
                var pointsTemp = [];
                var _z;

                for (var i = 0; i < vertices.length; i++) {
                    _z = vertices[i].y * 100;
                    if (!pointsTemp.includes(_z)) {
                        pointsTemp.push(_z);
                    }
                }

                for (i = 0; i < pointsTemp.length; i++) {
                    points.push([pointsTemp[i], 0]);
                }

                for (i = pointsTemp.length - 1; i >= 0; i--) {
                    points.push([pointsTemp[i], depth * 100]);
                }

                for (var i = 0; i < points.length; i++) {
                    if (points[i][0] < 0) {
                        var amount = -points[i][0];
                        for (var j = 0; j < points.length; j++) {
                            points[j][0] += amount;
                        }
                        pos.z -= amount;
                    }
                }

                var poly = drawSide.polygon(points);
                poly.fill(material.color)
                    .stroke({ width: 1 })
                    .move(_depth / 2 + pos.z, 0)
                    .flip('y', _height / 2);

                break;
        }



    }
    this.makeGeometry = function (object, material, position) {
        var _x = object.x * 100;
        var _y = object.y * 100;
        var _z = object.z * 100;

        //top
        var poly = drawTop.rect(_z, _x)
            .fill('none')
            .stroke({ width: 1 })
            .move((_depth - _z) / 2 + position.z * 100, (_width - _x) / 2 + position.x * 100)
            .flip('y', _width / 2);

        //side
        var poly = drawSide.rect(_z, _y)
            .fill(material.color)
            .stroke({ width: 1 })
            .move((_depth - _z) / 2 + position.z * 100, position.y * 100)
            .flip('y', _height / 2)
    }

}

function Vector2(_x, _y) {
    this.x = _x;
    this.y = _y;
}
function Position(_x, _y, _z) {
    this.x = _x;
    this.y = _y;
    this.z = _z;
}
