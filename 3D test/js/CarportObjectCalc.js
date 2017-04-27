function CarportObjectCalc(objectMaker) {

    var objectMaker = objectMaker;

    //presets
    var legsThickness = 0.15;
    var roofThickness = 0.05;
    var wallThickness = 0.02;
    var legSupportThickness = 0.2;
    var doorWidth = 0.7;
    var doorHeight = 1.8;

    var slope = 0;

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


    this.calcCarport = function (object) {
        setMesurments(object);
        buildCarport();
        objectMaker.done();
    }

    function setMesurments(object) {
        carport.width = object.guiCarport.width / 100;
        carport.depth = object.guiCarport.depth / 100;
        carport.height = object.guiCarport.height / 100;

        roof.gable = object.guiRoof.gableRoof;
        roof.overhang.sides = object.guiRoof.overhang.sides / 100;
        roof.overhang.front = object.guiRoof.overhang.front / 100;
        roof.overhang.back = object.guiRoof.overhang.back / 100;

        shed.shed = object.guiShed.shed;
        shed.depth = object.guiShed.depth / 100;
        shed.side = object.guiShed.side;
        shed.doorPlacement = object.guiShed.doorPlacement;

        shed.rotateDoor = object.guiShed.rotateDoor;
    }

    function position(x, y, z) {
        return {
            x: x,
            y: y,
            z: z
        }
    }
    function buildCarport() {
        //calcSlope();
        if (roof.gable) {
            gableRoof();
        } else {
            flatRoof();
        }
        legs();
        if (shed.shed) {
            makeShed();
        }
    }

    //move to objectCalc
    function legs() {
        numberOfLegs = calcLegs();

        var zBack = roof.overhang.back - (carport.depth - legsThickness) / 2;
        var tempX = (carport.width - legsThickness) /
            2 - roof.overhang.sides; //sets the temp x for the current  
        var tempZ = zBack; //sets the temp z
        var zJump = (carport.depth - legsThickness - roof.overhang.back - roof.overhang.front) /
            (numberOfLegs / 2 - 1); //calculates spaces between each leg
        var heightBumb = ((!roof.gable) ? 0.15 / (numberOfLegs / 2) : 0);
        //position of all legs
        for (i = 1; i <= 2; i++) { //loop to place the 2 rows of legs
            for (j = 0; j < numberOfLegs / 2; j++) { //make the leg obejcts for one side
                objectMaker.makeGeometry(
                    {
                        x: legsThickness,
                        y: carport.height - legSupportThickness + heightBumb * j + 0.03,
                        z: legsThickness
                    },
                    objectMaker.wood,
                    position(tempX, 0, tempZ)
                );

                tempZ += zJump; //move one step forward
            }
            tempZ = zBack; //resets z axis to the back again
            tempX = -tempX; //moves to the opposite side to place legs
        }

    }

    function gableRoof() {
        var _depth = ((shed.shed) ? carport.depth + shed.depth : carport.depth);
        for (i = -1; i <= 1; i += 2) { //loop to get one on each side
            objectMaker.makeGeometry(
                {
                    x: legsThickness,
                    y: legSupportThickness,
                    z: _depth
                },
                objectMaker.wood,
                position(
                    ((carport.width - legsThickness) / 2 - roof.overhang.sides)*i,
                    carport.height - legSupportThickness,
                    0)
            );
        }
        var geometry = objectMaker.PrismGeometry([
            new THREE.Vector2(0, Math.log(carport.width)), //top
            new THREE.Vector2(-carport.width / 2, 0), //left corner 
            new THREE.Vector2(carport.width / 2, 0)], //rigth corner 
            _depth,
            'front',
            objectMaker.wood,
            position(0, carport.height, 0)
        );
    }

    function flatRoof() {
         for (i = -1; i <= 1; i += 2) { //loop to get one on each side
            objectMaker.PrismGeometry([
                new THREE.Vector2(-carport.depth / 2, legSupportThickness), //top back
                new THREE.Vector2(-carport.depth / 2, 0), //bottom back 
                new THREE.Vector2(carport.depth / 2, 0.1), //bottom front  
                new THREE.Vector2(carport.depth / 2, 0.1 + legSupportThickness)], //top front
                legsThickness,
                'side',
                objectMaker.wood,
                position(
                    ((carport.width - legsThickness) / 2 - roof.overhang.sides)*i,
                    carport.height - legSupportThickness,
                    0)
            );
        }
        objectMaker.PrismGeometry([
            new THREE.Vector2(-carport.depth / 2, roofThickness), //top back
            new THREE.Vector2(-carport.depth / 2, 0), //bottom back 
            new THREE.Vector2(carport.depth / 2, 0.1), //bottom front  
            new THREE.Vector2(carport.depth / 2, 0.1 + roofThickness)], //top front
            carport.width,
            'side',
            objectMaker.wood,
            position(0, carport.height, 0)
        );
    }

    function makeShed() { //change y offset later
        calcSlope();
        var heightDifference = -(Math.sin(THREE.Math.degToRad(slope)) * shed.depth) /
            Math.sin(THREE.Math.degToRad(90 + slope));

        //frontwall
        var vectors = [];
        vectors.push(new THREE.Vector2(-carport.width / 2 + roof.overhang.sides, carport.height)); //top left
        vectors.push(new THREE.Vector2(-carport.width / 2 + roof.overhang.sides, 0)); //bottom left 
        if (shed.side == 'Foran') {
            doorMid = 0 + shed.doorPlacement * carport.width / 2;
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            vectors.push(new THREE.Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new THREE.Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new THREE.Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new THREE.Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new THREE.Vector2(carport.width / 2 - roof.overhang.sides, 0)); //bottom right  
        vectors.push(new THREE.Vector2(carport.width / 2 - roof.overhang.sides, carport.height)); //top right
        objectMaker.PrismGeometry(
            vectors,
            wallThickness,
            'front',
            objectMaker.wood,
            position(0, 0, -carport.depth / 2 + wallThickness / 2)
        );

        //back
        vectors = [];
        vectors.push(new THREE.Vector2(-carport.width / 2 + roof.overhang.sides, carport.height - heightDifference)); //top left
        vectors.push(new THREE.Vector2(-carport.width / 2 + roof.overhang.sides, 0)); //bottom left 
        if (shed.side == 'Bagved') {
            doorMid = 0 + shed.doorPlacement * carport.width / 2;
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            vectors.push(new THREE.Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new THREE.Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new THREE.Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new THREE.Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new THREE.Vector2(carport.width / 2 - roof.overhang.sides, 0)); //bottom right  
        vectors.push(new THREE.Vector2(carport.width / 2 - roof.overhang.sides, carport.height - heightDifference)); //top right
        objectMaker.PrismGeometry(
            vectors,
            wallThickness,
            'front',
            objectMaker.wood,
            position(0, 0, -carport.depth / 2 + wallThickness / 2 - shed.depth)
        );

        //left
        vectors = [];
        vectors.push(new THREE.Vector2(-shed.depth / 2, carport.height - heightDifference)); //top back
        vectors.push(new THREE.Vector2(-shed.depth / 2, 0)); //bottom back 
        if (shed.side == 'Venstre') {
            doorMid = 0 + shed.doorPlacement * shed.depth / 2;
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            vectors.push(new THREE.Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new THREE.Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new THREE.Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new THREE.Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new THREE.Vector2(shed.depth / 2, 0)); //bottom front  
        vectors.push(new THREE.Vector2(shed.depth / 2, carport.height)); //top front
        objectMaker.PrismGeometry(
            vectors,
            wallThickness,
            'side',
            objectMaker.wood,
            position(-carport.width / 2 + roof.overhang.sides + wallThickness / 2, 0, (-carport.depth - shed.depth) / 2 + wallThickness / 2)
        );
        //right
        vectors = [];
        vectors.push(new THREE.Vector2(-shed.depth / 2, carport.height - heightDifference)); //top back
        vectors.push(new THREE.Vector2(-shed.depth / 2, 0)); //bottom back 
        if (shed.side == 'Højre') {
            doorMid = 0 + shed.doorPlacement * shed.depth / 2;
            var doorLeft = doorMid - doorWidth / 2;
            var doorRight = doorMid + doorWidth / 2;

            vectors.push(new THREE.Vector2(doorLeft, 0)); //door bottom left
            vectors.push(new THREE.Vector2(doorLeft, doorHeight)); //door top left
            vectors.push(new THREE.Vector2(doorRight, doorHeight)); //door top right
            vectors.push(new THREE.Vector2(doorRight, 0)); //door bottom right
        }
        vectors.push(new THREE.Vector2(shed.depth / 2, 0)); //bottom front  
        vectors.push(new THREE.Vector2(shed.depth / 2, carport.height)); //top front
        objectMaker.PrismGeometry(
            vectors,
            wallThickness,
            'side',
            objectMaker.wood,
            position(carport.width / 2 - roof.overhang.sides - wallThickness / 2, 0, (-carport.depth - shed.depth) / 2 + wallThickness / 2)
        );

    }






    //math
    function calcLegs() {
        var m2 = (carport.width) * (carport.depth) / 2;
        numberOfLegs = Math.ceil(m2 / 4) * 2;
        if (numberOfLegs <= 3) {
            numberOfLegs = 4;
        }
        return numberOfLegs;
    }

    function calcSlope() {
        slope = (
            (!roof.gable) ?
                -Math.tan(0.1 / (carport.depth - roof.overhang.back - roof.overhang.front)) * 100 :
                0
        ); //slope of a flat roof
    }


}