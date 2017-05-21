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

    this.prismGeometry = function(vertices, depth, loadside, material, position) { //function made for the gable roof
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

        var geometryObj = new THREE.ExtrudeGeometry(shape, settings);

        switch (loadside.valueOf()) {
            case "front":
                geometryObj.translate(0, 0, -depth / 2);
                break;
            case "side":
                geometryObj.translate(0, 0, -depth / 2);
                geometryObj.rotateY(THREE.Math.degToRad(-90));
                break;
            case "top":
                geometryObj.rotateX(THREE.Math.degToRad(90));
                geometryObj.translate(0, depth, 0);
                break;
        }
        pushToCanvas(geometryObj, material, position);
    };

    this.makeGeometry = function(geometry, material, position) {
        var geometryObj = new THREE.BoxGeometry(geometry.x, geometry.y, geometry.z);
        //moves cube up half of Y to get a new center at 0,0,0
        //this makes it easier to place later without going under the ground
        geometryObj.translate(0, geometry.y / 2, 0);
        pushToCanvas(geometryObj, material, position);
    }

    function pushToCanvas(geometryObj, material, position) {
        var meshObj = new THREE.Mesh(geometryObj, material);
        meshObj.castShadow = true;
        meshObj.position.set(position.x, position.y, position.z);
        loader.add(meshObj);
    }


}