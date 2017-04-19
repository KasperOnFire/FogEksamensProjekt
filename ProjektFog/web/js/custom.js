//ja, jeg stjal det =)

var currentColor;

function changeColor() {
    var colors = ['#F44336', '#E91E63', '#9C27B0', '#673AB7', '#3F51B5', '#2196F3', '#03A9F4', '#00BCD4', '#009688', '#4CAF50', '#8BC34A', '#FF9800', '#FF5722'];
    var randNumb = Math.floor(Math.random() * colors.length);
    currentColor = colors[randNumb];
    document.getElementsByClassName("h1").style["background-color"] = currentColor;
}