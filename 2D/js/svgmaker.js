function SvgMaker() {
    this.wood = {
        color: 'black'
    }
    this.plastic = {
        color: 'black'
    }
    this.PrismGeometry = function (vertices, depth, loadside, material, position) {

    }
    this.makeGeometry = function (object, material, position) {
        var draw = SVG('drawing').size(500, 500)

        var polygon = draw.polygon('50,50 50,100 150,150')
        .fill(material.color)
        .stroke({ width: 1 })
        .move(50, 50);
        
        //var polygon = draw.polygon('0,0 100,50 50,100').fill('black').stroke({ width: 1 })
    }
}