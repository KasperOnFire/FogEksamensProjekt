function THREECanvas() {

    this.render = function() {
        render();
    }

    this.resetCamera = function() {
        resetCamera();
    }

    this.add = function(meshObj) {
        scene.add(meshObj);
    }

    //#fix
    this.removeOld = function() { //temp function just to get it started
        while (scene.getObjectByName('carport') != null) {
            scene.remove(scene.getObjectByName('carport'));
        }
    }

    var camera, scene, renderer, controls; //three.js

    function init() {
        scene = new THREE.Scene();

        camera = new THREE.PerspectiveCamera(
            70, window.innerWidth / window.innerHeight, 0.25, 300);

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
        resetCamera();

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

    }

    function resetCamera() {
        camera.position.set(6, 5, 9);
        controls.target.set(0, 1, 0);
        controls.update();
    }

    function onWindowResize() {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();

        renderer.setSize(window.innerWidth, window.innerHeight);
    }

    function render() {
        requestAnimationFrame(render);

        renderer.render(scene, camera);
    }

    init();
    render();
}