const readMp3 = require('./readMp3File');
let fs = require('fs');
authMusic = (music, gfs, model) => {
    let writeStream = gfs.createWriteStream({
        filename:music['audio'][0]['originalFilename'],
        mode:'w',
        content_type:'audio/mpeg'
    });



    writeStream.on('close',(files)=>{
        let user = new model({musicName: music['audio'][0]['originalFilename'], creator: '이상훈',musicFile:files});
        user.save(err => {
            if (err) {
                console.log(err);
                return;
            }
            console.log("사용자 데이터 추가함!");
        })
    });

    let content = fs.readFileSync('music/'+music['audio'][0]['originalFilename']);
    console.log(content);
    writeStream.write(content);
    writeStream.end();
    readMp3(gfs, music['audio'][0]['originalFilename']);
};

module.exports = authMusic;