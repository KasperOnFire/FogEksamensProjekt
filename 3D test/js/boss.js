
canvas = new THREECanvas();             //uses three.js
datGui = new DatGui();                  //uses dat.gui

loader = new Loader(canvas);            //uses three.js
objectMaker = new MeshMaker(loader);    //uses three.js

objectCalc = new CarportObjectCalc(objectMaker);



function update() {

    objectCalc.calcCarport(datGui.getObjects());

}

update();



/*
var geometry1 = new THREE.BoxGeometry(1, 1, 1);
geometry1.rotateX(THREE.Math.degToRad(45));
geometry1.translate(0, 0.5, 0);
var object = new THREE.Mesh(geometry1, material1);
object.castShadow = true;
object.position.set(0, 0, 0);
canvas.add(object);


//rotating after is bad because the center shifts a bit doing this
var geometry2 = new THREE.BoxGeometry(1, 1, 1);
geometry2.translate(0, 0.5, 0);
geometry2.rotateX(THREE.Math.degToRad(45));

var object = new THREE.Mesh(geometry1, material1);
object.castShadow = true;
object.position.set(0, 0, 0);
canvas.add(object);

var object = new THREE.Mesh(geometry2, material2);
object.castShadow = true;
object.position.set(0, 0, 0);
canvas.add(object);
*/