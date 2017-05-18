function Loader(canvas) {
    var meshes = [];
    // #fix group and push as group

    this.add = function(meshObj) {
        meshes.push(meshObj);
    }

    this.paint = function() {
        canvas.removeOld();
        while (meshes.length > 0) {
            canvas.add(meshes.pop());
        }
    }
}