function Loader(canvas){
    var threeJS = canvas;
    var meshes = [];
    this.add = function(mesh){  //needs to be rewritten later
        meshes.push(mesh);
    }

    this.paint = function(){
        canvas.removeOld();
        while(meshes.length > 0){
            canvas.add(meshes.pop());
        }
    }


}