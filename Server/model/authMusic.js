


authMusic = (music, gfs, model) => {
    let writeStream = gfs.createWriteStream({
        filename:music['audio'][0]['originalFilename'],
        mode:'w',
        content_type:'audio/mpeg'
    });

    writeStream.write('../music/'+music['audio'][0]['originalFilename']);

    writeStream.on('close',(files)=>{
        console.log(files);
        let user = new model({musicName: music['audio'][0]['originalFilename'], creator: '이상훈', musicFIle:files.toString()});
        user.save(err => {
            if (err) {
                console.log(err);
                return;
            }
            console.log("사용자 데이터 추가함!");
        })
    });

    writeStream.end();



};

module.exports = authMusic;