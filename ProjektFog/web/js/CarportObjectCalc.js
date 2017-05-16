function CarportObjectCalc(objectMaker) {

    var objectMaker = objectMaker;

    //presets
    var legsThickness = 0.15;
    var roofThickness = 0.05;
    var wallThickness = 0.05;
    var legSupportThickness = 0.2;
    var doorWidth = 0.7;
    var doorHeight = 1.8;

    //temp
    var boardThickness = 0.025;
    var boardHeight = 0.195;
    var rafterThickness = 0.07;
    var rafterHeight = 0.07;

    var slope = 0; //slope of a flat roof

    //calced form guiObjects
    //going to get mesurements from the first pull
    var carport = {
        width: 0,
        depth: 0,
        height: 0
    };

    var roof = {
        gable: false,
        overhang: {
            sides: 0,
            front: 0,
            back: 0
        }
    };

    var shed = {
        shed: false,
        depth: 0,
        doorPlacement: 0,
        side: '',
        rotateDoor: false
    };


    this.calcCarport = function (measurentObj) {
        setMeasurments(measurentObj);

        //Needed for SVG
        objectMaker.paint(
            carport.width + 1.5,
            ((roof.gable) ?
                carport.height + 1.5 + Math.log(carport.width) :
                carport.height + 1.5
            ),
            ((shed.shed) ?
                carport.depth + shed.depth + 3 :
                carport.depth + 3
            )
        );

        buildCarport();
        objectMaker.done();
    }

    function setMeasurments(measurentObj) {
        carport.width = measurentObj.carport.width / 100;
        carport.depth = measurentObj.carport.depth / 100;
        carport.height = measurentObj.carport.height / 100;

        roof.gable = measurentObj.roof.gableRoof;
        roof.overhang.sides = measurentObj.roof.overhang.sides / 100;
        roof.overhang.front = measurentObj.roof.overhang.front / 100;
        roof.overhang.back = measurentObj.roof.overhang.back / 100;

        shed.shed = measurentObj.shed.shed;
        shed.depth = measurentObj.shed.depth / 100;
        shed.side = measurentObj.shed.side;
        shed.doorPlacement = measurentObj.shed.doorPlacement;

        shed.rotateDoor = measurentObj.shed.rotateDoor;
    }

    function buildCarport() {
        calcSlope();
        if (shed.shed) {
            makeShed();
        }
        legs();
        if (roof.gable) {
            gableRoof();
        } else {
            flatRoof();
        }
        //mesurements for SVG
        //carport width
        objectMaker.drawMeasurements(carport.width - 2 * legsThickness,
            'x',
            new Vector3(
                0,
                0,
                ((shed.shed) ?
                    -(carport.depth + shed.depth) / 2 - roof.overhang.back - 0.2 :
                    -carport.depth / 2 - roof.overhang.back - 0.2
                )
            )
        );
        //roof width
        objectMaker.drawMeasurements(carport.width + roof.overhang.sides * 2,
            'x',
            new Vector3(
                0,
                0,
                ((shed.shed) ?
                    -(carport.depth + shed.depth) / 2 - roof.overhang.back - 0.4 :
                    -carport.depth / 2 - roof.overhang.back - 0.4
                )
            )
        );
    }

    //move to objectCalc
    function legs() {
        numberOfLegs = calcLegs();

        var zBack = ((shed.shed) ?
            -(carport.depth - legsThickness) / 2 + shed.depth / 2 :
            -(carport.depth - legsThickness) / 2);
        var tempX = (carport.width - legsThickness) / 2; //sets the temp x for the current  
        var tempZ = zBack; //sets the temp z
        var zJump = (carport.depth - legsThickness) /
            (numberOfLegs / 2 - 1); //calculates spaces between each leg

        for (i = 0; i < 2; i++) { //loop to place the 2 rows of legs
            for (j = 0; j < numberOfLegs / 2; j++) { //make the leg obejcts for one side
                objectMaker.makeGeometry({
                    x: legsThickness,
                    y: ((!roof.gable) ?
                        carport.height + calcHeightDifference(-slope, tempZ - zBack) :
                        carport.height
                    ),
                    z: legsThickness
                },
                    objectMaker.wood,
                    new Vector3(tempX, 0, tempZ)
                );

                tempZ += zJump; //move one step forward
            }
            tempZ = zBack; //resets z axis to the back again
            tempX = -tempX; //moves to the opposite side to place legs
        }

    }

    function gableRoof() {
        var _depth = ((shed.shed) ? carport.depth + shed.depth : carport.depth);
        _depth += roof.overhang.back + roof.overhang.front;
        for (i = -1; i <= 1; i += 2) { //loop to get one on each side
            objectMaker.makeGeometry({
                x: legsThickness,
                y: legSupportThickness,
                z: _depth
            },
                objectMaker.wood,
                new Vector3(
                    ((carport.width - legsThickness) / 2) * i,
                    carport.height - legSupportThickness,
                    (roof.overhang.front - roof.overhang.back) / 2)
            );
        }

        var geometry = objectMaker.prismGeometry([
            new Vector2(-carport.width / 2 - roof.overhang.sides, 0), //left corner 
            new Vector2(0, Math.log(carport.width)), //top
            new Vector2(carport.width / 2 + roof.overhang.sides, 0) //rigth corner
        ], 

            _depth,
            'front',
            objectMaker.wood,
            new Vector3(
                0,
                carport.height,
                (roof.overhang.front - roof.overhang.back) / 2
            )
        );

        //svg
        objectMaker.drawMeasurements(
            _depth,
            'z',
            new Vector3(
                (-carport.width / 2 - 0.55),
                (-0.1),
                (roof.overhang.front - roof.overhang.back) / 2
            )
        );
        //svg
        objectMaker.drawMeasurements(
            carport.height - legSupportThickness,
            'y',
            new Vector3(
                0,
                0,
                (_depth - roof.overhang.back + roof.overhang.front) / 2 + 0.35
            )
        );
    }

    function flatRoof() {
        var _depth = ((shed.shed) ?
            (carport.depth + shed.depth) / 2 :
            carport.depth / 2
        );
        var _distance = ((shed.shed) ?
            shed.depth + roof.overhang.back :
            roof.overhang.back
        );
        var _posY = carport.height + calcHeightDifference(slope, _distance);
        var _front = calcHeightDifference(-slope,
            _depth * 2 + roof.overhang.back + roof.overhang.front
        );

        for (i = -1; i <= 1; i += 2) { //loop to get one on each side
            objectMaker.prismGeometry([
                new Vector2(-_depth - roof.overhang.back, legSupportThickness), //top back
                new Vector2(-_depth - roof.overhang.back, 0), //bottom back 
                new Vector2(_depth + roof.overhang.front, _front), //bottom front  
                new Vector2(_depth + roof.overhang.front, _front + legSupportThickness)
            ], //top front
                legsThickness,
                'side',
                objectMaker.wood,
                new Vector3(
                    ((carport.width - legsThickness) / 2) * i,
                    _posY - legSupportThickness,
                    0)
            );
        }

        //roof
        objectMaker.prismGeometry([
            new Vector2(-_depth - roof.overhang.back, roofThickness), //top back
            new Vector2(-_depth - roof.overhang.back, 0), //bottom back 
            new Vector2(_depth + roof.overhang.front, _front), //bottom front  
            new Vector2(_depth + roof.overhang.front, _front + roofThickness)
        ], //top front
            carport.width + roof.overhang.sides * 2,
            'side',
            objectMaker.plastic,
            new Vector3(0, _posY + rafterHeight, 0) //maybe not
        );

        //rafter
        var rafterAmount = Math.ceil(_depth * 2 / 0.6);
        var _heightdif = calcHeightDifference(-slope, rafterHeight);

        var _tempZ = -_depth - roof.overhang.back + rafterThickness/2;
        var _zSpacing = (_depth * 2 + roof.overhang.front + roof.overhang.back - rafterThickness) / rafterAmount;

        var _tempY = _posY;
        var _heightBumb = calcHeightDifference(-slope, _zSpacing);

        for (i = rafterAmount; i >= 0; i--) {
            objectMaker.prismGeometry([
                new Vector2(-rafterThickness / 2 - _heightdif, rafterHeight), //top back
                new Vector2(-rafterThickness / 2, 0), //bottom back 
                new Vector2(rafterThickness / 2, _heightdif), //bottom front  
                new Vector2(rafterThickness / 2 - _heightdif, _heightdif + rafterHeight) //top front
            ],
                carport.width + roof.overhang.sides * 2,
                'side',
                objectMaker.wood,
                new Vector3(0, _tempY, _tempZ)
            );
            _tempZ += _zSpacing; //move one step forward
            _tempY += _heightBumb; //moves up
        }

        //boards
        //back
        objectMaker.prismGeometry([
            new Vector2(-boardThickness / 2, boardHeight), //top back
            new Vector2(-boardThickness / 2, 0), //bottom back 
            new Vector2(boardThickness / 2, 0), //bottom front  
            new Vector2(boardThickness / 2, boardHeight) //top front
        ],
            carport.width + roof.overhang.sides * 2,
            'side',
            objectMaker.wood,
            new Vector3(
                0,
                _posY - boardThickness - rafterHeight + roofThickness / 2
                , -_depth - roof.overhang.back - boardThickness / 2
            )
        );
        //front
        objectMaker.prismGeometry([
            new Vector2(-boardThickness / 2, boardHeight), //top back
            new Vector2(-boardThickness / 2, 0), //bottom back 
            new Vector2(boardThickness / 2, 0), //bottom front  
            new Vector2(boardThickness / 2, boardHeight) //top front
        ],
            carport.width + roof.overhang.sides * 2,
            'side',
            objectMaker.wood,
            new Vector3(
                0,
                _posY + _front - boardThickness - rafterHeight + roofThickness / 2,
                _depth + roof.overhang.front + boardThickness / 2
            )
        );
        //right
        objectMaker.prismGeometry([
            new Vector2(-_depth - roof.overhang.back - boardThickness, boardHeight), //top back
            new Vector2(-_depth - roof.overhang.back - boardThickness, 0), //bottom back 
            new Vector2(_depth + roof.overhang.front + boardThickness, _front), //bottom front  
            new Vector2(_depth + roof.overhang.front + boardThickness, _front + boardHeight) //top front
        ],
            boardThickness,
            'side',
            objectMaker.wood,
            new Vector3(
                carport.width / 2 + roof.overhang.sides,
                _posY - boardThickness - rafterHeight + roofThickness / 2,
                0)
        );

        //left
        objectMaker.prismGeometry([
            new Vector2(-_depth - roof.overhang.back - boardThickness, boardHeight), //top back
            new Vector2(-_depth - roof.overhang.back - boardThickness, 0), //bottom back 
            new Vector2(_depth + roof.overhang.front + boardThickness, _front), //bottom front  
            new Vector2(_depth + roof.overhang.front + boardThickness, _front + boardHeight) //top front
        ],
            boardThickness,
            'side',
            objectMaker.wood,
            new Vector3(
                (-carport.width / 2 - roof.overhang.sides),
                _posY - boardThickness - rafterHeight + roofThickness / 2,
                0)
        );


        //svg
        objectMaker.drawMeasurements(
            _depth * 2 + roof.overhang.back + roof.overhang.front,
            'z',
            new Vector3(
                (-carport.width / 2 - 0.55),
                (-0.1),
                (roof.overhang.front - roof.overhang.back) / 2
            )
        );
        //svg
        objectMaker.drawMeasurements(
            _posY - legSupportThickness,
            'y',
            new Vector3(
                0,
                0,
                (-_depth * 2 - roof.overhang.back - roof.overhang.front) / 2 - 0.15
            )
        );

        //svg
        objectMaker.drawMeasurements(
            _posY + _front - legSupportThickness,
            'y',
            new Vector3(
                0,
                0,
                (_depth * 2 - roof.overhang.back + roof.overhang.front) / 2 + 0.35
            )
        );
    }

    function makeShed() {
        var _back = carport.height + calcHeightDifference(slope, shed.depth);
        var _front = _back + calcHeightDifference(-slope, shed.depth);

        //frontwall
        var vectors = [];
        vectors.push(new Vector2(-carport.width / 2 + wallThickness, _front)); //top left
        vectors.push(new Vector2(-carport.width / 2 + wallThickness, 0)); //bottom left 
        if (shed.side == 'Foran') {
            doorMid = shed.doorPlacement * (carport.width / 2 - doorWidth / 2 - legsThickness);
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            //door front
            var posX = 0;
            var posZ = (-carport.depth - wallThickness + shed.depth) / 2;
            if (!shed.rotateDoor) {
                door(1, 1, posX + doorLeft, posZ);
            } else {
                door(-1, 1, posX + doorRight, posZ);
            }

            //door outline
            vectors.push(new Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new Vector2(carport.width / 2 - wallThickness, 0)); //bottom right  
        vectors.push(new Vector2(carport.width / 2 - wallThickness, _front)); //top right
        objectMaker.prismGeometry(
            vectors,
            wallThickness,
            'front',
            objectMaker.wood,
            new Vector3(0, 0, (-carport.depth - wallThickness + shed.depth) / 2)
        );

        //back
        vectors = [];
        vectors.push(new Vector2(-carport.width / 2 + wallThickness, _back)); //top left
        vectors.push(new Vector2(-carport.width / 2 + wallThickness, 0)); //bottom left 
        if (shed.side == 'Bagved') {
            doorMid = shed.doorPlacement * (carport.width / 2 - doorWidth / 2 - legsThickness);
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            //door back
            var posX = 0;
            var posZ = (-carport.depth + shed.depth) / 2 - shed.depth;
            if (!shed.rotateDoor) {
                door(1, -1, posX + doorLeft, posZ);
            } else {
                door(-1, -1, posX + doorRight, posZ);
            }

            //door outline
            vectors.push(new Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new Vector2(carport.width / 2 - wallThickness, 0)); //bottom right  
        vectors.push(new Vector2(carport.width / 2 - wallThickness, _back)); //top right
        objectMaker.prismGeometry(
            vectors,
            wallThickness,
            'front',
            objectMaker.wood,
            new Vector3(0, 0, (-carport.depth + wallThickness + shed.depth) / 2 - shed.depth)
        );

        //right
        vectors = [];
        vectors.push(new Vector2(-shed.depth / 2, _back)); //top back
        vectors.push(new Vector2(-shed.depth / 2, 0)); //bottom back 
        if (shed.side == 'Højre') {
            doorMid = shed.doorPlacement * (shed.depth / 2 - doorWidth / 2 - wallThickness);
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            //door right
            var posX = carport.width / 2 - wallThickness / 2;
            var posZ = -carport.depth / 2;
            if (!shed.rotateDoor) {
                door(1, 1, posX, posZ + doorLeft);
            } else {
                door(1, -1, posX, posZ + doorRight);
            }

            //door outline
            vectors.push(new Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new Vector2(shed.depth / 2, 0)); //bottom front  
        vectors.push(new Vector2(shed.depth / 2, _front)); //top front
        objectMaker.prismGeometry(
            vectors,
            wallThickness,
            'side',
            objectMaker.wood,
            new Vector3(carport.width / 2 - wallThickness / 2, 0, -carport.depth / 2)
        );

        //left
        vectors = [];
        vectors.push(new Vector2(-shed.depth / 2, _back)); //top back
        vectors.push(new Vector2(-shed.depth / 2, 0)); //bottom back 
        if (shed.side == 'Venstre') {
            doorMid = shed.doorPlacement * (shed.depth / 2 - doorWidth / 2 - wallThickness);
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            //door left
            var posX = -carport.width / 2 + wallThickness / 2;
            var posZ = -carport.depth / 2;
            if (!shed.rotateDoor) {
                door(-1, 1, posX, posZ + doorLeft);
            } else {
                door(-1, -1, posX, posZ + doorRight);
            }

            //door outline
            vectors.push(new Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new Vector2(shed.depth / 2, 0)); //bottom front  
        vectors.push(new Vector2(shed.depth / 2, _front)); //top front
        objectMaker.prismGeometry(
            vectors,
            wallThickness,
            'side',
            objectMaker.wood,
            new Vector3(-carport.width / 2 + wallThickness / 2, 0, -carport.depth / 2)
        );

    }

    function door(x, y, posX, posZ) {
        var doorDis = (Math.sin(degToRad(45)) * doorWidth) / Math.sin(degToRad(90));
        var _offset = 0.01;
        var _x = doorDis * x;
        var _y = doorDis * y;
        objectMaker.prismGeometry([
            new Vector2(0, 0), //center
            new Vector2(0.01, 0), //center 
            new Vector2(_x, _y), //outer  
            new Vector2(_x - _offset, _y)
        ], //outer
            doorHeight,
            'top',
            objectMaker.wood,
            new Vector3(posX, 0, posZ)
        );
    }


    //math
    function degToRad(degree) {
        return degree * (Math.PI / 180);
    }

    function calcHeightDifference(slope, distance) {
        return (Math.sin(degToRad(slope)) * distance) / Math.sin(degToRad(90 - slope));
    }

    function calcLegs() {
        var m2 = (carport.width + roof.overhang.sides * 2)
            * (carport.depth + roof.overhang.front + roof.overhang.back);
        numberOfLegs = Math.ceil(m2 / 2 / 4) * 2;
        if (numberOfLegs <= 3) {
            numberOfLegs = 4;
        }
        return numberOfLegs;
    }

    function calcSlope() {
        slope = (
            (!roof.gable) ?
                -Math.tan(0.1
                    / (carport.depth - roof.overhang.back - roof.overhang.front)) * 100 :
                0
        );
    }
}

function Vector2(_x, _y) {
    this.x = _x;
    this.y = _y;
}

function Vector3(_x, _y, _z) {
    this.x = _x;
    this.y = _y;
    this.z = _z;
}