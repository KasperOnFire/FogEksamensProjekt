var camera, scene, renderer, controls; //three.js
var geometry;

//var for the carport
var height, depth, width, numberOfLegs;
var roofDepth, slope;

//to offset for the legs in the 3 places
var spaceBack = 0.15;
var spaceFront = 0.15;
var spaceSides = 0.15;

//preset dimentions of sizes
var legsThickness = 0.15;
var roofThickness = 0.30;

//presets for shed
var wallThickness = 0.02;
var doorHeight = 1.8;
var doorWidth = 0.7;
var doorThickness = wallThickness;

//gui
var gui;

//material
var material = new THREE.MeshPhongMaterial({
    color: 0x8c4d27,
    shininess: 100,
    side: THREE.DoubleSide
})

//gui data object
var guiItem = {
    resetCamera: function () {
        camera.position.set(0, 5, 9);
        controls.target.set(0, 1, 0);
        controls.update();
    },
    gableRoof: false,
    shed: false,
    width: 500,
    depth: 500,
    height: 230,
};
var guiShed = {
    shedDepth: 300,
    doorPlacement: 0.00,
    rotateDoor: false
}

//geom functions
function de2ra(degree) {
    return degree * (Math.PI / 180);
}

function PrismGeometry(vertices, depth) { //function made for the gable roof
    var shape = new THREE.Shape();
    shape.moveTo(vertices[0].x, vertices[0].y);
    for (var i = 1; i < vertices.length; i++) {
        shape.lineTo(vertices[i].x, vertices[i].y);
    }
    shape.lineTo(vertices[0].x, vertices[0].y);
    var settings = {
        amount: depth,
        bevelEnabled: false
    };
    var object = new THREE.ExtrudeGeometry(shape, settings);
    return object;
};


//gui functions
dat.GUI.prototype.removeFolder = function (name) {
    var folder = this.__folders[name];
    if (folder != null) {
        folder.close();
        this.__ul.removeChild(folder.domElement.parentNode);
        delete this.__folders[name];
        this.onResize();
        return;
    }
    return;
}

function shedFolder() {
    if (guiItem.shed) {
        var shed = gui.addFolder('shedFolder');
        shed.add(guiShed, 'shedDepth').min(200).max(400).step(5)
            .name('Dybde').onChange(function () { rerender() });
        shed.add(guiShed, 'doorPlacement').min(-0.8).max(0.8).step(0.05)
            .name('Dør placering').onChange(function () { rerender() });
        shed.add(guiShed, 'rotateDoor').name('Roter dør')
            .onChange(function () { rerender() });
        shed.open();
    } else {
        gui.removeFolder('shedFolder');
    }
}

function init() {
    scene = new THREE.Scene();

    camera = new THREE.PerspectiveCamera(
        70, window.innerWidth / window.innerHeight, 0.25, 300);

    camera.position.set(6, 5, 9);

    gui = new dat.gui.GUI();

    // Lights
    scene.add(new THREE.AmbientLight(0x726f6f));

    var spotLight = new THREE.SpotLight(0xffffff);
    spotLight.angle = Math.PI / 5; //how wide the angle of the light is, too wide and the shadows gets blurry 
    spotLight.penumbra = 0.2; //how sharp edges on the light is
    spotLight.position.set(9, 15, 9); //position of the light
    spotLight.castShadow = true;
    spotLight.shadow.camera.near = 3;
    spotLight.shadow.camera.far = 10;

    spotLight.shadow.mapSize.width = 1024;
    spotLight.shadow.mapSize.height = 1024;
    scene.add(spotLight);

    var dirLight = new THREE.DirectionalLight(0x55505a, 1);
    dirLight.position.set(0, 10, 0);
    dirLight.castShadow = true;
    dirLight.shadow.camera.near = 1;
    dirLight.shadow.camera.far = 20;

    dirLight.shadow.camera.right = 10;
    dirLight.shadow.camera.left = -10;
    dirLight.shadow.camera.top = 10;
    dirLight.shadow.camera.bottom = -10;

    dirLight.shadow.mapSize.width = 1024;
    dirLight.shadow.mapSize.height = 1024;
    scene.add(dirLight);

    // Renderer
    renderer = new THREE.WebGLRenderer();
    renderer.shadowMap.enabled = true;
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight);
    window.addEventListener('resize', onWindowResize, false);
    document.body.appendChild(renderer.domElement);

    // Controls
    controls = new THREE.OrbitControls(camera, renderer.domElement);
    controls.target.set(0, 1, 0);
    controls.update();

    // GUI
    gui.add(guiItem, 'resetCamera').name('Reset Kamera');
    gui.add(guiItem, 'gableRoof').name('Tag')
        .onChange(function () {
            rerender()
        });
    gui.add(guiItem, 'shed').name('Skur')
        .onChange(function () { shedFolder(); rerender() });
    gui.add(guiItem, 'width').min(200).max(750).step(5).name('Bredde')
        .onChange(function () {
            rerender()
        });
    gui.add(guiItem, 'depth').min(200).max(800).step(5).name('Dybde')
        .onChange(function () {
            rerender()
        });
    gui.add(guiItem, 'height').min(200).max(260).step(5).name('Højde')
        .onChange(function () {
            rerender()
        });
    shedFolder();


    // Geometry

    //ground
    var ground = new THREE.Mesh(
        new THREE.PlaneBufferGeometry(500, 500, 1, 1),
        new THREE.MeshPhongMaterial({
            color: 0xa0adaf,
            shininess: 150
        }));
    ground.rotation.x = -Math.PI / 2; // rotates X/Y to X/Z
    ground.receiveShadow = true;
    scene.add(ground);

    //load carport
    loadCarport();
}

