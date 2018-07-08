var image;
var rainbowImage= null;
var grayImage = null;
var redImage = null;
var blImage = null;

var gcanvas = null;

// Load the image into the website
function loadImage(){
  gcanvas = document.getElementById("can");
  // clear the canvas
  var ctx = gcanvas.getContext("2d");
  ctx.clearRect(0, 0, gcanvas.width, gcanvas.height);
  image = null;
  rainbowImage = null;
  grayImage = null;
  redImage = null;
  blImage = null;
  
  image = new SimpleImage(document.getElementById("fbutton"));
  image.drawTo(gcanvas);
}

function ok(theImage){
  if(theImage == null){
    console.log("is null");
    return false;
  }
  if(! theImage.complete()){
    console.log("not ready");
    return false;
  }
  
  var s = image.getWidth() + " x " + image.getHeight();
  var dimension = document.getElementById("dims");
  dimension.innerHTML = s;
  
  console.log("image ready");
  return true;
}

function copyImage(theImage){
  var newImage = new SimpleImage(theImage.getWidth(), theImage.getHeight());
  for (var pixel of newImage.values()){
    x = pixel.getX();
    y = pixel.getY();
    newImage.setPixel(x, y, theImage.getPixel(x, y));
  }
  return newImage;
}

// =============================
// rainbow
// =============================
function rainbow(){
  rainbowImage = copyImage(image);
  console.log("made rainbow first");
  
  rainbowImage = makeRainbow(rainbowImage);
  console.log("made rainbow new");
  
  rainbowImage.drawTo(gcanvas);
}

function makeRainbow(theImage){
  var h = theImage.getHeight();
  var bands = Math.floor(h/7);
  console.log("rainbow height: "+h+" bands: "+bands);
  low = 0;
  high = low + bands;
  
  for (var n = 0; n < 7; n++){
    for(var k = low; k <= high; k++){
      for(var x = 0; x < theImage.getWidth(); x++){
        var pix = theImage.getPixel(x, k);
        switch (n) {
          case 0 :
            pix.setRed(255);
            break;
          case 1 :
            pix.setRed(255);
            pix.setGreen(165);
            break;
          case 2 :
            pix.setRed(255);
            pix.setGreen(255);
            break;
          case 3 :
            pix.setGreen(255);
            break;
          case 4 :
            pix.setBlue(255);
            break;
          case 5 :
            pix.setRed(75);
            pix.setGreen(0);
            pix.setBlue(130);
            break;
          case 6 :
            pix.setRed(85);
            pix.setGreen(36);
            pix.setBlue(139);
            break;
          default :
            pix.setRed(0);
            pix.setGreen(0);
            pix.setBlue(0);
        } //switch ends
      } //x loop
    } //k loop
    console.log("finish "+n+"'th zone from "+low+" to "+high);
    low = high + 1;
    high = low + bands;
    if(high > h){
      high = h - 1;
    }
  }
  console.log("done rainbow");
  return theImage;
}


// =============================
// gray
// =============================
function gray(){
  grayImage = copyImage(image);
  console.log("made gray first");
  
  grayImage = makeGray(grayImage);
  console.log("made gray new");
  
  grayImage.drawTo(gcanvas);
}

function makeGray(theImage){
  if(! ok(theImage)){
    console.log("not ok gray");
    return theImage;
  }
  console.log("making gray");
  for(var px of theImage.values()){
    avg = (px.getRed() + px.getGreen() + px.getBlue())/3;
    px.setRed(avg);
    px.setGreen(avg);
    px.setBlue(avg);
  }
  console.log("finish gray");
  return theImage;
}

// =============================
// red
// =============================
function red(){
  redImage = copyImage(image);
  console.log("make red first");
  
  redImage = makeRed(redImage);
  console.log("make red new")
  
  redImage.drawTo(gcanvas);
  console.log("finish red")
}

function makeRed(theImage){
  console.log("making red");
  if(! ok(theImage)){
    console.log("not ok red");
    return theImage;
  }
  for(var px of theImage.values()){
    px.setRed(255);
  }
  console.log("made it red");
  return theImage;
}

// =============================
// blur
// =============================
function doblur(){
  blImage = copyImage(image);
  console.log("make blur first");
  
  var radius = 20;
  blImage = makeBlur(blImage, radius);
  console.log("make blur new");
  
  blImage.drawTo(gcanvas);
  console.log("finish blur");
}

// ensure the coordinate is in [0, size - 1]
function ensureInImage(coordinate, size){
  if (coordinate < 0){
    return 0;
  }
  if (coordinate > size - 1){
    return (size - 1);
  }
  return coordinate;
}

function getPixelNearby(theImage, x, y, diameter){
  // the changing range of x and y is [-diameter/2, diameter/2]
  var dx = Math.random() * diameter - diameter/2; 
  var dy = Math.random() * diameter - diameter/2;
  var nx = ensureInImage(x + dx, theImage.getWidth());
  var ny = ensureInImage(y + dy, theImage.getHeight());
  return theImage.getPixel(nx, ny);
}

function makeBlur(theImage, radius){
  if(! ok(theImage)){
    console.log("not ok blur");
    return theImage;
  }
  var output = new SimpleImage(theImage.getWidth(), theImage.getHeight());
  for (var px of output.values()){
    x = px.getX();
    y = px.getY();
    if (Math.random() > 0.5){
      output.setPixel(x, y, getPixelNearby(theImage, x, y, radius));
    } else {
      output.setPixel(x, y, theImage.getPixel(x, y));
    }
  }
  return output;
}

// =============================
// reset
// =============================

function reset(){
  if(ok(image)){
    console.log("reset image");
    image.drawTo(gcanvas);
  }
}