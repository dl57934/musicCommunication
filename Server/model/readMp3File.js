let fs = require('fs');
let fileReader = require('filereader');
let reader = new fileReader();
readMp3 = (gfs, name)=>{
gfs.files.find({
    filename:name
}).toArray((err, files) =>{
    if(files.length === 0){
        console.log('file Not Found');
    }

    let data = [];
    let readStream = gfs.createReadStream({
        filename:files[0].filename
    });

    readStream.on('data', (chunk)=>{
        data.push(chunk);
    });

    readStream.on('end', ()=>{
        data = Buffer.concat(data);
        console.log(data);
          fs.writeFileSync('test.mp3', Buffer.from(new Uint8Array(data)));
          console.log('success');
    })
})
};






module.exports = readMp3;