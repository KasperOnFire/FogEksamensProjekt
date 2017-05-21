function Loader(canvas) {
    var grpMesh = new THREE.Object3D();
    var grpMeshOld;

    this.add = function(meshObj) {
        grpMesh.add(meshObj);
    }

    this.paint = function() {
        canvas.remove(grpMeshOld);
        canvas.add(grpMesh);
        grpMeshOld = grpMesh;
        grpMesh = new THREE.Object3D();
    }
}