function Svgmaker() {
    this.wood = {
        color: '0x000000'
    }
    this.plastic = {
        color: '0x000000'
    }
    this.PrismGeometry = function (vertices, depth, loadside, material, position) {

    }
    this.makeGeometry = function (object, material, position) {
        var draw = SVG('drawing').size(500, 500)

        var polygon = draw.polygon('50,50 100,100 150,150')
        polygon.fill(material.color).move(50, 50)
        console.log('pik')
    }
}