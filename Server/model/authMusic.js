const createModel = require('./connectMongoDB');
authMusic= (music) => {
    let DBInfo = new createModel();
    let model = DBInfo.createSchema();
    let user = new model({music:"Over_the_Horizon.mp3"});
    user.save(err=>{
        if(err){
            console.log(err);
            return;
        }
        console.log("사용자 데이터 추가함!");
    })


};

module.exports = authMusic;