function onWindowResize() {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();

    renderer.setSize(window.innerWidth, window.innerHeight);
}

function loadCarport() {
    //gets the dimentions from the GUI
    height = guiItem.height / 100;
    depth = guiItem.depth / 100;
    width = guiItem.width / 100;
    var shedDepth = guiShed.shedDepth / 100;
        

    roofDepth = (
        (!guiItem.gableRoof) ?
            Math.sqrt(Math.pow(0.10, 2) + Math.pow(depth, 2)) :
            depth
    );
    slope = (
        (!guiItem.gableRoof) ?
            -Math.tan(0.1 / (depth - spaceBack - spaceFront)) * 100 :
            0
    ); //slope of a flat roof

    function calcLegs() {
        var m2 = (width) * (depth) / 2;
        numberOfLegs = Math.ceil(m2 / 4) * 2;
        if (numberOfLegs <= 3) {
            numberOfLegs = 4;
        }
    }

    function legs() {
        calcLegs();
        var zBack = spaceBack - (depth - legsThickness) / 2; //Finds the z axis 
        var tempX = (width - legsThickness) /
            2 - spaceSides; //sets the temp x for the current 
        var tempZ = zBack; //sets the temp z
        var zJump = (depth - legsThickness - spaceBack - spaceFront) /
            (numberOfLegs / 2 - 1); //calculates spaces between each leg
        var heightBumb = 0;
        if (guiItem.gableRoof == false) {
            heightBumb = 0.1 / (numberOfLegs / 2);
        }

        for (i = 1; i <= 2; i++) { //loop to place the 2 rows of legs
            legSupport(tempX);
            for (j = 0; j < numberOfLegs / 2; j++) { //make the leg obejcts for one side
                geometry = new THREE.BoxGeometry(
                    legsThickness,
                    height + heightBumb,
                    legsThickness
                );
                object = new THREE.Mesh(geometry, material);
                object.position.set(
                    tempX,
                    ((height + heightBumb) / 2),
                    tempZ
                );
                object.castShadow = true;
                object.name = "leg"; //naming to find and remove again later
                scene.add(object);
                tempZ += zJump; //move one step forward
            }
            tempZ = zBack; //resets z axis to the back again
            tempX = -tempX; //moves to the opposite side to place legs
        }
    }

    function legSupport(x) {
        geometry = new THREE.BoxGeometry(
            legsThickness + 0.05,
            0.2,
            roofDepth - 0.20
        );
        object = new THREE.Mesh(geometry, material);
        object.position.set(x, height - 0.1, 0);
        object.castShadow = true;
        object.name = "roof"; //naming to find and remove again later
        if (guiItem.gableRoof == false) { //roof = gable lay support flat
            object.position.y += 0.1;
            object.rotateX(de2ra(slope));
        }
        scene.add(object);
    }

    function flatRoof() {
        geometry = new THREE.BoxGeometry(
            width,
            roofThickness,
            roofDepth
        );
        object = new THREE.Mesh(geometry, material);
        object.position.set(
            0,
            (height + roofThickness / 2) + 0.1,
            0
        );
        object.castShadow = true;
        object.name = "roof"; //naming to find and remove again later
        object.rotateX(de2ra(slope));
        scene.add(object);
    }

    function gableRoof() {
        var _depth = ((guiItem.shed) ? depth + shedDepth : depth);
        var geometry = PrismGeometry([
            new THREE.Vector2(0, Math.log(width)),  //top
            new THREE.Vector2(-width / 2, 0), //left corner 
            new THREE.Vector2(width / 2, 0)  //rigth corner
        ], _depth);

        var object = new THREE.Mesh(geometry, material);
        object.castShadow = true;
        object.name = "roof"; //naming to find and remove again later
        object.position.set(0, height, -_depth / 2);

        scene.add(object);
    }

    function shed() {
        var shedHeightDiference = 0.3; //needs to be calculated from slope on roof or something
        var shedHeightBack = (
            (guiItem.gableRoof) ?
                height :
                height - shedHeightDiference
        );
        var shedHeightFront = height;
        var offsetFromCenterZ = -(depth + shedDepth) / 2;
        var doorRotation = ((guiShed.rotateDoor) ? 1 : -1);
        var doorLeft = (
            (guiShed.rotateDoor) ?
                width / 2 * guiShed.doorPlacement - 0.47 * doorRotation :
                width / 2 * guiShed.doorPlacement + 0.23 * doorRotation
        );

        //side walls
        geometry = PrismGeometry([
            new THREE.Vector2(shedDepth / 2, 0), //bottom front  
            new THREE.Vector2(shedDepth / 2, shedHeightFront),  //top front
            new THREE.Vector2(-shedDepth / 2, shedHeightBack),  //top back
            new THREE.Vector2(-shedDepth / 2, 0)  //bottom back 
        ], wallThickness);

        var offsetFromCenterX = wallThickness / 2 + width / 2;
        for (i = -1; i <= 1; i += 2) {  //to add one to each side
            var object = new THREE.Mesh(geometry, material);
            object.rotateY(de2ra(-90));
            object.castShadow = true;
            object.name = 'shed'; //naming to find and remove again later
            object.position.set(
                i * offsetFromCenterX,
                0,
                offsetFromCenterZ
            );
            object.position.x += wallThickness / 2;   //to offset extrude så set works like any other geometry

            scene.add(object);
        }

        //backwall
        geometry = new THREE.BoxGeometry(
            width,
            shedHeightBack,
            wallThickness
        );
        object = new THREE.Mesh(geometry, material);
        object.position.set(
            0,
            shedHeightBack / 2,
            offsetFromCenterZ - shedDepth / 2 + wallThickness / 2
        );
        object.castShadow = true;
        object.name = 'shed'; //naming to find and remove again later
        scene.add(object);

        //door
        geometry = new THREE.BoxGeometry(doorWidth,
            doorHeight,
            doorThickness
        );
        object = new THREE.Mesh(geometry, material);
        object.position.set(
            width / 2 * guiShed.doorPlacement,
            doorHeight / 2,
            offsetFromCenterZ + shedDepth / 2 - wallThickness / 2
        );
        object.castShadow = true;
        object.rotateY(de2ra(45 * doorRotation));
        var doorOffset = (Math.sin(de2ra(45)) * doorWidth) / Math.sin(de2ra(90));
        object.position.z += doorOffset / 2 /*- 0.01*/; //offset out because of the angel
        object.name = 'shed'; //naming to find and remove again later
        scene.add(object);

        //frontwall
        geometry = PrismGeometry([
            new THREE.Vector2(-width / 2, shedHeightFront), //top left  
            new THREE.Vector2(-width / 2, 0),  //bottom left
            new THREE.Vector2(doorLeft, 0),  //door  bottom left
            new THREE.Vector2(doorLeft, doorHeight),  //door top left 
            new THREE.Vector2(doorLeft + doorWidth, doorHeight),  //door top right 
            new THREE.Vector2(doorLeft + doorWidth, 0),  //door bottom right 
            new THREE.Vector2(width / 2, 0),  //bottom right 
            new THREE.Vector2(width / 2, height)  //top right 
        ], wallThickness);
        object = new THREE.Mesh(geometry, material);
        object.position.set(
            0,
            0,
            offsetFromCenterZ + shedDepth / 2 - wallThickness / 2
        );
        object.position.z -= wallThickness / 2;
        object.castShadow = true;
        object.name = 'shed'; //naming to find and remove again later
        scene.add(object);
    }

    //load carport
    legs();
    if (!guiItem.gableRoof) {
        flatRoof();
    } else {
        gableRoof();
    }
    if (guiItem.shed) {
        shed();
    }
}

function removeCarport() {
    while (scene.getObjectByName('roof') != null) {
        scene.remove(scene.getObjectByName('roof'));
    }

    while (scene.getObjectByName('shed') != null) {
        scene.remove(scene.getObjectByName('shed'));
    }

    while (scene.getObjectByName('leg') != null) {
        scene.remove(scene.getObjectByName('leg'));
    }
}

function rerender() {
    removeCarport();
    loadCarport();
}

function render() {
    requestAnimationFrame(render);

    renderer.render(scene, camera);
}

init();
render();