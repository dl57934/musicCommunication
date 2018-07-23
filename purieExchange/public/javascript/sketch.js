 preload = ()=> {
    sound = loadSound('../music/001 연애시대 (Feat. Ra.D) (Narr. 한효주)-이승기.mp3');
    sound1 = loadSound('../music/_Digimon OST_ Butterfly - Fingerstyle Guitar Cover.mp3');
  }
  
setup=()=>{
  var cnv = createCanvas(64*160,300);
  colorMode(HSB);
    cnv.mouseClicked(togglePlay);
    fft = new p5.FFT(0.9, 16);
    w = width/3072
    sound.amp(0.5);
    sound1.amp(0.5);
    background(0);
  }
  let addWidth = 0;
  
  draw=()=>{
    wid = 8;
    let totalY = 0;
    let spectrum = fft.analyze(); 
    noStroke();
    for (let i = 0; i < spectrum.length; i++){
      let rectWidth = 0 ;
      let amp = spectrum[i];
      let y = map(amp, 0, 301, height, 0);
      fill(w, 255, 255);
      totalY += y; 
      if (i==15){
        y16= totalY/16;
        if(y16 != 0){
        rect(addWidth, y16, w-2, height - y16);
        console.log(height-y16);
        addWidth += 3;
        }
      }
      
    }
    
   }
  
  // fade sound if mouse is over canvas
  togglePlay=()=> {
    if (sound.isPlaying()) {
      sound.pause();
    } else {
      sound.loop();
    }
  }