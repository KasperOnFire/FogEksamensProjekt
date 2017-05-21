function DatGui() {

    this.getObjects = function() {
        return {
            carport: carport,
            roof: roof,
            shed: shed
        }
    }

    this.setObjects = function(JSONObj) {
        gui.destroy();
        carport = JSONObj.carport;
        roof = JSONObj.roof;
        shed = JSONObj.shed;
        init();
        update();
    }

    var carport, roof, shed, guiFunctions;
    var gui;

    dat.GUI.prototype.removeFolder = function(name) { //extend with useful function
        var folder = this.__folders[name];
        if (folder != null) {
            folder.close();
            this.__ul.removeChild(folder.domElement.parentNode);
            delete this.__folders[name];
            this.onResize();
        }
    }

    guiFunctions = {
        resetCamera: function() {
            canvas.resetCamera();
        },
        saveData: function() {
            sendJson();
        }
    };

    carport = {
        width: 500,
        depth: 500,
        height: 230
    };

    roof = {
        gableRoof: false,
        overhang: {
            sides: 20,
            front: 20,
            back: 20
        }
    };

    shed = {
        shed: false,
        depth: 300,
        doorPlacement: 0.00,
        side: 'Foran',
        rotateDoor: false
    };


    function init() {
        gui = new dat.gui.GUI();

        gui.add(guiFunctions, 'resetCamera')
            .name('Reset Kamera');
        gui.add(guiFunctions, 'saveData')
            .name('Gem Carport');

        gui.add(shed, 'shed')
            .name('Skur')
            .onChange(function() {
                shedFolder();
                update()
            });

        //carportGui
        gui.add(carport, 'width')
            .min(200)
            .max(750)
            .step(5)
            .name('Bredde')
            .onChange(function() {
                update()
            });
        gui.add(carport, 'depth')
            .min(200)
            .max(800)
            .step(5)
            .name('Dybde')
            .onChange(function() {
                update()
            });
        gui.add(carport, 'height')
            .min(200)
            .max(260)
            .step(5)
            .name('Højde')
            .onChange(function() {
                update()
            });

        //roofGui
        var folderRoof = gui.addFolder('Tag');
        folderRoof.add(roof, 'gableRoof')
            .name('Spidstag')
            .onChange(function() {
                update()
            });
        var folderOverhang = folderRoof.addFolder('overhang');
        folderOverhang.add(roof.overhang, 'sides')
            .min(0)
            .max(30)
            .step(5)
            .name("sider")
            .onChange(function() {
                update()
            });
        folderOverhang.add(roof.overhang, 'front')
            .min(0)
            .max(30)
            .step(5)
            .name("foran")
            .onChange(function() {
                update()
            });
        folderOverhang.add(roof.overhang, 'back')
            .min(0)
            .max(30)
            .step(5)
            .name("bagved")
            .onChange(function() {
                update()
            });
        folderOverhang.open();
        folderRoof.open();

        //shedGui
        shedFolder();
    }

    function shedFolder() {
        if (shed.shed) {
            var folderShed = gui.addFolder('Skur');
            folderShed.add(shed, 'depth')
                .min(200)
                .max(400)
                .step(5)
                .name('Dybde')
                .onChange(function() {
                    update()
                });
            folderShed.add(shed, 'doorPlacement')
                .min(-1)
                .max(1)
                .step(0.05)
                .name('Dør placering').onChange(function() {
                    update()
                });
            folderShed.add(shed, 'side', ['Foran', 'Bagved', 'Venstre', 'Højre'])
                .name('Side')
                .onChange(function() {
                    update()
                });
            folderShed.open();
            folderShed.add(shed, 'rotateDoor')
                .name('Roter dør')
                .onChange(function() {
                    update()
                });
            folderShed.open();
        } else {
            gui.removeFolder('Skur');
        }
    }

    init();
}