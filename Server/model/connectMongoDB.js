const mongoose = require('mongoose');


class DBInfo {

    connectDB(){
        const databaseUrl = 'mongodb://localhost:27017/local';

        console.log('데이터 베이스 연결을 시도합니다.');
        let database = mongoose.connection;
        mongoose.connect(databaseUrl);
        database.on('error', console.error.bind(console, 'mongoose connection error.'));
        database.on('open', () => {
            console.log("데이터베이스에 연결되었습니다.: " + databaseUrl);
        });
        database.on('disconnected', () => {
            console.log('연결이 끊어졌습니다. 5초 뒤에 다시 연결합니다.');
            setInterval(mongoose.connect(databaseUrl), 5000);
        });
    };
    createSchema(){
        let userSchema = mongoose.Schema({
            music: {type:String,required: true, unique: true},
            create_at: Date,
            update_at: Date
        });

        console.log("UserModel 정의함");
        return mongoose.model("musicWithGroup", userSchema);
    };

}

module.exports = DBInfo;
