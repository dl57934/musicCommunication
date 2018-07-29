const mongoose = require('mongoose');
let database;

class DBInfo {

    connectDB() {
        const databaseUrl = 'mongodb://localhost:27017/local';
        console.log('데이터 베이스 연결을 시도합니다.');
        database = mongoose.connection;
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

    createSchema() {
        let userSchema = mongoose.Schema({
            musicName: {type: String},
            creator: {type: String},
            musicFile: [mongoose.Schema.Types.Mixed],
        });

        return mongoose.model("musicWithGroup", userSchema);
    };
    createUserSchema(){
        const userSchema = mongoose.Schema({
            id:{type:String, required:true, unique:true},
            password:{type:String, required:true},
            groupName:{type:String, unique:true}
        });
        userSchema.static('findById', (id, callback)=>{
            return this.find({id:id}, calback);
        });
        return mongoose.model("userInfo", userSchema);
    }
    createGroupSchema(){
        const groupSchema = mongoose.Schema({
            groupName:{type:String, required:true, unique:true},
            groupMember:{type:String},
            groupExplanation:{type:String},
            musicName:{type:String}
        });
        groupSchema.static('findById', (id, callback)=>{
            return this.find({id:id}, callback)
        })
        return mongoose.model("groupInfo", groupSchema);
    }
    createGridFs() {
        let gridFs = require('gridfs-stream');
        gridFs.mongo = mongoose.mongo;
        let gfs = gridFs(database.db);
        return gfs;
    }
}

module.exports = DBInfo;
