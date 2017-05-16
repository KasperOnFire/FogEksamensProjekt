function SvgMaker() {

    var _width;
    var _height;
    var _depth;

    var drawTop, drawSide;

    drawTop = new SVG('topView');
    drawSide = new SVG('sideView');
    var sideMid, topMid;

    this.wood = {
        color: 'white'
    }
    this.plastic = {
        color: 'white'
    }

    function calcMid() {
        sideMid = {
            z: _depth / 2,
            y: 50,
        }

        topMid = {
            z: _depth / 2,
            x: _width / 2,
        }
    }

    function makeCanvas(width, height, depth) {
        drawTop.size(depth, width);
        drawSide.size(depth, height);
        _width = width;
        _height = height;
        _depth = depth;
        calcMid();
    }

    this.paint = function(width, height, depth) {
        makeCanvas(width * 100, height * 100, depth * 100);
        drawTop.clear();
        drawSide.clear();
    }

    this.done = function() {
        //#fix scale to fit in a box
        drawTop.scale(1);
        drawSide.scale(1);

    }

    this.prismGeometry = function(vertices, depth, loadside, material, position) {
        switch (loadside.valueOf()) {
            case "front":
                //top
                prismGeomFrontViewTop(vertices, depth, position);

                //side
                prismGeomFrontViewSide(vertices, depth, position);
                break;

            case "side":
                //top
                prismGeomSideViewTop(vertices, depth, position);

                //side
                prismGeomSideViewSide(vertices, depth, position);
                break;

            case "top":
                //top
                prismGeomTopViewTop(vertices, depth, position);
                //side
                prismGeomTopViewSide(vertices, depth, position);
                break;
        }
    }

    this.makeGeometry = function(object, material, position) {
        var _x = object.x * 100;
        var _y = object.y * 100;
        var _z = object.z * 100;

        //top
        var poly = drawTop.rect(_z, _x)
            .fill('none')
            .stroke({ width: 1 })
            .move(topMid.z - _z / 2 + position.z * 100, topMid.x - _x / 2 + position.x * 100)
            .flip('y', topMid.x);

        //side
        var poly = drawSide.rect(_z, _y)
            .fill('white')
            .stroke({ width: 1 })
            .move(sideMid.z - _z / 2 + position.z * 100, sideMid.y + position.y * 100)
            .flip('y', _height / 2);
    }

    this.drawMeasurements = function(length, axis, position) {
        length *= 100;
        if (length % 1 < 0.5) {
            length = Math.floor(length);
        } else {
            length = Math.ceil(length);
        }
        axis.toLowerCase();

        //top
        if (axis == 'x') {
            var line = drawTop.polyline([
                    [0, 0],
                    [10, 0],
                    [5, 0],
                    [5, length],
                    [0, length],
                    [10, length]
                ])
                .fill('none')
                .stroke({ width: 1 })
                .move(topMid.z - 5 + position.z * 100, topMid.x - (length / 2) + position.x * 100)
                .flip('y', topMid.x);
            var text = drawTop.text((length) + 'cm')
                .move(topMid.z - 35 + position.z * 100, topMid.x + position.x * 100)
                .rotate(-90)
        }

        if (axis == 'z') {
            var line = drawTop.polyline([
                    [0, 0],
                    [0, 10],
                    [0, 5],
                    [length, 5],
                    [length, 0],
                    [length, 10]
                ])
                .fill('none')
                .stroke({ width: 1 })
                .move(topMid.z - (length / 2) + position.z * 100, topMid.x + position.x * 100)
                .flip('y', topMid.x);
            var text = drawTop.text((length) + 'cm')
                .move(topMid.z + position.z * 100, topMid.x - position.x * 100)
        }

        //side
        if (axis == 'y') {
            var line = drawSide.polyline([
                    [0, 0],
                    [10, 0],
                    [5, 0],
                    [5, length],
                    [0, length],
                    [10, length]
                ])
                .fill('none')
                .stroke({ width: 1 })
                .move(sideMid.z - 5 + position.z * 100, sideMid.y + position.y * 100)
                .flip('y', _height / 2);
            var text = drawSide.text((length) + 'cm')
                .move(sideMid.z + position.z * 100 - 35, _height - sideMid.y + position.y * 100 - (length / 2))
                .rotate(-90)
        }

        if (axis == 'z') {
            var line = drawSide.polyline([
                    [0, 0],
                    [0, 10],
                    [0, 5],
                    [length, 5],
                    [length, 0],
                    [length, 10]
                ])
                .fill('none')
                .stroke({ width: 1 })
                .move(sideMid.z - (length / 2) + position.z * 100, sideMid.y + position.y * 100)
                .flip('y', _height / 2);
            var text = drawSide.text((length) + 'cm')
                .move(sideMid.z + position.z * 100, _height - sideMid.y - position.y * 100)
        }

    }

    function prismGeomFrontViewTop(vertices, depth, position) {
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
            .move(topMid.z - (depth * 100 / 2) + pos.z, topMid.x + pos.x)
            .flip('y', topMid.x);
    }

    function prismGeomFrontViewSide(vertices, depth, position) {
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
        poly.fill('white')
            .stroke({ width: 1 })
            .move(sideMid.z - (depth * 100 / 2) + pos.z, sideMid.y + pos.y)
            .flip('y', _height / 2);
    }

    function prismGeomSideViewTop(vertices, depth, position) {
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
            .move(topMid.z + pos.z, topMid.x - (depth * 100 / 2) + pos.x)
            .flip('y', topMid.x);
    }

    function prismGeomSideViewSide(vertices, depth, position) {
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
        poly.fill('white')
            .stroke({ width: 1 })
            .move(sideMid.z + pos.z, sideMid.y + pos.y)
            .flip('y', _height / 2);
    }

    function prismGeomTopViewTop(vertices, depth, position) {
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
        poly.fill('white')
            .stroke({ width: 1 })
            .move(topMid.z + pos.z, topMid.x + pos.x)
            .flip('y', topMid.x);
    }

    function prismGeomTopViewSide(vertices, depth, position) {
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
        poly.fill('white')
            .stroke({ width: 1 })
            .move(sideMid.z + pos.z, sideMid.y + pos.y)
            .flip('y', _height / 2);

    }
}