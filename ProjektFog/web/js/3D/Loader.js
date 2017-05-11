function Loader(canvas) {
    var meshes = [];

    this.add = function(mesh) {
        meshes.push(mesh);
    }

    this.paint = function() {
        canvas.removeOld();
        while (meshes.length > 0) {
            canvas.add(meshes.pop());
        }
    }
}