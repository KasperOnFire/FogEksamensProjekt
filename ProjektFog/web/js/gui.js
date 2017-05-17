function DatGui() {

    this.getObjects = function() {
        return {
            guiCarport: guiCarport,
            guiRoof: guiRoof,
            guiShed: guiShed

        }
    }

    this.setObjects = function(object) { //not tested yet #DOESN'T WORK
        gui.destroy();
        guiCarport = object.guiCarport;
        guiRoof = object.guiRoof;
        guiShed = object.guiShed;
        init();
        update();
    }
    var guiCarport, guiRoof, guiShed, guiFunctions;
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

    guiCarport = {
        width: 500,
        depth: 500,
        height: 230
    };

    guiRoof = {
        gableRoof: false,
        overhang: {
            sides: 20,
            front: 20,
            back: 20
        }
    };

    guiShed = {
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

        gui.add(guiShed, 'shed')
            .name('Skur')
            .onChange(function() {
                shedFolder();
                update()
            });

        //carportGui
        gui.add(guiCarport, 'width')
            .min(200)
            .max(750)
            .step(5)
            .name('Bredde')
            .onChange(function() {
                update()
            });
        gui.add(guiCarport, 'depth')
            .min(200)
            .max(800)
            .step(5)
            .name('Dybde')
            .onChange(function() {
                update()
            });
        gui.add(guiCarport, 'height')
            .min(200)
            .max(260)
            .step(5)
            .name('Højde')
            .onChange(function() {
                update()
            });

        //roofGui
        var roof = gui.addFolder('Tag');
        roof.add(guiRoof, 'gableRoof')
            .name('Spidstag')
            .onChange(function() {
                update()
            });
        var overhang = roof.addFolder('overhang');
        overhang.add(guiRoof.overhang, 'sides')
            .min(0)
            .max(30)
            .step(5)
            .name("sider")
            .onChange(function() {
                update()
            });
        overhang.add(guiRoof.overhang, 'front')
            .min(0)
            .max(30)
            .step(5)
            .name("foran")
            .onChange(function() {
                update()
            });
        overhang.add(guiRoof.overhang, 'back')
            .min(0)
            .max(30)
            .step(5)
            .name("bagved")
            .onChange(function() {
                update()
            });
        overhang.open();
        roof.open();

        //shedGui
        shedFolder();
    }

    function shedFolder() {
        if (guiShed.shed) {
            var shed = gui.addFolder('Skur');
            shed.add(guiShed, 'depth')
                .min(200)
                .max(400)
                .step(5)
                .name('Dybde')
                .onChange(function() {
                    update()
                });
            shed.add(guiShed, 'doorPlacement')
                .min(-1)
                .max(1)
                .step(0.05)
                .name('Dør placering').onChange(function() {
                    update()
                });
            shed.add(guiShed, 'side', ['Foran', 'Bagved', 'Venstre', 'Højre'])
                .name('Side')
                .onChange(function() {
                    update()
                });
            shed.open();
            shed.add(guiShed, 'rotateDoor')
                .name('Roter dør')
                .onChange(function() {
                    update()
                });
            shed.open();
        } else {
            gui.removeFolder('Skur');
        }
    }

    init();
}