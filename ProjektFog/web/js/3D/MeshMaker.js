function MeshMaker(loader) {
    //used in svg
    this.paint = function() {}
    this.drawMeasurements = function(length, axis, position) {}

    this.done = function() {
        loader.paint();
    }

    this.wood = new THREE.MeshPhongMaterial({
        color: 0x8c4d27,
        shininess: 100,
        side: THREE.DoubleSide
    });
    this.plastic = new THREE.MeshPhongMaterial({
        color: 0xdbdbdb,
        shininess: 100,
        transparent: true,
        opacity: 0.6

    });

    this.PrismGeometry = function(vertices, depth, loadside, material, position) { //function made for the gable roof
        var shape = new THREE.Shape();
        var settings;
        shape.moveTo(vertices[0].x, vertices[0].y);
        for (var i = 1; i < vertices.length; i++) {
            shape.lineTo(vertices[i].x, vertices[i].y);
        }
        shape.lineTo(vertices[0].x, vertices[0].y);
        settings = {
            amount: depth,
            bevelEnabled: false
        };

        var object = new THREE.ExtrudeGeometry(shape, settings);

        switch (loadside.valueOf()) {
            case "front":
                object.translate(0, 0, -depth / 2);
                break;
            case "side":
                object.translate(0, 0, -depth / 2);
                object.rotateY(THREE.Math.degToRad(-90));
                break;
            case "top":
                object.rotateX(THREE.Math.degToRad(90));
                object.translate(0, depth, 0);
                break;
        }
        pushToCanvas(object, material, position);
    };

    this.makeGeometry = function(object, material, position) {
        var geometry = new THREE.BoxGeometry(object.x, object.y, object.z);
        //moves cube up half of Y to get a new center at 0,0,0
        //this makes it easier to place later without going under the ground
        geometry.translate(0, object.y / 2, 0);
        pushToCanvas(geometry, material, position);
    }

    function pushToCanvas(geometry, material, position) {
        var object = new THREE.Mesh(geometry, material);
        object.castShadow = true;
        object.position.set(position.x, position.y, position.z);
        object.name = 'carport'; //needs to be something else later
        loader.add(object);
    }


}