var camera, scene, renderer; //three.js 

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
var wallThickness = 0.15;


//gui data object
var guiItem = {
    gableRoof: false,
    shed: false,
    width: 500,
    depth: 500,
    height: 230
};

function init() {
    scene = new THREE.Scene();

    camera = new THREE.PerspectiveCamera(
        70, window.innerWidth / window.innerHeight, 0.25, 300);

    camera.position.set(6, 5, 9);

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

    // Geometry
    loadCarport();

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

    // Renderer
    renderer = new THREE.WebGLRenderer();
    renderer.shadowMap.enabled = true;
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight);
    window.addEventListener('resize', onWindowResize, false);
    document.body.appendChild(renderer.domElement);

    // Controls
    var controls = new THREE.OrbitControls(camera, renderer.domElement);
    controls.target.set(0, 1, 0);
    controls.update();

    // GUI
    var gui = new dat.gui.GUI();
    gui.add(guiItem, 'gableRoof').name('Tag').onChange(function() { rerender() });
    gui.add(guiItem, 'shed').name('Skur').onChange(function() { rerender() });
    gui.add(guiItem, 'width').min(200).max(750).step(5).name('Bredde').onChange(function() { rerender() });
    gui.add(guiItem, 'depth').min(200).max(800).step(5).name('Dybde').onChange(function() { rerender() });
    gui.add(guiItem, 'height').min(200).max(360).step(5).name('Højde').onChange(function() { rerender() });
}

function onWindowResize() {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();

    renderer.setSize(window.innerWidth, window.innerHeight);
}

function loadCarport() {
    var material = new THREE.MeshPhongMaterial({
        color: 0x8c4d27,
        shininess: 100,
        side: THREE.DoubleSide
    })

    //gets the dimentions from the GUI
    height = guiItem.height / 100;
    depth = guiItem.depth / 100;
    width = guiItem.width / 100;

    roofDepth = Math.sqrt(Math.pow(0.10, 2) + Math.pow(depth, 2));
    slope = -Math.tan(0.1 / (depth - spaceBack - spaceFront)) * 100; //slope of a flat roof

    console.log(slope); //sout slope for debugging

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
        var tempX = (width - legsThickness) / 2 - spaceSides; //sets the temp x for the current 
        var tempZ = zBack; //sets the temp z
        var zJump = (depth - legsThickness - spaceBack - spaceFront) / (numberOfLegs / 2 - 1); //calculates spaces between each leg
        var heightBumb = 0;
        if (guiItem.gableRoof == false) {
            heightBumb = 0.1 / (numberOfLegs / 2);
        }

        for (i = 1; i <= 2; i++) { //loop to place the 2 rows of legs
            legSupport(tempX);
            for (j = 0; j < numberOfLegs / 2; j++) { //make the leg obejcts for one side
                geometry = new THREE.BoxGeometry(legsThickness, height + heightBumb, legsThickness);
                object = new THREE.Mesh(geometry, material);
                object.position.set(tempX, ((height + heightBumb) / 2), tempZ);
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
        geometry = new THREE.BoxGeometry(legsThickness + 0.05, 0.2, roofDepth - 0.20);
        object = new THREE.Mesh(geometry, material);
        object.position.set(x, height - 0.0, 0);
        object.castShadow = true;
        object.name = "roof"; //naming to find and remove again later
        if (guiItem.gableRoof == false) { //roof = gable lay support flat
            object.rotateX(de2ra(slope));
        }
        scene.add(object);
    }

    function de2ra(degree) {
        return degree * (Math.PI / 180);
    }

    function flatRoof() {
        geometry = new THREE.BoxGeometry(width, roofThickness, roofDepth);
        object = new THREE.Mesh(geometry, material);
        object.position.set(0, (height + roofThickness / 2) + 0.1, 0);
        object.castShadow = true;
        object.name = "roof"; //naming to find and remove again later
        object.rotateX(de2ra(slope));
        scene.add(object);
    }

    function backwall() {
        geometry = new THREE.BoxGeometry(width, (height + roofThickness), wallThickness + 2);
        object = new THREE.Mesh(geometry, material);
        object.position.set(0, ((height + roofThickness) / 2), -((depth + wallThickness) / 2) - 1);
        object.castShadow = true;
        object.name = "wall"; //naming to find and remove again later
        scene.add(object);
    }
    legs();
    flatRoof();
    if (guiItem.shed == true) {
        backwall();
    }
}

function removeCarport() {
    while (scene.getObjectByName("roof") != null) {
        scene.remove(scene.getObjectByName("roof"));
    }

    while (scene.getObjectByName("wall") != null) {
        scene.remove(scene.getObjectByName("wall"));
    }

    while (scene.getObjectByName("leg") != null) {
        scene.remove(scene.getObjectByName("leg"));
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